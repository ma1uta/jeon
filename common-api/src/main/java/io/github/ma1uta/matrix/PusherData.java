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

package io.github.ma1uta.matrix;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Pusher data.
 */
@Schema(
    description = "Pusher data."
)
public class PusherData {

    /**
     * Required if kind is http. The URL to use to send notifications to.
     */
    @Schema(
        description = "Required if kind is http. The URL to use to send notifications to."
    )
    private String url;

    /**
     * The format to use when sending notifications to the Push Gateway.
     */
    @Schema(
        description = "The format to use when sending notifications to the Push Gateway."
    )
    private String format;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
