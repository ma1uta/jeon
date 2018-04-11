package io.github.ma1uta.identity.api;

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.identity.api.StatusApi;

/**
 * Implementation of the {@link StatusApi}.
 */
public class Status implements StatusApi {
    @Override
    public EmptyResponse v1Status() {
        return new EmptyResponse();
    }
}
