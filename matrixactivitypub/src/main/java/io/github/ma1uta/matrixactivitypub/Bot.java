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

package io.github.ma1uta.matrixactivitypub;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;

/**
 * Matrix bot.
 */
public class Bot extends Application<BotConfiguration> {

    /**
     * Entry point.
     *
     * @param args arguments.
     * @throws Exception never throws.
     */
    public static void main(String[] args) throws Exception {
        new Bot().run(args);
    }

    @Override
    public void run(BotConfiguration botConfiguration, Environment environment) {
        matrixBot(botConfiguration, environment);
    }

    private void matrixBot(BotConfiguration botConfiguration, Environment environment) {
        environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        Client jersey = new JerseyClientBuilder(environment).using(botConfiguration.getJerseyClient()).build("jersey");

        MatrixBot botClient = new MatrixBot(jersey, botConfiguration.getBaseUrl(), botConfiguration.getBotUserName(),
            botConfiguration.getPassword(), botConfiguration.getDisplayName());
        environment.lifecycle().manage(new MatrixEndPoint(botClient));

        botClient.run();
    }
}
