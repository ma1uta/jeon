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

package io.github.ma1uta.identity.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.github.ma1uta.identity.configuration.AssociationConfiguration;
import io.github.ma1uta.identity.configuration.InvitationServiceConfiguration;
import io.github.ma1uta.identity.configuration.KeyServiceConfiguration;
import io.github.ma1uta.identity.configuration.SelfKeyGeneratorConfiguration;
import io.github.ma1uta.identity.configuration.SessionServiceConfiguration;
import io.github.ma1uta.identity.dropwizard.service.MailConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class IdentityConfiguration extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @NotEmpty
    private String secureRandomSeed;

    @Valid
    @NotNull
    private SelfKeyGeneratorConfiguration selfKeyGeneratorConfiguration;

    @Valid
    @NotNull
    private KeyServiceConfiguration keyServiceConfiguration;

    @Valid
    @NotNull
    private AssociationConfiguration associationConfiguration;

    @Valid
    @NotNull
    private InvitationServiceConfiguration invitationServiceConfiguration;

    @Valid
    @NotNull
    private SessionServiceConfiguration sessionServiceConfiguration;

    @Valid
    @NotNull
    private MailConfiguration mailConfiguration;

    @JsonProperty
    public DataSourceFactory getDatabase() {
        return database;
    }

    @JsonProperty
    public void setDatabase(DataSourceFactory database) {
        this.database = database;
    }

    @JsonProperty
    public String getSecureRandomSeed() {
        return secureRandomSeed;
    }

    @JsonProperty
    public void setSecureRandomSeed(String secureRandomSeed) {
        this.secureRandomSeed = secureRandomSeed;
    }

    @JsonProperty
    public SelfKeyGeneratorConfiguration getSelfKeyGeneratorConfiguration() {
        return selfKeyGeneratorConfiguration;
    }

    @JsonProperty
    public void setSelfKeyGeneratorConfiguration(SelfKeyGeneratorConfiguration selfKeyGeneratorConfiguration) {
        this.selfKeyGeneratorConfiguration = selfKeyGeneratorConfiguration;
    }

    @JsonProperty
    public KeyServiceConfiguration getKeyServiceConfiguration() {
        return keyServiceConfiguration;
    }

    @JsonProperty
    public void setKeyServiceConfiguration(KeyServiceConfiguration keyServiceConfiguration) {
        this.keyServiceConfiguration = keyServiceConfiguration;
    }

    @JsonProperty
    public AssociationConfiguration getAssociationConfiguration() {
        return associationConfiguration;
    }

    @JsonProperty
    public void setAssociationConfiguration(AssociationConfiguration associationConfiguration) {
        this.associationConfiguration = associationConfiguration;
    }

    @JsonProperty
    public InvitationServiceConfiguration getInvitationServiceConfiguration() {
        return invitationServiceConfiguration;
    }

    @JsonProperty
    public void setInvitationServiceConfiguration(
        InvitationServiceConfiguration invitationServiceConfiguration) {
        this.invitationServiceConfiguration = invitationServiceConfiguration;
    }

    @JsonProperty
    public SessionServiceConfiguration getSessionServiceConfiguration() {
        return sessionServiceConfiguration;
    }

    @JsonProperty
    public void setSessionServiceConfiguration(SessionServiceConfiguration sessionServiceConfiguration) {
        this.sessionServiceConfiguration = sessionServiceConfiguration;
    }

    @JsonProperty
    public MailConfiguration getMailConfiguration() {
        return mailConfiguration;
    }

    @JsonProperty
    public void setMailConfiguration(MailConfiguration mailConfiguration) {
        this.mailConfiguration = mailConfiguration;
    }
}
