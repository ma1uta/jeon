module matrix.server.api {
    exports io.github.ma1uta.matrix.server.api;
    exports io.github.ma1uta.matrix.server.model.federation;
    exports io.github.ma1uta.matrix.server.model.federation.edu;
    exports io.github.ma1uta.matrix.server.model.federation.edu.content;
    exports io.github.ma1uta.matrix.server.model.federation.edu.content.nested;
    exports io.github.ma1uta.matrix.server.model.federation.v1;
    exports io.github.ma1uta.matrix.server.model.federation.v3;
    exports io.github.ma1uta.matrix.server.model.key;
    exports io.github.ma1uta.matrix.server.model.serverdiscovery;
    exports io.github.ma1uta.matrix.server.model.version;

    requires transitive matrix.common.api;
}
