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

package io.github.ma1uta.matrix.client.model.push;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * JSON body response for push notifications.
 */
@ApiModel(description = "JSON body response for push notifications.")
public class NotificationResponse {

    /**
     * The token to supply in the from param of the next /notifications request in order to request more events.
     * If this is absent, there are no more results.
     */
    @ApiModelProperty(name = "next_token", value = "The token to supply in the from param of the next /notifications request in "
        + "order to request more events. If this is absent, there are no more results.")
    @JsonProperty("next_token")
    private String nextToken;

    /**
     * Required. The list of events that triggered notifications.
     */
    @ApiModelProperty(value = "The list of events that triggered notifications.", required = true)
    private List<Notification> notifications;

    public String getNextToken() {
        return nextToken;
    }

    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
