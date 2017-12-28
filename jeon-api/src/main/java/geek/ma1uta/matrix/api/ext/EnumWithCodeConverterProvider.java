package geek.ma1uta.matrix.api.ext;

import geek.ma1uta.matrix.model.AuthType;
import geek.ma1uta.matrix.model.EventType;
import geek.ma1uta.matrix.model.MessageType;
import geek.ma1uta.matrix.model.RegisterType;

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
        }
        return null;
    }
}
