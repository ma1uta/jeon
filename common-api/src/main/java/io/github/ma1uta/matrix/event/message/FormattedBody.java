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

package io.github.ma1uta.matrix.event.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.event.content.RoomMessageContent;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * This message is the most basic message and is used to represent text.
 */
@Schema(
    description = "This message is the most basic message and is used to represent text."
)
public abstract class FormattedBody extends RoomMessageContent {

    /**
     * Format.
     */
    public static class Format {

        protected Format() {

        }

        /**
         * "org.matrix.custom.html".
         */
        public static final String ORG_MATRIX_CUSTOM_HTML = "org.matrix.custom.html";
    }

    /**
     * The format used in the ``formatted_body``. Currently only ``org.matrix.custom.html`` is supported.
     */
    @Schema(
        description = "The format used in the ``formatted_body``. Currently only ``org.matrix.custom.html`` is supported."
    )
    private String format;

    /**
     * The formatted version of the ``body``. This is required if ``format`` is specified.
     */
    @Schema(
        name = "formatted_body",
        description = "The formatted version of the ``body``. This is required if ``format`` is specified.")
    @JsonbProperty("formatted_body")
    private String formattedBody;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @JsonProperty("formatted_body")
    public String getFormattedBody() {
        return formattedBody;
    }

    public void setFormattedBody(String formattedBody) {
        this.formattedBody = formattedBody;
    }
}
