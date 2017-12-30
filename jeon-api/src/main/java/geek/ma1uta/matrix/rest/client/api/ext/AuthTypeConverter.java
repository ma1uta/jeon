package geek.ma1uta.matrix.rest.client.api.ext;

import geek.ma1uta.matrix.rest.client.model.AuthType;

import javax.ws.rs.ext.ParamConverter;

public class AuthTypeConverter implements ParamConverter<AuthType> {
    @Override
    public AuthType fromString(String value) {
        return AuthType.valueOf(value);
    }

    @Override
    public String toString(AuthType value) {
        return value.code();
    }
}
