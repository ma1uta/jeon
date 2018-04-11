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

import io.github.ma1uta.identity.model.Association;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extract {@link Association} instance from the {@link ResultSet}.
 */
public class AssociationRowMapper implements RowMapper<Association> {
    @Override
    public Association map(ResultSet rs, StatementContext ctx) throws SQLException {
        Association association = new Association();
        association.setAddress(rs.getString("address"));
        association.setMedium(rs.getString("medium"));
        association.setCreated(rs.getLong("created"));
        association.setExpired(rs.getLong("expired"));
        association.setTs(rs.getLong("ts"));
        association.setMxid(rs.getString("mxid"));
        return association;
    }
}
