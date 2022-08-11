package io.github.ma1uta.matrix.client.model.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Refresh token request.
 */
@Schema(
    description = "Refresh token request"
)
public class RefreshTokenRequest {

    /**
     * Refresh token.
     */
    @Schema(
        description = "Refresh token.",
        name = "refresh_token",
        required = true
    )
    @JsonbProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refresh_token) {
        this.refreshToken = refresh_token;
    }
}
