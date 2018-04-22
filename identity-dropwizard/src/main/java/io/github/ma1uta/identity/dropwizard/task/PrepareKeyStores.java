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

package io.github.ma1uta.identity.dropwizard.task;

import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;
import io.github.ma1uta.identity.service.KeyService;

import java.io.PrintWriter;

/**
 * Prepare key stores.
 * <p/>
 * Delete all existing keys and generate an one long-term key.
 */
public class PrepareKeyStores extends Task {

    private final KeyService keyService;

    /**
     * Create a new command with the given name and description.
     *
     * @param name       the name of the command, used for command line invocation.
     * @param keyService key service.
     */
    public PrepareKeyStores(String name, KeyService keyService) {
        super(name);
        this.keyService = keyService;
    }

    public KeyService getKeyService() {
        return keyService;
    }

    @Override
    public void execute(ImmutableMultimap<String, String> parameters, PrintWriter output) {
        getKeyService().cleanKeyStores();
        getKeyService().generateLongTermKey();

        output.println("Done");
    }
}
