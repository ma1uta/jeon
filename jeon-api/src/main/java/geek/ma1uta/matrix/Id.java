package geek.ma1uta.matrix;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public abstract class Id {

    private static final Logger LOGGER = LoggerFactory.getLogger(Id.class);

    public static final char SEPARATOR = ':';

    public static final Pattern PATTERN = Pattern.compile("^[@!$#+]([a-z0-9\\._=\\-/])" + SEPARATOR + "(.*)$");

    private String localpart;

    private String domain;

    protected Id(String localpart, String domain) {
        this.localpart = localpart;
        this.domain = domain;
    }

    public abstract char sigil();

    public static Id of(String id) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(String.format("Parse id: '%s'", id));
        }
        if (StringUtils.isBlank(id)) {
            String message = "Empty id";
            LOGGER.error(message);
            throw new IllegalArgumentException(message);
        }

        String trimmedId = id.trim();
        Matcher matcher = PATTERN.matcher(trimmedId);
        if (!matcher.matches()) {
            String message = String.format("Invalid id: '%s'", trimmedId);
            LOGGER.error(message);
            throw new IllegalArgumentException(message);
        }

        String localpart = matcher.group(1);
        String domain = matcher.group(2);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("localpart: '%s', domain: '%s'", localpart, domain);
        }

        try {
            new URL(domain);
        } catch (MalformedURLException e) {
            String message = String.format("Invalid domain part: '%s'", domain);
            LOGGER.error(message);
            throw new IllegalArgumentException(message);
        }

        Id itemId;
        switch (trimmedId.charAt(0)) {
            case UserId.SIGIL:
                itemId = new UserId(localpart, domain);
                break;
            case RoomId.SIGIL:
                itemId = new RoomId(localpart, domain);
                break;
            case EventId.SIGIL:
                itemId = new EventId(localpart, domain);
                break;
            case RoomAlias.SIGIL:
                itemId = new RoomAlias(localpart, domain);
                break;
            case GroupId.SIGIL:
                itemId = new GroupId(localpart, domain);
                break;
            default:
                String message = String.format("Unknown id: '%s'", id);
                LOGGER.error(message);
                throw new IllegalArgumentException(message);
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(String.format("Id class: '%s'", itemId.getClass().getCanonicalName()));
        }

        return itemId;
    }

    public static boolean isId(String id) {
        Matcher matcher = PATTERN.matcher(id);
        if (!matcher.find()) {
            return false;
        }
        try {
            new URL(matcher.group(2));
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
