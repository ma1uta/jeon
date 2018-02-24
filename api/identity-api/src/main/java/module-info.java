module io.github.ma1uta.matrix.identity.api {
    exports io.github.ma1uta.matrix.identity.api;
    exports io.github.ma1uta.matrix.identity.model.associations;
    exports io.github.ma1uta.matrix.identity.model.invitation;
    exports io.github.ma1uta.matrix.identity.model.key;
    exports io.github.ma1uta.matrix.identity.model.lookup;
    exports io.github.ma1uta.matrix.identity.model.validation;

    requires io.github.ma1uta.matrix.api;
    requires slf4j.api;
    requires java.ws.rs;
    requires lombok;
    requires com.fasterxml.jackson.annotation;
}
