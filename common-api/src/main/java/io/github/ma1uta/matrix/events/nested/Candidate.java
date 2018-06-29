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

package io.github.ma1uta.matrix.events.nested;

/**
 * Candidate.
 */
public class Candidate {

    /**
     * Required. The SDP media type this candidate is intended for.
     */
    private String sdpMid;

    /**
     * Required. The index of the SDP 'm' line this candidate is intended for.
     */
    private Long sdpMLineIndex;

    /**
     * Required. The SDP 'a' line of the candidate.
     */
    private String candidate;

    public String getSdpMid() {
        return sdpMid;
    }

    public void setSdpMid(String sdpMid) {
        this.sdpMid = sdpMid;
    }

    public Long getSdpMLineIndex() {
        return sdpMLineIndex;
    }

    public void setSdpMLineIndex(Long sdpMLineIndex) {
        this.sdpMLineIndex = sdpMLineIndex;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }
}
