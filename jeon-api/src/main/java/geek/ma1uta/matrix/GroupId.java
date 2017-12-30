package geek.ma1uta.matrix;

import geek.ma1uta.matrix.Id;

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
