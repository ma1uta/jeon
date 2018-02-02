package io.github.ma1uta.matrix.client.model.auth;

import io.github.ma1uta.matrix.client.model.account.AuthenticationData;
import lombok.Getter;
import lombok.Setter;

/**
 * In the REST API described in this specification, authentication works by the client and server exchanging JSON dictionaries.
 * The server indicates what authentication data it requires via the body of an HTTP 401 response, and the client submits that
 * authentication data via the auth request parameter.
 * <p/>
 * Indicate that this object need user interactive session.
 *
 * @author ma1uta
 */
@Getter
@Setter
public abstract class UserInteractiveData {

    /**
     * Additional authentication information for the user-interactive authentication API.
     * Note that this information is not used to define how the registered user should be authenticated,
     * but is instead used to authenticate the register call itself.
     * It should be left empty, or omitted, unless an earlier call returned an response with status code 401.
     */
    private AuthenticationData auth;
}
