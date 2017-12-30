package geek.ma1uta.matrix.rest.client.api.ext;

import geek.ma1uta.matrix.rest.client.model.EventFormat;

import javax.ws.rs.ext.ParamConverter;

public class EventFormatConverter implements ParamConverter<EventFormat> {
    @Override
    public EventFormat fromString(String value) {
        return EventFormat.valueOf(value);
    }

    @Override
    public String toString(EventFormat value) {
        return value.code();
    }
}
