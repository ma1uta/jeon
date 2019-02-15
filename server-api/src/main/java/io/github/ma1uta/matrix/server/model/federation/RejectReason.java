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

package io.github.ma1uta.matrix.server.model.federation;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Reject reason.
 */
@Schema(
    description = "Reject reason."
)
public class RejectReason {

    /**
     * Reasons.
     */
    public static class Reasons {
        private Reasons() {
            //singleton
        }

        /**
         * Auth error.
         */
        public static final String AUTH_ERROR = "auth_error";

        /**
         * Replaced.
         */
        public static final String REPLACED = "replaced";

        /**
         * Not ancestor.
         */
        public static final String NOT_ANCESTOR = "not_ancestor";
    }

    /**
     * Required. The reason for the event being rejected. One of: ["auth_error", "replaced", "not_ancestor"]
     */
    @Schema(
        description = "The reason for the event being rejected.",
        required = true,
        allowableValues = {"auth_error", "replaced", "not_ancestor"}
    )
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
