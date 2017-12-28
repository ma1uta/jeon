package geek.ma1uta.matrix.model;

public class GroupId extends Id {

    public static final char SIGIL = '+';

    protected GroupId(String localpart, String domain) {
        super(localpart, domain);
    }

    @Override
    public char sigil() {
        return SIGIL;
    }
}
