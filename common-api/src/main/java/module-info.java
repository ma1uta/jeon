module matrix.common.api {
    exports io.github.ma1uta.matrix.common;
    exports io.github.ma1uta.matrix.event;
    exports io.github.ma1uta.matrix.event.content;
    exports io.github.ma1uta.matrix.event.message;
    exports io.github.ma1uta.matrix.event.nested;
    exports io.github.ma1uta.matrix.event.encrypted;
    exports io.github.ma1uta.matrix.protocol;
    exports io.github.ma1uta.matrix.thirdpid;

    requires transitive java.ws.rs;
    requires transitive java.json.bind;
    requires transitive com.fasterxml.jackson.annotation;
    requires transitive io.swagger.v3.oas.annotations;
}
