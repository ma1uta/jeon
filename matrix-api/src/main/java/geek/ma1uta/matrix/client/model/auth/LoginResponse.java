package geek.ma1uta.matrix.client.model.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * Response for the login request.
 *
 * @author ma1uta
 */
@Getter
@Setter
public class LoginResponse {

    /**
     * The fully-qualified Matrix ID that has been registered.
     */
    private String userId;

    /**
     * An access token for the account. This access token can then be used to authorize other requests.
     */
    private String accessToken;

    /**
     * The hostname of the homeserver on which the account has been registered.
     */
    private String homeServer;

    /**
     * ID of the logged-in device. Will be the same as the corresponding parameter in the request, if one was specified.
     */
    private String deviceId;
}
