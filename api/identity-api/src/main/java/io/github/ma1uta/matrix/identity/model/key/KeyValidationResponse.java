package io.github.ma1uta.matrix.identity.model.key;

import lombok.Getter;
import lombok.Setter;

/**
 * The validity of the public key.
 *
 * @author ma1uta
 */
@Getter
@Setter
public class KeyValidationResponse {

    /**
     * {@code true} if key is valid, else {@code false}.
     */
    private Boolean valid;
}
