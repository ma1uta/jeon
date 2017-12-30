package geek.ma1uta.matrix.rest.client.model;

import geek.ma1uta.matrix.EnumWithCode;

public enum PresenceType implements EnumWithCode {
    OFFLINE("offline");

    private String code;

    PresenceType(String code) {
        this.code = code;
    }


    @Override
    public String code() {
        return code;
    }
}
