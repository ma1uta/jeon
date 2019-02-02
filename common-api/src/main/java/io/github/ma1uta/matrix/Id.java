/*
 * Copyright sablintolya@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ma1uta.matrix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * Matrix id (MXID) util class.
 */
public abstract class Id implements CharSequence {

    /**
     * Max length of the dns name.
     */
    public static final int DNS_NAME_LENGTH = 255;

    /**
     * Max length of the ipv6 address.
     */
    public static final int IPV6_LENGTH = 45;

    /**
     * Count of the ipv4 parts.
     */
    public static final int IPV4_PART_COUNT = 4;

    /**
     * Max length of the ipv4 parts.
     */
    public static final int IPV4_LENGTH = 3;

    /**
     * Default port.
     */
    public static final int DEFAULT_PORT = 80;

    private String localpart;

    private String hostname;

    private int port = DEFAULT_PORT;

    private String fullId;

    private Collection<IdParseException> errors;

    protected Id(String localpart, String serverName) {
        this.localpart = localpart(localpart);
        serverName(serverName);
    }

    public String getLocalpart() {
        return localpart;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    /**
     * Provides a sigil symbol.
     *
     * @return sigil.
     */
    public abstract char getSigil();

    @Override
    public String toString() {
        if (fullId == null) {
            StringBuilder builder = new StringBuilder();
            builder.append(getSigil()).append(getLocalpart()).append(":").append(getHostname());
            if (getPort() != DEFAULT_PORT) {
                builder.append(":").append(getPort());
            }
            this.fullId = builder.toString();
        }
        return fullId;
    }

    /**
     * Parse the input string and return a mxid instance.
     *
     * @param mxid mxid in string format.
     * @return mxid instance.
     */
    public static Id valueOf(String mxid) {
        return IdParser.getInstance().parse(mxid);
    }

    /**
     * Is valid mxid or not.
     *
     * @return {@code true} if mxid valid else {@code false}.
     */
    public boolean isValid() {
        return errors == null || errors.isEmpty();
    }

    /**
     * Return all mxid violations.
     *
     * @return mxid spec violations.
     */
    public Collection<IdParseException> errors() {
        return errors == null ? Collections.emptyList() : errors;
    }

    /**
     * Add a new mxid mistake.
     *
     * @param error spec violation.
     */
    protected void addError(IdParseException error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }

    /**
     * Prepare localpart.
     */
    protected abstract String localpart(String localpart);

    /**
     * Prepare hostname (server name without port).
     *
     * @param hostname hotname.
     * @return hostname.
     */
    protected String hostname(String hostname) {
        return validateHostname(hostname);
    }

    /**
     * Prepare server name and set server name and port.
     *
     * @param serverName servername.
     */
    private void serverName(String serverName) {
        String hostname = serverName;
        if (serverName.charAt(serverName.length() - 1) != ']') {
            int portIndex = serverName.lastIndexOf(":");
            if (portIndex != -1) {
                try {
                    this.port = Integer.parseInt(serverName.substring(portIndex));
                    hostname = serverName.substring(0, portIndex);
                } catch (NumberFormatException e) {
                    addError(new IdParseException("Port must contains only digits."));
                }
            }
        }

        this.hostname = hostname(hostname);
    }

    protected String validateHostname(String hostname) {
        if (validateIpv4Address(hostname) || validateIpv6Address(hostname)) {
            return hostname;
        }

        validateDnsName(hostname);
        return hostname;
    }

    protected boolean validateIpv4Address(String ipv4Address) {
        String[] ipv4parts = ipv4Address.split("\\.");

        if (ipv4parts.length != IPV4_PART_COUNT) {
            return false;
        }

        for (int i = 0; i < IPV4_PART_COUNT; i++) {
            if (!validateIpv4Part(ipv4parts[i].toCharArray())) {
                return false;
            }
        }
        return true;
    }

    protected boolean validateIpv4Part(char[] ipv4part) {
        if (ipv4part.length > IPV4_LENGTH) {
            addError(new Id.IdParseException("Length of the ipv4 part is longer than " + IPV4_LENGTH + " symbols."));
        }
        for (char ipv4char : ipv4part) {
            if (!Character.isDigit(ipv4char)) {
                return false;
            }
        }
        return true;
    }

    protected boolean validateIpv6Address(String ipv6Address) {
        if (ipv6Address.charAt(0) != '[' || ipv6Address.charAt(ipv6Address.length() - 1) != ']') {
            return false;
        }

        if (ipv6Address.length() < 2) {
            return false;
        }

        if (ipv6Address.length() > IPV6_LENGTH) {
            return false;
        }

        for (int i = 0; i < ipv6Address.length(); i++) {
            if (!validateIpv6Char(ipv6Address.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    protected boolean validateIpv6Char(char ipv6char) {
        return Character.isDigit(ipv6char)
            || (ipv6char >= 'a' && ipv6char <= 'f')
            || (ipv6char >= 'A' && ipv6char <= 'F')
            || ipv6char == ':' || ipv6char == '.';
    }

    protected void validateDnsName(String dnsName) {
        if (dnsName.length() > DNS_NAME_LENGTH) {
            addError(new Id.IdParseException("Dns name must be shorter than " + DNS_NAME_LENGTH + " symbols"));
        }

        for (int i = 0; i < dnsName.length(); i++) {
            validateDnsChar(dnsName.charAt(i));
        }
    }

    protected void validateDnsChar(char dnsChar) {
        if (!(Character.isDigit(dnsChar) || Character.isAlphabetic(dnsChar) || dnsChar == '-' || dnsChar == '.')) {
            addError(new Id.IdParseException("Char '" + dnsChar + "' must be a digit, alphabetic, '-' or '.'"));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Id)) {
            return false;
        }
        Id id = (Id) o;
        return getSigil() == id.getSigil()
            && port == id.port
            && localpart.equals(id.localpart)
            && hostname.equals(id.hostname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSigil(), localpart, hostname, port);
    }

    @Override
    public int length() {
        return toString().length();
    }

    @Override
    public char charAt(int index) {
        return toString().charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return toString().subSequence(start, end);
    }

    /**
     * Mismatches with the specification.
     */
    public static class IdParseException extends RuntimeException {

        public IdParseException(String message) {
            super(message);
        }
    }
}
