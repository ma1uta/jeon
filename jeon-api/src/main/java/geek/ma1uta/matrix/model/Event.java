package geek.ma1uta.matrix.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public abstract class Event<T extends EnumWithCode> {

    private Map<String, Object> content;

    private T type;
}
