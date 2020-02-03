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

package io.github.ma1uta.matrix.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Protocol metadata.
 */
@Schema(
    description = "Protocol metadata."
)
public class Protocol {

    /**
     * Required. Fields used to identify a third party user.
     */
    @Schema(
        name = "user_fields",
        description = "Fields used to identify a third party user.",
        required = true
    )
    @JsonbProperty("user_fields")
    private List<String> userFields;

    /**
     * Required. Fields used to identify a third party location.
     */
    @Schema(
        name = "location_fields",
        description = "Fields used to identify a third party location.",
        required = true
    )
    @JsonbProperty("location_fields")
    private List<String> locationFields;

    /**
     * Required. An icon representing the third party protocol.
     */
    @Schema(
        description = "An icon representing the third party protocol.",
        required = true
    )
    private String icon;

    /**
     * Required. All location or user fields should have an entry here.
     */
    @Schema(
        name = "field_types",
        description = "All location or user fields should have an entry here.",
        required = true
    )
    @JsonbProperty("field_types")
    private Map<String, FieldMetadata> fieldTypes;

    /**
     * Required. A list of objects representing independent instances of configuration.
     * For instance multiple networks on IRC if multiple are bridged by the same bridge.
     */
    @Schema(
        description = "A list of objects representing independent instances of configuration. For instance multiple networks"
            + "on IRC if multiple are bridged by the same bridge.",
        required = true
    )
    private List<Instance> instances;

    @JsonProperty("user_fields")
    public List<String> getUserFields() {
        return userFields;
    }

    public void setUserFields(List<String> userFields) {
        this.userFields = userFields;
    }

    @JsonProperty("location_fields")
    public List<String> getLocationFields() {
        return locationFields;
    }

    public void setLocationFields(List<String> locationFields) {
        this.locationFields = locationFields;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @JsonProperty("field_types")
    public Map<String, FieldMetadata> getFieldTypes() {
        return fieldTypes;
    }

    public void setFieldTypes(Map<String, FieldMetadata> fieldTypes) {
        this.fieldTypes = fieldTypes;
    }

    public List<Instance> getInstances() {
        return instances;
    }

    public void setInstances(List<Instance> instances) {
        this.instances = instances;
    }
}
