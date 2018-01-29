package io.github.ma1uta.matrix.identity.model.lookup;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Look up response the Matrix user ID for a 3pid.
 *
 * @author ma1uta
 */
@Getter
@Setter
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
}
