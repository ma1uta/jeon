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

package io.github.ma1uta.matrix.client.model.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * JSON body response.
 */
@ApiModel(description = "JSON body request.")
public class PublicRoomsResponse {

    /**
     * Required. A paginated chunk of public rooms.
     */
    @ApiModelProperty(value = "A paginated chunk of public rooms.", required = true)
    private List<PublicRoomsChunk> chunk;

    /**
     * A pagination token for the response. The absence of this token means there are no more results to fetch and the client
     * should stop paginating.
     */
    @ApiModelProperty(name = "next_batch", value = "A pagination token for the response. The absence of this token means there "
        + "are no more results to fetch and the client should stop paginating.")
    @JsonProperty("next_batch")
    private String nextBatch;

    /**
     * A pagination token that allows fetching previous results. The absence of this token means there are no results before
     * this batch, i.e. this is the first batch.
     */
    @ApiModelProperty(name = "prev_batch", value = "A pagination token that allows fetching previous results. The absence of "
        + "this token means there are no results before this batch, i.e. this is the first batch.")
    @JsonProperty("prev_batch")
    private String prevBatch;

    /**
     * An estimate on the total number of public rooms, if the server has an estimate.
     */
    @ApiModelProperty(name = "total_room_count_estimate", value = "An estimate on the total number of public rooms, if the server "
        + "has an estimate.")
    @JsonProperty("total_room_count_estimate")
    private Long totalRoomCountEstimate;

    public List<PublicRoomsChunk> getChunk() {
        return chunk;
    }

    public void setChunk(List<PublicRoomsChunk> chunk) {
        this.chunk = chunk;
    }

    public String getNextBatch() {
        return nextBatch;
    }

    public void setNextBatch(String nextBatch) {
        this.nextBatch = nextBatch;
    }

    public String getPrevBatch() {
        return prevBatch;
    }

    public void setPrevBatch(String prevBatch) {
        this.prevBatch = prevBatch;
    }

    public Long getTotalRoomCountEstimate() {
        return totalRoomCountEstimate;
    }

    public void setTotalRoomCountEstimate(Long totalRoomCountEstimate) {
        this.totalRoomCountEstimate = totalRoomCountEstimate;
    }
}
