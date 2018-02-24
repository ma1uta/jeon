module io.github.ma1uta.jeon.server.common {
    exports io.github.ma1uta.jeon.exception;

    requires io.github.ma1uta.jeon.common.api;
    requires slf4j.api;
    requires spring.web;
    requires java.ws.rs;
    requires kotlin.stdlib;
}
