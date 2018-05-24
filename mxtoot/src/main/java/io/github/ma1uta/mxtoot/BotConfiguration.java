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

package io.github.ma1uta.mxtoot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.github.ma1uta.matrix.bot.Command;
import io.github.ma1uta.matrix.bot.RunState;
import io.github.ma1uta.mxtoot.matrix.MxTootConfig;
import io.github.ma1uta.mxtoot.matrix.MxTootDao;
import io.github.ma1uta.mxtoot.matrix.MxTootService;
import io.github.ma1uta.mxtoot.matrix.mastodon.MxMastodonClient;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * BotApplication configuration.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BotConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("jerseyClient")
    private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

    @NotEmpty
    private String domain;

    @NotEmpty
    @URL
    private String baseUrl;

    @NotEmpty
    private String botUserName;

    private String displayName;

    @NotEmpty
    @JsonProperty("hs_token")
    private String hsToken;

    @NotEmpty
    @JsonProperty("as_token")
    private String asToken;

    @NotEmpty
    private String url;

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    @NotNull
    private RunState runState;

    @NotNull
    private boolean strictMode = true;

    private List<Class<? extends Command<MxTootConfig, MxTootDao, MxTootService<MxTootDao>, MxMastodonClient>>> commands =
        new ArrayList<>();

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public JerseyClientConfiguration getJerseyClient() {
        return jerseyClient;
    }

    public void setJerseyClient(JerseyClientConfiguration jerseyClient) {
        this.jerseyClient = jerseyClient;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBotUserName() {
        return botUserName;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getHsToken() {
        return hsToken;
    }

    public void setHsToken(String hsToken) {
        this.hsToken = hsToken;
    }

    public String getAsToken() {
        return asToken;
    }

    public void setAsToken(String asToken) {
        this.asToken = asToken;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Class<? extends Command<MxTootConfig, MxTootDao, MxTootService<MxTootDao>, MxMastodonClient>>> getCommands() {
        return commands;
    }

    public void setCommands(List<Class<? extends Command<MxTootConfig, MxTootDao, MxTootService<MxTootDao>, MxMastodonClient>>> commands) {
        this.commands = commands;
    }

    public RunState getRunState() {
        return runState;
    }

    public void setRunState(RunState runState) {
        this.runState = runState;
    }

    public boolean isStrictMode() {
        return strictMode;
    }

    public void setStrictMode(boolean strictMode) {
        this.strictMode = strictMode;
    }
}
