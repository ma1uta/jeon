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

package io.github.ma1uta.matrix.identity.model.lookup;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Look up response the Matrix user ID for a 3pid.
 *
 * @author ma1uta
 */
public class LookupResponse {

    /**
     * The 3pid address of the user being looked up.
     */
    private String address;

    /**
     * The literal string "email" or "msisdn".
     */
    private String medium;

    /**
     * The Matrix user ID associated with the 3pid.
     */
    private String mxid;

    /**
     * A unix timestamp before which the association is not known to be valid.
     */
    @JsonProperty("not_before")
    private Long notBefore;

    /**
     * A unix timestamp after which the association is not known to be valid.
     */
    @JsonProperty("not_after")
    private Long notAfter;

    /**
     * The unix timestamp at which the association was verified.
     */
    private Long ts;

    /**
     * The signatures of the verifying identity services which show that the association should be trusted,
     * if you trust the verifying identity services.
     */
    private Map<String, Map<String, String>> signatures;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getMxid() {
        return mxid;
    }

    public void setMxid(String mxid) {
        this.mxid = mxid;
    }

    public Long getNotBefore() {
        return notBefore;
    }

    public void setNotBefore(Long notBefore) {
        this.notBefore = notBefore;
    }

    public Long getNotAfter() {
        return notAfter;
    }

    public void setNotAfter(Long notAfter) {
        this.notAfter = notAfter;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public Map<String, Map<String, String>> getSignatures() {
        return signatures;
    }

    public void setSignatures(Map<String, Map<String, String>> signatures) {
        this.signatures = signatures;
    }
}
