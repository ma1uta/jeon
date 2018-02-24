module io.github.ma1uta.jeon.server.jserver {
    requires io.github.ma1uta.jeon.server.common;
    requires io.github.ma1uta.jeon.client.api;
    requires jersey.server;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires kotlin.stdlib;
    requires spring.core;
    requires javax.inject;
    requires spring.boot;
    requires com.fasterxml.jackson.databind;
    requires spring.beans;
    requires spring.web;
    requires spring.security.core;
    requires spring.security.config;
    requires jmacaroons;
    requires io.github.ma1uta.jeon.common.api;
    requires javax.servlet.api;
    requires slf4j.api;
    requires java.ws.rs;
    requires spring.tx;
    requires spring.jdbc;
    requires java.sql;
}
