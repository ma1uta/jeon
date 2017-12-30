package geek.ma1uta.matrix.rest.client.api.ext;

import geek.ma1uta.matrix.rest.client.model.PresenceType;

import javax.ws.rs.ext.ParamConverter;

public class PresenceTypeConverter implements ParamConverter<PresenceType> {
    @Override
    public PresenceType fromString(String value) {
        return PresenceType.valueOf(value);
    }

    @Override
    public String toString(PresenceType value) {
        return value.code();
    }
}
