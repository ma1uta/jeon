package geek.ma1uta.matrix;

public class RoomId extends Id {

    public static final char SIGIL = '!';

    protected RoomId(String localpart, String domain) {
        super(localpart, domain);
    }

    @Override
    public char sigil() {
        return SIGIL;
    }
}
