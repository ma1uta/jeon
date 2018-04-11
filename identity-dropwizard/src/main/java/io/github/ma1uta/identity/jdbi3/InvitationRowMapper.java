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

import io.github.ma1uta.identity.model.Invitation;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Extract {@link Invitation} from the {@link ResultSet}.
 */
public class InvitationRowMapper implements RowMapper<Invitation> {
    @SuppressWarnings("unchecked") // to suppress check cast publicKeys from Object to Collection.
    @Override
    public Invitation map(ResultSet rs, StatementContext ctx) throws SQLException {
        Invitation invitation = new Invitation();
        invitation.setAddress(rs.getString("address"));
        invitation.setMedium(rs.getString("medium"));
        invitation.setRoomId(rs.getString("room_id"));
        invitation.setToken(rs.getString("token"));
        invitation.setSender(rs.getString("sender"));
        invitation.setPublicKeys(new ArrayList<>((Collection<String>) rs.getArray("public_keys").getArray()));
        invitation.setDisplayName(rs.getString("display_name"));
        return invitation;
    }
}
