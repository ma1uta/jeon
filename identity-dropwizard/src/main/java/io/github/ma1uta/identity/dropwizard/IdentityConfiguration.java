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

/**
 * Identity configuration.
 */
public class IdentityConfiguration extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @NotEmpty
    private String secureRandomSeed;

    @Valid
    @NotNull
    private SelfKeyGeneratorConfiguration selfKeyGenerator;

    @Valid
    @NotNull
    private KeyServiceConfiguration key;

    @Valid
    @NotNull
    private AssociationConfiguration association;

    @Valid
    @NotNull
    private InvitationServiceConfiguration invitation;

    @Valid
    //@NotNull
    private SessionServiceConfiguration session;

    @Valid
    //@NotNull
    private MailConfiguration mail;

    @Valid
    private Boolean tracing = false;

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
    public SelfKeyGeneratorConfiguration getSelfKeyGenerator() {
        return selfKeyGenerator;
    }

    @JsonProperty
    public void setSelfKeyGenerator(SelfKeyGeneratorConfiguration selfKeyGenerator) {
        this.selfKeyGenerator = selfKeyGenerator;
    }

    @JsonProperty
    public KeyServiceConfiguration getKey() {
        return key;
    }

    @JsonProperty
    public void setKey(KeyServiceConfiguration key) {
        this.key = key;
    }

    @JsonProperty
    public AssociationConfiguration getAssociation() {
        return association;
    }

    @JsonProperty
    public void setAssociation(AssociationConfiguration association) {
        this.association = association;
    }

    @JsonProperty
    public InvitationServiceConfiguration getInvitation() {
        return invitation;
    }

    @JsonProperty
    public void setInvitation(
        InvitationServiceConfiguration invitation) {
        this.invitation = invitation;
    }

    @JsonProperty
    public SessionServiceConfiguration getSession() {
        return session;
    }

    @JsonProperty
    public void setSession(SessionServiceConfiguration session) {
        this.session = session;
    }

    @JsonProperty
    public MailConfiguration getMail() {
        return mail;
    }

    @JsonProperty
    public void setMail(MailConfiguration mail) {
        this.mail = mail;
    }

    @JsonProperty
    public Boolean getTracing() {
        return tracing;
    }

    @JsonProperty
    public void setTracing(Boolean tracing) {
        this.tracing = tracing;
    }
}
