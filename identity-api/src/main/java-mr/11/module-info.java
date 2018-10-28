module matrix.identity.api {
    exports io.github.ma1uta.matrix.identity.api;
    exports io.github.ma1uta.matrix.identity.model.associations;
    exports io.github.ma1uta.matrix.identity.model.invitation;
    exports io.github.ma1uta.matrix.identity.model.key;
    exports io.github.ma1uta.matrix.identity.model.lookup;
    exports io.github.ma1uta.matrix.identity.model.session;
    exports io.github.ma1uta.matrix.identity.model.signing;
    exports io.github.ma1uta.matrix.identity.model.validation;

    requires matrix.common.api;

    requires java.ws.rs;
}
