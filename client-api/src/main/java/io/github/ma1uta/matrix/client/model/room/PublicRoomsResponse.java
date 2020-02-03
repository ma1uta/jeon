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

package io.github.ma1uta.matrix.client.model.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * JSON body response.
 */
@Schema(
    description = "JSON body request."
)
public class PublicRoomsResponse {

    /**
     * Required. A paginated chunk of public rooms.
     */
    @Schema(
        description = "A paginated chunk of public rooms."
    )
    private List<PublicRoomsChunk> chunk;

    /**
     * A pagination token for the response. The absence of this token means there are no more results to fetch and the client
     * should stop paginating.
     */
    @Schema(
        description = "A pagination token for the response. The absence of this token means there "
            + "are no more results to fetch and the client should stop paginating."
    )
    @JsonbProperty("next_batch")
    private String nextBatch;

    /**
     * A pagination token that allows fetching previous results. The absence of this token means there are no results before
     * this batch, i.e. this is the first batch.
     */
    @Schema(
        description = "A pagination token that allows fetching previous results. The absence of "
            + "this token means there are no results before this batch, i.e. this is the first batch."
    )
    @JsonbProperty("prev_batch")
    private String prevBatch;

    /**
     * An estimate on the total number of public rooms, if the server has an estimate.
     */
    @Schema(
        description = "An estimate on the total number of public rooms, if the server has an estimate."
    )
    @JsonbProperty("total_room_count_estimate")
    private Long totalRoomCountEstimate;

    public List<PublicRoomsChunk> getChunk() {
        return chunk;
    }

    public void setChunk(List<PublicRoomsChunk> chunk) {
        this.chunk = chunk;
    }

    @JsonProperty("next_batch")
    public String getNextBatch() {
        return nextBatch;
    }

    public void setNextBatch(String nextBatch) {
        this.nextBatch = nextBatch;
    }

    @JsonProperty("prev_batch")
    public String getPrevBatch() {
        return prevBatch;
    }

    public void setPrevBatch(String prevBatch) {
        this.prevBatch = prevBatch;
    }

    @JsonProperty("total_room_count_estimate")
    public Long getTotalRoomCountEstimate() {
        return totalRoomCountEstimate;
    }

    public void setTotalRoomCountEstimate(Long totalRoomCountEstimate) {
        this.totalRoomCountEstimate = totalRoomCountEstimate;
    }
}
