module io.github.ma1uta.matrix.common.api {
    exports io.github.ma1uta.matrix;
    exports io.github.ma1uta.matrix.event;
    exports io.github.ma1uta.matrix.event.content;
    exports io.github.ma1uta.matrix.event.encrypted;
    exports io.github.ma1uta.matrix.event.message;
    exports io.github.ma1uta.matrix.event.nested;
    exports io.github.ma1uta.matrix.protocol;
    exports io.github.ma1uta.matrix.support;
    exports io.github.ma1uta.matrix.thirdpid;

    requires swagger.annotations;
    requires com.fasterxml.jackson.annotation;
    requires java.json.bind;
}
