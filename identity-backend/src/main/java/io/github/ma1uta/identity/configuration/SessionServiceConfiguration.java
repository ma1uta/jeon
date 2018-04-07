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
 * Configuration of the session service.
 */
public class SessionServiceConfiguration {

    /**
     * Homeserver host.
     */
    private String homeserver;

    /**
     * Subject of the email to send the token.
     */
    private String emailSubject;

    /**
     * Message of the email with token, sid and client secret.
     */
    private String emailMessageTemplate;

    public String getHomeserver() {
        return homeserver;
    }

    public void setHomeserver(String homeserver) {
        this.homeserver = homeserver;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailMessageTemplate() {
        return emailMessageTemplate;
    }

    public void setEmailMessageTemplate(String emailMessageTemplate) {
        this.emailMessageTemplate = emailMessageTemplate;
    }
}
