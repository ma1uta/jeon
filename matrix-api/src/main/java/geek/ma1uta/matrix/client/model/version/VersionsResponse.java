package geek.ma1uta.matrix.client.model.version;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The versions supported by the server.
 *
 * @author ma1uta
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VersionsResponse {

    /**
     * The supported versions.
     */
    private String[] versions;
}
