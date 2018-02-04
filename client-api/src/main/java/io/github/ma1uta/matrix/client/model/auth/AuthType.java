package io.github.ma1uta.matrix.client.model.auth;

/**
 * Authentication types.
 * <p/>
 * <a href="https://matrix.org/docs/spec/client_server/unstable.html#id100>See more</a>.
 *
 * @author ma1uta
 */
public interface AuthType {

    /**
     * The client submits a username and secret password, both sent in plain-text.
     */
    String PASSWORD = "m.login.password";

    /**
     * The user completes a Google ReCaptcha 2.0 challenge.
     */
    String RECAPTCHA = "m.login.recaptcha";

    /**
     * Authentication is supported via OAuth2 URLs. This login consists of multiple requests.
     */
    String OAUTH2 = "m.login.oauth2";

    /**
     * Authentication is supported by authorising an email address with an identity server.
     */
    String EMAIL_IDENTITY = "m.login.email.identity";

    /**
     * The client submits a login token.
     */
    String TOKEN = "m.login.token";

    /**
     * Dummy authentication always succeeds and requires no extra parameters. Its purpose is to allow servers to not require any
     * form of User-Interactive Authentication to perform a request.
     */
    String DUMMY = "m.login.dummy";
}
