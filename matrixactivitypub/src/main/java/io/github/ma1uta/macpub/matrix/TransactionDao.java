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

package io.github.ma1uta.macpub.matrix;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * DAO for transactions.
 */
public class TransactionDao extends AbstractDAO<Transaction> {

    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public TransactionDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Save new transaction.
     *
     * @param transaction transaction.
     */
    public void save(Transaction transaction) {
        persist(transaction);
    }

    /**
     * Check the specified transaction exists.
     *
     * @param txnId transaction id.
     * @return {@code true} if exist else {@code false}.
     */
    public boolean exist(String txnId) {
        return get(txnId) != null;
    }
}
