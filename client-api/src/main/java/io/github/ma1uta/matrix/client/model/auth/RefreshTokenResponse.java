package io.github.ma1uta.matrix.client.model.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Refresh token response.
 */
@Schema(
    description = "Refresh token response"
)
public class RefreshTokenResponse {

    /**
     * Access token.
     */
    @Schema(
        name = "access_token",
        description = "Access token",
        required = true
    )
    @JsonbProperty("access_token")
    private String accessToken;

    /**
     * Expires in ms.
     */
    @Schema(
        description = "Expires in ms",
        name = "expires_in_ms",
        required = true
    )
    @JsonbProperty("expires_in_ms")
    private Long expiresInMs;

    /**
     * Refresh token.
     */
    @Schema(
        name = "refresh_token",
        description = "Refresh token.",
        required = true
    )
    @JsonbProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @JsonProperty("expires_in_ms")
    public Long getExpiresInMs() {
        return expiresInMs;
    }

    public void setExpiresInMs(Long expiresInMs) {
        this.expiresInMs = expiresInMs;
    }

    @JsonProperty("refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
