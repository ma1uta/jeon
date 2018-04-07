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

package io.github.ma1uta.identity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.github.ma1uta.identity.configuration.AssociationConfiguration;
import io.github.ma1uta.identity.configuration.InvitationServiceConfiguration;
import io.github.ma1uta.identity.configuration.KeyServiceConfiguration;
import io.github.ma1uta.identity.configuration.SelfKeyGeneratorConfiguration;
import io.github.ma1uta.identity.configuration.SessionServiceConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

public class IdentityConfiguration extends Configuration {

    @NotEmpty
    private String secureRandomSeed;

    @NotEmpty
    private SelfKeyGeneratorConfiguration selfKeyGeneratorConfiguration;

    @NotEmpty
    private KeyServiceConfiguration keyServiceConfiguration;

    @NotEmpty
    private AssociationConfiguration associationConfiguration;

    @NotEmpty
    private InvitationServiceConfiguration invitationServiceConfiguration;

    @NotEmpty
    private SessionServiceConfiguration sessionServiceConfiguration;

    @JsonProperty
    public String getSecureRandomSeed() {
        return secureRandomSeed;
    }

    @JsonProperty
    public void setSecureRandomSeed(String secureRandomSeed) {
        this.secureRandomSeed = secureRandomSeed;
    }

    public SelfKeyGeneratorConfiguration getSelfKeyGeneratorConfiguration() {
        return selfKeyGeneratorConfiguration;
    }

    public void setSelfKeyGeneratorConfiguration(SelfKeyGeneratorConfiguration selfKeyGeneratorConfiguration) {
        this.selfKeyGeneratorConfiguration = selfKeyGeneratorConfiguration;
    }

    public KeyServiceConfiguration getKeyServiceConfiguration() {
        return keyServiceConfiguration;
    }

    public void setKeyServiceConfiguration(KeyServiceConfiguration keyServiceConfiguration) {
        this.keyServiceConfiguration = keyServiceConfiguration;
    }

    public AssociationConfiguration getAssociationConfiguration() {
        return associationConfiguration;
    }

    public void setAssociationConfiguration(AssociationConfiguration associationConfiguration) {
        this.associationConfiguration = associationConfiguration;
    }

    public InvitationServiceConfiguration getInvitationServiceConfiguration() {
        return invitationServiceConfiguration;
    }

    public void setInvitationServiceConfiguration(
        InvitationServiceConfiguration invitationServiceConfiguration) {
        this.invitationServiceConfiguration = invitationServiceConfiguration;
    }

    public SessionServiceConfiguration getSessionServiceConfiguration() {
        return sessionServiceConfiguration;
    }

    public void setSessionServiceConfiguration(SessionServiceConfiguration sessionServiceConfiguration) {
        this.sessionServiceConfiguration = sessionServiceConfiguration;
    }
}
