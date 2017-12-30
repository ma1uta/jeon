package geek.ma1uta.matrix.rest.client.model;

import geek.ma1uta.matrix.EnumWithCode;

public enum AuthType implements EnumWithCode {

    PASSWORD("m.login.password"),
    RECAPTCHA("m.login.recaptcha"),
    OAUTH2("m.login.oauth2"),
    EMAIL_IDENTITY("m.login.email.identity"),
    TOKEN("m.login.token"),
    DUMMY("m.login.dummy");

    private String code;

    AuthType(String code) {
        this.code = code;
    }


    @Override
    public String code() {
        return code;
    }
}
