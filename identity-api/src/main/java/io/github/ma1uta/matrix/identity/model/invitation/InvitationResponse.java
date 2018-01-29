package io.github.ma1uta.matrix.identity.model.invitation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Result of the pending invitation.
 *
 * @author ma1uta
 */
@Getter
@Setter
public class InvitationResponse {

    /**
     * The generated token.
     */
    private String token;

    /**
     * A list of [server's long-term public key, generated ephemeral public key].
     */
    @JsonProperty("public_keys")
    private List<String> publicKeys;

    /**
     * The generated display_name, which is a redacted version of address which does not leak the full contents of the address.
     */
    @JsonProperty("display_name")
    private String displayName;
}
