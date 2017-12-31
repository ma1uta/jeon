package geek.ma1uta.matrix;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Id {

    private static final Logger LOGGER = LoggerFactory.getLogger(Id.class);

    public static final Pattern PATTERN = Pattern.compile("^[@!$#+]([a-z0-9\\._=\\-/]):(.*)$");

    public static class Sigil {
        public static char EVENT = '$';
        public static char USER = '@';
        public static char ROOM = '!';
        public static char ALIAS = '#';
        public static char GROUP = '+';
    }

    public static boolean isId(String id) {
        try {
            validate(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static Matcher validate(String id) {
        if (StringUtils.isBlank(id)) {
            String message = "Empty id";
            LOGGER.error(message);
            throw new IllegalArgumentException(message);
        }

        Matcher matcher = PATTERN.matcher(id.trim());
        if (!matcher.matches()) {
            String message = String.format("Invalid id: '%s'", id);
            LOGGER.error(message);
            throw new IllegalArgumentException(message);
        }

        String localpart = matcher.group(1);
        String domain = matcher.group(2);
        LOGGER.trace("localpart: '%s', domain: '%s'", localpart, domain);

        try {
            new URL(domain);
        } catch (MalformedURLException e) {
            String message = String.format("Invalid domain part: '%s'", domain);
            LOGGER.error(message);
            throw new IllegalArgumentException(message);
        }
        return matcher;
    }

    public static char sigil(String id) {
        validate(id);
        return id.charAt(0);
    }

    public static String localpart(String id) {
        return validate(id).group(1);
    }

    public static String domain(String id) {
        return validate(id).group(2);
    }

    public static boolean isUserId(String id) {
        return sigil(id) == Sigil.USER;
    }

    public static boolean isEventId(String id) {
        return sigil(id) == Sigil.EVENT;
    }

    public static boolean isRoomId(String id) {
        return sigil(id) == Sigil.ROOM;
    }

    public static boolean isAliasIs(String id) {
        return sigil(id) == Sigil.ALIAS;
    }

    public static boolean isGroupId(String id) {
        return sigil(id) == Sigil.GROUP;
    }
}
