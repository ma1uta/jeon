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

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * A list of terms URLs the user has previously accepted.
 * Clients SHOULD use this to avoid presenting the user with terms they have already agreed to.
 */
@Schema(
    description = "A list of terms URLs the user has previously accepted. "
        + "Clients SHOULD use this to avoid presenting the user with terms they have already agreed to."
)
public class AcceptedTermsContent implements EventContent {

    /**
     * The list of URLs the user has previously accepted. Should be appended to when the user agrees to new terms.
     */
    @Schema(
        description = " The list of URLs the user has previously accepted. Should be appended to when the user agrees to new terms."
    )
    private List<String> accepted;

    public List<String> getAccepted() {
        return accepted;
    }

    public void setAccepted(List<String> accepted) {
        this.accepted = accepted;
    }
}
