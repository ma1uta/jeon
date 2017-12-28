package geek.ma1uta.matrix.api.ext;

import geek.ma1uta.matrix.model.EventType;

import javax.ws.rs.ext.ParamConverter;

public class EventTypeConverter implements ParamConverter<EventType> {
    @Override
    public EventType fromString(String value) {
        return EventType.valueOf(value);
    }

    @Override
    public String toString(EventType value) {
        return value.code();
    }
}
