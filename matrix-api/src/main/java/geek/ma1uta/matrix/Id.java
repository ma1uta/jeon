package geek.ma1uta.matrix;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Id {

    Logger LOGGER = LoggerFactory.getLogger(Id.class);

    Pattern PATTERN = Pattern.compile("^[@!$#+]([a-z0-9\\._=\\-/]):(.*)$");

    interface Sigil {
        char EVENT = '$';
        char USER = '@';
        char ROOM = '!';
        char ALIAS = '#';
        char GROUP = '+';
    }

    static boolean isId(String id) {
        try {
            validate(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    static Matcher validate(String id) {
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

    static char sigil(String id) {
        validate(id);
        return id.charAt(0);
    }

    static String localpart(String id) {
        return validate(id).group(1);
    }

    static String domain(String id) {
        return validate(id).group(2);
    }

    static boolean isUserId(String id) {
        return sigil(id) == Sigil.USER;
    }

    static boolean isEventId(String id) {
        return sigil(id) == Sigil.EVENT;
    }

    static boolean isRoomId(String id) {
        return sigil(id) == Sigil.ROOM;
    }

    static boolean isAliasIs(String id) {
        return sigil(id) == Sigil.ALIAS;
    }

    static boolean isGroupId(String id) {
        return sigil(id) == Sigil.GROUP;
    }
}
