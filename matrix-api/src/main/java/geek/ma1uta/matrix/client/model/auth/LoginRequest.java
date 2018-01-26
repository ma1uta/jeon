package geek.ma1uta.matrix.client.model.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * Authenticates the user, and issues an access token they can use to authorize themself in subsequent requests.
 *
 * @author ma1uta
 */
@Getter
@Setter
public class LoginRequest {

    /**
     * Required. The login type being used. One of: ["m.login.password", "m.login.token"].
     */
    private String type;

    /**
     * The fully qualified user ID or just local part of the user ID, to log in.
     */
    private String user;

    /**
     * When logging in using a third party identifier, the medium of the identifier. Must be 'email'.
     */
    private String medium;

    /**
     * Third party identifier for the user.
     */
    private String address;

    /**
     * Required when type is m.login.password. The user's password.
     */
    private CharSequence password;

    /**
     * Required when type is m.login.token. The login token.
     */
    private String token;

    /**
     * ID of the client device. If this does not correspond to a known client device, a new device will be created.
     * The server will auto-generate a device_id if this is not specified.
     */
    private String deviceId;

    /**
     * A display name to assign to the newly-created device. Ignored if device_id corresponds to a known device.
     */
    private String initialDeviceDisplayName;
}
