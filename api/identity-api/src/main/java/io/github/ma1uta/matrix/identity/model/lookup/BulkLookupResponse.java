package io.github.ma1uta.matrix.identity.model.lookup;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Request of the lookup Matrix user IDs for a list of 3pids.
 *
 * @author ma1uta
 */
@Getter
@Setter
public class BulkLookupResponse {

    /**
     * Required. an array of array containing the 3PID Types with the medium in first position, the address in second position
     * and Matrix ID in third position.
     */
    private List<List<String>> threepids;
}
