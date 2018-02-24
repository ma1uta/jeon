module io.github.ma1uta.matrix.client.api {
    exports io.github.ma1uta.matrix.client.api;
    exports io.github.ma1uta.matrix.client.model;
    exports io.github.ma1uta.matrix.client.model.auth;
    exports io.github.ma1uta.matrix.client.model.account;
    exports io.github.ma1uta.matrix.client.model.version;

    requires io.github.ma1uta.matrix.api;
    requires slf4j.api;
    requires java.ws.rs;
    requires lombok;
    requires com.fasterxml.jackson.annotation;
}
