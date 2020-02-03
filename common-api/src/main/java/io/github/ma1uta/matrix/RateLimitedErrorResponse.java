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

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Rate limited error response.
 */
public class RateLimitedErrorResponse extends ErrorResponse {

    /**
     * Time to retry send a request.
     */
    @JsonbProperty("retry_after_ms")
    private Long retryAfterMs;

    public RateLimitedErrorResponse() {
    }

    public RateLimitedErrorResponse(String errcode, String error) {
        super(errcode, error);
    }

    public RateLimitedErrorResponse(String errcode, String error, Long retryAfterMs) {
        super(errcode, error);
        this.retryAfterMs = retryAfterMs;
    }

    @JsonProperty("retry_after_ms")
    public Long getRetryAfterMs() {
        return retryAfterMs;
    }

    public void setRetryAfterMs(Long retryAfterMs) {
        this.retryAfterMs = retryAfterMs;
    }
}
