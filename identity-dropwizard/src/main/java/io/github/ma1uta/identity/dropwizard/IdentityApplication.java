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

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.jdbi3.bundles.JdbiExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.sslreload.SslReloadBundle;
import io.github.ma1uta.identity.api.Invitation;
import io.github.ma1uta.identity.api.KeyManagement;
import io.github.ma1uta.identity.api.Lookup;
import io.github.ma1uta.identity.api.Session;
import io.github.ma1uta.identity.api.Status;
import io.github.ma1uta.identity.api.Validation;
import io.github.ma1uta.identity.dropwizard.service.AssociationJdbiService;
import io.github.ma1uta.identity.dropwizard.service.InvitationJdbiService;
import io.github.ma1uta.identity.dropwizard.service.JacksonSerializer;
import io.github.ma1uta.identity.dropwizard.service.MailService;
import io.github.ma1uta.identity.dropwizard.service.RestJerseyService;
import io.github.ma1uta.identity.dropwizard.service.SessionJdbiService;
import io.github.ma1uta.identity.dropwizard.service.SimpleKeyService;
import io.github.ma1uta.identity.dropwizard.task.PrepareKeyStores;
import io.github.ma1uta.identity.key.LongTermKeyProvider;
import io.github.ma1uta.identity.key.SelfCertificateKeyGenerator;
import io.github.ma1uta.identity.key.ShortTermKeyProvider;
import io.github.ma1uta.identity.service.AssociationService;
import io.github.ma1uta.identity.service.InvitationService;
import io.github.ma1uta.identity.service.KeyService;
import io.github.ma1uta.identity.service.RestService;
import io.github.ma1uta.identity.service.SerializerService;
import io.github.ma1uta.identity.service.SessionService;
import io.github.ma1uta.jeon.exception.ExceptionHandler;
import net.i2p.crypto.eddsa.EdDSASecurityProvider;
import org.jdbi.v3.core.Jdbi;

import java.security.Security;
import javax.ws.rs.client.ClientBuilder;

/**
 * Identity main class.
 */
public class IdentityApplication extends Application<IdentityConfiguration> {

    private AssociationService associationService;
    private InvitationService invitationService;
    private KeyService keyService;
    private SessionService sessionService;
    private Jdbi jdbi;
    private SerializerService serializerService;

    /**
     * Entry point.
     *
     * @param args console arguments.
     * @throws Exception if something wrong.
     */
    public static void main(String[] args) throws Exception {
        new IdentityApplication().run(args);
    }

    @Override
    public void run(IdentityConfiguration configuration, Environment environment) {
        Security.addProvider(new EdDSASecurityProvider());

        initializeDataSource(configuration, environment);
        initializeServices(configuration, environment);
        registerResourses(configuration, environment);

        environment.admin().addTask(new PrepareKeyStores("preparekeys", this.keyService));
    }

    @Override
    public void initialize(Bootstrap<IdentityConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
            new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));
        bootstrap.addBundle(new SslReloadBundle());
        bootstrap.addBundle(new JdbiExceptionsBundle());

        bootstrap.getObjectMapper().enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    private void initializeDataSource(IdentityConfiguration configuration, Environment environment) {
        this.jdbi = new JdbiFactory().build(environment, configuration.getDatabase(), "postgresql");
    }

    private void initializeServices(IdentityConfiguration configuration, Environment environment) {
        SelfCertificateKeyGenerator keyGenerator = new SelfCertificateKeyGenerator(
            configuration.getSelfKeyGenerator());
        this.keyService = new SimpleKeyService(
            new LongTermKeyProvider(configuration.getKey().getLongTerm(), configuration.getSecureRandomSeed(), keyGenerator),
            new ShortTermKeyProvider(configuration.getKey().getUsedShortTerm(), configuration.getSecureRandomSeed(), keyGenerator),
            configuration.getKey());
        this.keyService.init();

        this.serializerService = new JacksonSerializer(environment.getObjectMapper());
        this.associationService = new AssociationJdbiService(this.jdbi, configuration.getAssociation());

        RestService restService = new RestJerseyService(ClientBuilder.newClient());
        this.invitationService = new InvitationJdbiService(this.associationService, this.keyService, serializerService,
            configuration.getInvitation(), restService, this.jdbi);

        this.sessionService = new SessionJdbiService(new MailService(configuration.getMail()), this.associationService,
            this.invitationService, configuration.getSession(), this.jdbi);
    }

    private void registerResourses(IdentityConfiguration configuration, Environment environment) {
        environment.jersey().register(new ExceptionHandler());

        environment.jersey().register(new Status());
        environment.jersey().register(new Invitation(this.invitationService));
        environment.jersey().register(new KeyManagement(this.keyService));
        environment.jersey().register(new Lookup(this.associationService, keyService, serializerService));
        environment.jersey().register(new Session(this.sessionService));
        environment.jersey().register(new Validation(this.sessionService));

        environment.getObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        if (configuration.getTracing()) {
            environment.jersey().property("jersey.config.server.tracing.type", "ALL");
            environment.jersey().property("jersey.config.server.tracing.threshold", "VERBOSE");
        }
    }
}
