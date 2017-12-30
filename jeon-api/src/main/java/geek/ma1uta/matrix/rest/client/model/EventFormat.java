package geek.ma1uta.matrix.rest.client.model;

import geek.ma1uta.matrix.EnumWithCode;

public enum EventFormat implements EnumWithCode {
    CLIENT("client"),
    FEDERATION("federation");

    private String code;

    EventFormat(String code) {
        this.code = code;
    }


    @Override
    public String code() {
        return code;
    }
}
