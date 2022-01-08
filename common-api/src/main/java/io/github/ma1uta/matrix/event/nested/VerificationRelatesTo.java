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

package io.github.ma1uta.matrix.event.nested;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Indicates the m.key.verification.request that this message is related to. Note that for encrypted messages,
 * this property should be in the unencrypted portion of the event.
 */
@Schema(
	description = "Indicates the m.key.verification.request that this message is related to."
)
public class VerificationRelatesTo {

	/**
	 * Relation type.
	 */
	public static class RelationType {

		protected RelationType() {
		}

		/**
		 * Reference.
		 */
		public static final String M_REFERENCE = "m.reference";
	}

	/**
	 * The event ID of the m.key.verification.request that this message is related to.
	 */
	@Schema(
		description = "The event ID of the m.key.verification.request that this message is related to."
	)
	@JsonbProperty("event_id")
	private String eventId;

	/**
	 * The relationship type. One of: [m.reference].
	 */
	@Schema(
		description = "The relationship type. One of: [m.reference]."
	)
	@JsonbProperty("rel_type")
	private String relType;

	@JsonProperty("event_id")
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	@JsonProperty("rel_type")
	public String getRelType() {
		return relType;
	}

	public void setRelType(String relType) {
		this.relType = relType;
	}
}
