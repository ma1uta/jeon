package io.github.ma1uta.matrix.server.model.bind;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Invitation.
 * <p/>
 * Sends when the association between mxid and pair medium-address is validated.
 */
@Getter
@Setter
public class Invite {

    /**
     * 'email' or 'msisdn'.
     */
    private String medium;
    /**
     * email or phone number.
     */
    private String address;
    /**
     * Owner of the 3pid.
     */
    private String mxid;
    /**
     * The room which receive this invite.
     */
    @JsonProperty("room_id")
    private String roomId;
    /**
     * Who sends this invite.
     */
    private String sender;
    /**
     * Signed part.
     */
    private Signed signed;
}
