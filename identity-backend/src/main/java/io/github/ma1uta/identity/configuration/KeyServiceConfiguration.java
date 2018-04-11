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

package io.github.ma1uta.identity.configuration;

/**
 * Key service configuration.
 */
public class KeyServiceConfiguration {

    /**
     * Default amount of new keys.
     */
    public static final int DEFAULT_AMOUNT_KEY_TO_CREATE = 20;

    /**
     * Self key generator configuration.
     */
    private SelfKeyGeneratorConfiguration selfKeyGeneratorConfiguration;

    /**
     * Long-term keys configuration.
     */
    private KeyStoreConfiguration longTermConfiguration;

    /**
     * Short-term keys configuration.
     */
    private KeyStoreConfiguration shortTermConfiguration;

    /**
     * Used short-term keys configuration.
     */
    private KeyStoreConfiguration usedShortTermConfiguration;

    /**
     * Secure random seed.
     */
    private String secureRandomSeed;

    /**
     * Use or not server root keys.
     */
    private boolean useServerKey = false;

    /**
     * Server root key configuration.
     */
    private ServerKeyConfiguration serverKeyConfiguration;

    /**
     * Amount of the key to create at one time.
     */
    private int amountKeysToCreate = DEFAULT_AMOUNT_KEY_TO_CREATE;

    /**
     * Identity server hostname.
     */
    private String hostname;

    public SelfKeyGeneratorConfiguration getSelfKeyGeneratorConfiguration() {
        return selfKeyGeneratorConfiguration;
    }

    public void setSelfKeyGeneratorConfiguration(SelfKeyGeneratorConfiguration selfKeyGeneratorConfiguration) {
        this.selfKeyGeneratorConfiguration = selfKeyGeneratorConfiguration;
    }

    public KeyStoreConfiguration getLongTermConfiguration() {
        return longTermConfiguration;
    }

    public void setLongTermConfiguration(KeyStoreConfiguration longTermConfiguration) {
        this.longTermConfiguration = longTermConfiguration;
    }

    public KeyStoreConfiguration getShortTermConfiguration() {
        return shortTermConfiguration;
    }

    public void setShortTermConfiguration(KeyStoreConfiguration shortTermConfiguration) {
        this.shortTermConfiguration = shortTermConfiguration;
    }

    public KeyStoreConfiguration getUsedShortTermConfiguration() {
        return usedShortTermConfiguration;
    }

    public void setUsedShortTermConfiguration(KeyStoreConfiguration usedShortTermConfiguration) {
        this.usedShortTermConfiguration = usedShortTermConfiguration;
    }

    public String getSecureRandomSeed() {
        return secureRandomSeed;
    }

    public void setSecureRandomSeed(String secureRandomSeed) {
        this.secureRandomSeed = secureRandomSeed;
    }

    public boolean isUseServerKey() {
        return useServerKey;
    }

    public void setUseServerKey(boolean useServerKey) {
        this.useServerKey = useServerKey;
    }

    public ServerKeyConfiguration getServerKeyConfiguration() {
        return serverKeyConfiguration;
    }

    public void setServerKeyConfiguration(ServerKeyConfiguration serverKeyConfiguration) {
        this.serverKeyConfiguration = serverKeyConfiguration;
    }

    public int getAmountKeysToCreate() {
        return amountKeysToCreate;
    }

    public void setAmountKeysToCreate(int amountKeysToCreate) {
        this.amountKeysToCreate = amountKeysToCreate;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
