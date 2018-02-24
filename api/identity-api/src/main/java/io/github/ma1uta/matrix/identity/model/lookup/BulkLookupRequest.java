package io.github.ma1uta.matrix.identity.model.lookup;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Request of the lookup Matrix user IDs for a list of 3pids..
 *
 * @author ma1uta
 */
@Getter
@Setter
public class BulkLookupRequest {

    /**
     * Required. an array of arrays containing the 3PID Types with the medium in first position and the address in second position.
     */
    private List<List<String>> threepids;
}
