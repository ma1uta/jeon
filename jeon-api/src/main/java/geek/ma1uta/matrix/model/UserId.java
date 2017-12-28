package geek.ma1uta.matrix.model;

public class UserId extends Id {

    public static final char SIGIL = '@';

    protected UserId(String localpart, String domain) {
        super(localpart, domain);
    }

    @Override
    public char sigil() {
        return SIGIL;
    }
}
