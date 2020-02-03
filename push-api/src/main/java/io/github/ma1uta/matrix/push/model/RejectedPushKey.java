/*
 * Copyright Anatoliy Sablin tolya@sablin.xyz
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

package io.github.ma1uta.matrix.push.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * JSON body response for push api.
 */
@Schema(
    description = "JSON body response for push api."
)
public class RejectedPushKey {

    /**
     * A list of all pushkeys given in the notification request that are not valid. These could have been rejected by an upstream
     * gateway because they have expired or have never been valid. Homeservers must cease sending notification requests for these
     * pushkeys and remove the associated pushers. It may not necessarily be the notification in the request that failed: it could
     * be that a previous notification to the same pushkey failed.
     */
    @Schema(
        description = "A list of all pushkeys given in the notification request that are not valid. These could have been"
            + " rejected by an upstream gateway because they have expired or have never been valid. Homeservers must cease sending"
            + " notification requests for these pushkeys and remove the associated pushers. It may not necessarily be the notification"
            + " in the request that failed: it could be that a previous notification to the same pushkey failed. May be empty.",
        required = true
    )
    private List<String> rejected;

    public List<String> getRejected() {
        return rejected;
    }

    public void setRejected(List<String> rejected) {
        this.rejected = rejected;
    }
}
