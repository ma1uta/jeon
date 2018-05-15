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

package io.github.ma1uta.macpub;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.sslreload.SslReloadBundle;
import io.github.ma1uta.jeon.exception.ExceptionHandler;
import io.github.ma1uta.macpub.matrix.AppResource;
import io.github.ma1uta.macpub.matrix.BotConfig;
import io.github.ma1uta.macpub.matrix.BotDao;
import io.github.ma1uta.macpub.matrix.BotService;
import io.github.ma1uta.macpub.matrix.Transaction;
import io.github.ma1uta.macpub.matrix.TransactionDao;

import javax.ws.rs.client.Client;

/**
 * Matrix bot.
 */
public class BotApplication extends Application<BotConfiguration> {

    private HibernateBundle<BotConfiguration> matrixHibernate = new HibernateBundle<BotConfiguration>(BotConfig.class, Transaction.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(BotConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    /**
     * Entry point.
     *
     * @param args arguments.
     * @throws Exception never throws.
     */
    public static void main(String[] args) throws Exception {
        new BotApplication().run(args);
    }

    @Override
    public void run(BotConfiguration botConfiguration, Environment environment) {
        matrixBot(botConfiguration, environment);
    }

    @Override
    public void initialize(Bootstrap<BotConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
            new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));
        bootstrap.addBundle(new SslReloadBundle());

        bootstrap.getObjectMapper().enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);

        bootstrap.addBundle(matrixHibernate);
    }

    private void matrixBot(BotConfiguration botConfiguration, Environment environment) {
        environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        Client jersey = new JerseyClientBuilder(environment).using(botConfiguration.getJerseyClient()).build("jersey");

        UnitOfWorkAwareProxyFactory proxyFactory = new UnitOfWorkAwareProxyFactory(matrixHibernate);
        BotDao botDao = new BotDao(matrixHibernate.getSessionFactory());
        TransactionDao transactionDao = new TransactionDao(matrixHibernate.getSessionFactory());

        BotService botService = proxyFactory.create(BotService.class,
            new Class<?>[] {String.class, String.class, String.class, Client.class, String.class, BotDao.class,
                UnitOfWorkAwareProxyFactory.class},
            new Object[] {botConfiguration.getBaseUrl(), botConfiguration.getDomain(), botConfiguration.getDisplayName(), jersey,
                botConfiguration.getAsToken(), botDao, proxyFactory});

        environment.lifecycle().manage(botService);
        environment.jersey()
            .register(new AppResource(botDao, transactionDao, botService, botConfiguration.getHsToken(), botConfiguration.getUrl()));
        environment.jersey().register(new ExceptionHandler());
    }
}
