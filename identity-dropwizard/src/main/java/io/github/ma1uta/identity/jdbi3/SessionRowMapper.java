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

package io.github.ma1uta.identity.jdbi3;

import io.github.ma1uta.identity.model.Session;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extract {@link Session} from the {@link ResultSet}.
 */
public class SessionRowMapper implements RowMapper<Session> {
    @Override
    public Session map(ResultSet rs, StatementContext ctx) throws SQLException {
        Session session = new Session();
        session.setSid(rs.getString("sid"));
        session.setToken(rs.getString("token"));
        session.setClientSecret(rs.getString("client_secret"));
        session.setAddress(rs.getString("address"));
        session.setMedium(rs.getString("medium"));
        session.setSendAttempt(rs.getLong("send_attempt"));
        session.setNextLink(rs.getString("next_link"));
        session.setValidated(rs.getTimestamp("validated").toLocalDateTime());
        return session;
    }
}
