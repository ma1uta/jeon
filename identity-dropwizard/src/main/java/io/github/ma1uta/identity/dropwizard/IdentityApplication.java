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
import io.github.ma1uta.identity.dropwizard.service.SimpleKeyService;
import io.github.ma1uta.identity.key.SelfCertificateKeyGenerator;
import io.github.ma1uta.identity.service.AssociationService;
import io.github.ma1uta.identity.service.InvitationService;
import io.github.ma1uta.identity.service.KeyService;
import io.github.ma1uta.identity.service.SessionService;
import io.github.ma1uta.identity.service.impl.AbstractAssociationService;
import io.github.ma1uta.identity.service.impl.AbstractInvitationService;
import io.github.ma1uta.identity.service.impl.AbstractSessionService;
import io.github.ma1uta.jeon.exception.ExceptionHandler;
import org.jdbi.v3.core.Jdbi;

public class IdentityApplication extends Application<IdentityConfiguration> {

    private AssociationService associationService;
    private InvitationService invitationService;
    private KeyService keyService;
    private SessionService sessionService;
    private Jdbi jdbi;

    public static void main(String[] args) throws Exception {
        new IdentityApplication().run(args);
    }

    @Override
    public void run(IdentityConfiguration configuration, Environment environment) throws Exception {
        initializeDataSource(configuration, environment);
        initializeServices(configuration);
        registerResourses(configuration, environment);
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

    private void initializeServices(IdentityConfiguration configuration) {
        this.keyService = new SimpleKeyService(new SelfCertificateKeyGenerator(configuration.getSelfKeyGeneratorConfiguration()),
            configuration.getKeyServiceConfiguration());
        this.keyService.init();
        this.associationService = new AbstractAssociationService(null, this.keyService, configuration.getAssociationConfiguration(), null);
        this.invitationService = new AbstractInvitationService(this.associationService, this.keyService, null, null,
            configuration.getInvitationServiceConfiguration(), null);
        this.sessionService = new AbstractSessionService(null, null, this.associationService, this.invitationService,
            configuration.getSessionServiceConfiguration());
    }

    private void registerResourses(IdentityConfiguration configuration, Environment environment) {
        environment.jersey().register(new ExceptionHandler());

        environment.jersey().register(new Invitation(this.invitationService));
        environment.jersey().register(new KeyManagement(this.keyService));
        environment.jersey().register(new Lookup(this.associationService));
        environment.jersey().register(new Session(this.sessionService));
        environment.jersey().register(new Status());
        environment.jersey().register(new Validation(this.sessionService));
    }
}
