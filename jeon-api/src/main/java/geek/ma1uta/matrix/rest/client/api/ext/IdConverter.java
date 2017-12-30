package geek.ma1uta.matrix.rest.client.api.ext;

import geek.ma1uta.matrix.Id;

import javax.ws.rs.ext.ParamConverter;

public class IdConverter<I extends Id> implements ParamConverter<I> {
    @Override
    public I fromString(String value) {
        return Id.of(value);
    }

    @Override
    public String toString(I value) {
        return value.toString();
    }
}
