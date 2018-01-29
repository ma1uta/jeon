package io.github.ma1uta.matrix.identity.model.associations;

import lombok.Getter;
import lombok.Setter;

/**
 * Result of the 3pid validation.
 *
 * @author ma1uta
 */
@Getter
@Setter
public class ValidationResponse {

    /**
     * REsult of the 3pid validation..
     */
    private Boolean validated;
}
