package io.github.ma1uta.matrix.server.model.bind;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * If the owner of that particular 3pid binds it with a Matrix user ID, the identity server will attempt to make an HTTP
 * POST to the Matrix user's homeserver this request.
 */
@Getter
@Setter
public class OnBindRequest {

    /**
     * 'email' or 'msisdn'.
     */
    private String medium;
    /**
     * email or phone number.
     */
    private String address;
    /**
     * Owner of the 3pid binds.
     */
    private String mxid;
    /**
     * Invites for this mxid.
     */
    private List<Invite> invites;
}
