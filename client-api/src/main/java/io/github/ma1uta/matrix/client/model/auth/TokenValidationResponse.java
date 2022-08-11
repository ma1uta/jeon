package io.github.ma1uta.matrix.client.model.auth;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Token validation response.
 */
@Schema(
    description = "Token validation response"
)
public class TokenValidationResponse {

    /**
     * True if the token is still valid, false otherwise.
     * This should additionally be false if the token is not a recognised token by the server.
     */
    @Schema(
        description = "True if the token is still valid, false otherwise."
            + " This should additionally be false if the token is not a recognised token by the server."
    )
    private Boolean valid;

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
