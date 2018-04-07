package io.github.ma1uta.matrix.server.model.bind;

import java.util.List;

/**
 * If the owner of that particular 3pid binds it with a Matrix user ID, the identity server will attempt to make an HTTP
 * POST to the Matrix user's homeserver this request.
 */
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

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMxid() {
        return mxid;
    }

    public void setMxid(String mxid) {
        this.mxid = mxid;
    }

    public List<Invite> getInvites() {
        return invites;
    }

    public void setInvites(List<Invite> invites) {
        this.invites = invites;
    }
}
