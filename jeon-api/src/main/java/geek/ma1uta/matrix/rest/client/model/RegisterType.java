package geek.ma1uta.matrix.rest.client.model;

import geek.ma1uta.matrix.EnumWithCode;

public enum RegisterType implements EnumWithCode {
    GUEST("guest"),
    USER("user");

    private String code;

    RegisterType(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return code;
    }
}
