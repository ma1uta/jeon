package io.github.ma1uta.matrix.identity.model.associations;

import lombok.Getter;
import lombok.Setter;

/**
 * The sid generated for this session to the caller, in a JSON object containing the sid key.
 *
 * @author ma1uta
 */
@Getter
@Setter
public class SessionResponse {

    /**
     * New "session" on the identity service.
     */
    private String sid;
}
