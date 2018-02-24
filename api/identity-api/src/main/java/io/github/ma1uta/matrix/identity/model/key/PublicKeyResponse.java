package io.github.ma1uta.matrix.identity.model.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * The public key exists.
 *
 * @author ma1uta
 */
@Getter
@Setter
public class PublicKeyResponse {

    /**
     * Public key.
     */
    @JsonProperty("public_key")
    private String publicKey;
}
