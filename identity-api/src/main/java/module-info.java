module matrix.identity.api {
    exports io.github.ma1uta.matrix.identity.api;
    exports io.github.ma1uta.matrix.identity.api.deprecated;
    exports io.github.ma1uta.matrix.identity.model.authentication;
    exports io.github.ma1uta.matrix.identity.model.associations;
    exports io.github.ma1uta.matrix.identity.model.invitation;
    exports io.github.ma1uta.matrix.identity.model.key;
    exports io.github.ma1uta.matrix.identity.model.lookup;
    exports io.github.ma1uta.matrix.identity.model.session;
    exports io.github.ma1uta.matrix.identity.model.signing;
    exports io.github.ma1uta.matrix.identity.model.terms;
    exports io.github.ma1uta.matrix.identity.model.validation;

    requires transitive matrix.common.api;
}
