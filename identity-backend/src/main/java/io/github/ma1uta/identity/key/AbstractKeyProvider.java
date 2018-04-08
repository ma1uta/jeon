/*
 * Copyright sablintolya@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ma1uta.identity.key;

import io.github.ma1uta.jeon.exception.MatrixException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

public abstract class AbstractKeyProvider implements KeyProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractKeyProvider.class);

    protected final StoreHelper storeHelper;
    protected final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    protected final AtomicInteger readBarrier = new AtomicInteger(0);

    public AbstractKeyProvider(String secureRandomSeed) {
        this.storeHelper = new StoreHelper(secureRandomSeed);
    }

    protected StoreHelper getStoreHelper() {
        return storeHelper;
    }

    protected <T> T readLock(Supplier<T> action) {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        try {
            synchronized (readBarrier) {
                while (readBarrier.get() > 0) {
                    try {
                        readBarrier.wait(10 * 1000);
                    } catch (InterruptedException e) {
                        String msg = "Cannot acquire lock.";
                        LOGGER.error(msg, e);
                        throw new MatrixException(MatrixException.M_INTERNAL, msg);
                    }
                }
            }
            readLock.lock();
            return action.get();
        } finally {
            readLock.unlock();
        }
    }

    protected <T> T writeLock(Supplier<T> action) {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        try {
            synchronized (readBarrier) {
                readBarrier.incrementAndGet();
            }
            writeLock.lock();
            return action.get();
        } finally {
            writeLock.unlock();
            synchronized (readBarrier) {
                readBarrier.decrementAndGet();
            }
            readBarrier.notifyAll();
        }
    }
}
