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

package io.github.ma1uta.mxtoot.matrix;

import io.github.ma1uta.matrix.bot.BotConfig;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Matrix bot persistent configuration.
 */
@Entity
@Table(name = "botconfig")
@NamedQueries( {@NamedQuery(name = "matrix.bot.findAll", query = "select d from MxTootConfig d"),
    @NamedQuery(name = "matrix.bot.findByUserId", query = "select d from MxTootConfig d where d.userId = :userId")})
public class MxTootConfig extends BotConfig {

    /**
     * Mastodon server.
     */
    private String mastodonServer;

    /**
     * Mastodon username.
     */
    private String mastodonUsername;

    /**
     * Mastodon client id.
     */
    private String mastodonClientId;

    /**
     * Mastodon client secret.
     */
    private String mastodonClientSecret;

    /**
     * Mastodon access token.
     */
    private String mastodonAccessToken;

    /**
     * Mastodon client state.
     */
    private MastodonClientState mastodonClientState;

    public String getMastodonServer() {
        return mastodonServer;
    }

    public void setMastodonServer(String mastodonServer) {
        this.mastodonServer = mastodonServer;
    }

    public String getMastodonUsername() {
        return mastodonUsername;
    }

    public void setMastodonUsername(String mastodonUsername) {
        this.mastodonUsername = mastodonUsername;
    }

    public String getMastodonClientId() {
        return mastodonClientId;
    }

    public void setMastodonClientId(String mastodonClientId) {
        this.mastodonClientId = mastodonClientId;
    }

    public String getMastodonClientSecret() {
        return mastodonClientSecret;
    }

    public void setMastodonClientSecret(String mastodonClientSecret) {
        this.mastodonClientSecret = mastodonClientSecret;
    }

    public String getMastodonAccessToken() {
        return mastodonAccessToken;
    }

    public void setMastodonAccessToken(String mastodonAccessToken) {
        this.mastodonAccessToken = mastodonAccessToken;
    }

    public MastodonClientState getMastodonClientState() {
        return mastodonClientState;
    }

    public void setMastodonClientState(MastodonClientState mastodonClientState) {
        this.mastodonClientState = mastodonClientState;
    }
}
