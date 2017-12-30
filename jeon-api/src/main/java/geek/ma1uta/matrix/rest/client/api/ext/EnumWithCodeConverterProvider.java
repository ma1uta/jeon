package geek.ma1uta.matrix.rest.client.api.ext;

import geek.ma1uta.matrix.rest.client.model.AuthType;
import geek.ma1uta.matrix.rest.client.model.EventFormat;
import geek.ma1uta.matrix.rest.client.model.EventType;
import geek.ma1uta.matrix.rest.client.model.MessageType;
import geek.ma1uta.matrix.rest.client.model.RegisterType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

@Provider
public class EnumWithCodeConverterProvider implements ParamConverterProvider {
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (EventType.class.isAssignableFrom(rawType)) {
            return (ParamConverter<T>) new EventTypeConverter();
        } else if (MessageType.class.isAssignableFrom(rawType)) {
            return (ParamConverter<T>) new MessageTypeConverter();
        } else if (AuthType.class.isAssignableFrom(rawType)) {
            return (ParamConverter<T>) new AuthTypeConverter();
        } else if (RegisterType.class.isAssignableFrom(rawType)) {
            return (ParamConverter<T>) new RegisterTypeConverter();
        } else if (EventFormat.class.isAssignableFrom(rawType)) {
            return (ParamConverter<T>) new EventFormatConverter();
        }
        return null;
    }
}
