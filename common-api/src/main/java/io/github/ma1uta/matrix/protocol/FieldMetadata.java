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

package io.github.ma1uta.matrix.protocol;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Definition of valid values for a field.
 */
@ApiModel(description = "Definition of valid values for a field.")
public class FieldMetadata {

    /**
     * Required. A regular expression for validation of a field's value.
     */
    @ApiModelProperty(
        value = "A regular expression for validation of a field's value.",
        required = true
    )
    private String regexp;

    /**
     * Required. An placeholder serving as a valid example of the field value.
     */
    @ApiModelProperty(
        value = "An placeholder serving as a valid example of the field value.",
        required = true
    )
    private String placeholder;

    public String getRegexp() {
        return regexp;
    }

    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
}
