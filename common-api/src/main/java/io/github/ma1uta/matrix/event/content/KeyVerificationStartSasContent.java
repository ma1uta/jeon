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

package io.github.ma1uta.matrix.event.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.event.nested.VerificationRelatesTo;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

/**
 * Begins a SAS key verification process using the m.sas.v1 method.
 */
@Schema(
    description = "Begins a SAS key verification process using the m.sas.v1 method."
)
public class KeyVerificationStartSasContent extends KeyVerificationStartContent {

    /**
     * Required when sent as an in-room message.
     * Indicates the m.key.verification.request that this message is related to.
     * Note that for encrypted messages, this property should be in the unencrypted portion of the event.
     */
    @Schema(
        name = "m.relates_to",
        description = "Required when sent as an in-room message." +
            " Indicates the m.key.verification.request that this message is related to." +
            " Note that for encrypted messages, this property should be in the unencrypted portion of the event."
    )
    @JsonbProperty("m.relates_to")
    private VerificationRelatesTo relatesTo;

    @JsonProperty("m.relates_to")
    public VerificationRelatesTo getRelatesTo() {
        return relatesTo;
    }

    public void setRelatesTo(VerificationRelatesTo relatesTo) {
        this.relatesTo = relatesTo;
    }
}
