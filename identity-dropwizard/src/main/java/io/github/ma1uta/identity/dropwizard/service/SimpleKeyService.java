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

package io.github.ma1uta.identity.dropwizard.service;

import io.github.ma1uta.identity.configuration.KeyServiceConfiguration;
import io.github.ma1uta.identity.key.KeyProvider;
import io.github.ma1uta.identity.service.impl.AbstractKeyService;

import java.security.cert.Certificate;
import java.util.Map;
import java.util.Optional;

/**
 * Very simple implementation of the {@link io.github.ma1uta.identity.service.KeyService} based on the {@link AbstractKeyService}.
 */
public class SimpleKeyService extends AbstractKeyService {

    public SimpleKeyService(KeyProvider longTermProvider, KeyProvider shortTermProvider, KeyServiceConfiguration configuration) {
        super(longTermProvider, shortTermProvider, configuration);
    }

    @Override
    public void init() {
        super.initInternal();
    }

    @Override
    public Optional<Certificate> key(String key) {
        return super.keyInternal(key);
    }

    @Override
    public boolean validLongTerm(String publicKey) {
        return super.validLongTermInternal(publicKey);
    }

    @Override
    public boolean validShortTerm(String publicKey) {
        return super.validShortTermInternal(publicKey);
    }

    @Override
    public Map<String, Map<String, String>> sign(String content) {
        return super.signInternal(content);
    }

    @Override
    public String retrieveLongTermKey() {
        return super.retrieveLongTermKeyInternal();
    }

    @Override
    public String generateShortTermKey() {
        return super.generateShortTermKeyInternal();
    }

    @Override
    public void generateLongTermKey() {
        super.generateLongTermKeyInternal();
    }

    @Override
    public void cleanKeyStores() {
        super.cleanKeyStoresInternal();
    }

}
