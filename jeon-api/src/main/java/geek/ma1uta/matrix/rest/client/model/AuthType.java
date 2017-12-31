package geek.ma1uta.matrix.rest.client.model;

public interface AuthType {

    String PASSWORD = "m.login.password";
    String RECAPTCHA = "m.login.recaptcha";
    String OAUTH2 = "m.login.oauth2";
    String EMAIL_IDENTITY = "m.login.email.identity";
    String TOKEN = "m.login.token";
    String DUMMY = "m.login.dummy";
}
