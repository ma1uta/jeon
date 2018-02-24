package io.github.ma1uta.matrix.identity.model.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Validation result.
 *
 * @author ma1uta
 */
@Getter
@Setter
public class ValidationResponse {

    /**
     * The literal string "email" or "msisdn".
     */
    private String medium;

    /**
     * Validation timestamp.
     */
    @JsonProperty("validated_at")
    private Long validatedAt;

    /**
     * The 3pid address of the user being validated.
     */
    private String address;
}
