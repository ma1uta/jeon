package geek.ma1uta.matrix.rest.client.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Page<T> {

    public static class Query {
        public static final String FROM = "from";
        public static final String TO = "to";
        public static final String LIMIT = "limit";
        public static final String DIR = "dir";

        public static final String START = "start";
        public static final String END = "end";
    }

    private String start;

    private String end;

    private List<T> chunk;
}
