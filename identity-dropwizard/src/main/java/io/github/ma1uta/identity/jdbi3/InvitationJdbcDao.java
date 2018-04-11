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

import io.github.ma1uta.identity.dao.InvitationDao;
import io.github.ma1uta.identity.model.Invitation;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

/**
 * Implementation of the {@link InvitationDao} based on the jdbi.
 */
public interface InvitationJdbcDao extends InvitationDao {

    @SqlUpdate("insert into \"invitation\"(medium, address, room_id, sender, token, public_key, display_name) "
        + "values(:medium, :address, :room_id, :sender, :token, :public_key, :display_name)")
    @Override
    void insert(@Bind("address") String address, @Bind("medium") String medium, @Bind("room_id") String roomId,
                @Bind("sender") String sender, @Bind("token") String token, @BindList("public_key") List<String> publicKeys,
                @Bind("display_name") String displayName);

    @SqlQuery("select * from \"invitation\" where medium = :medium and address = :address")
    @RegisterRowMapper(InvitationRowMapper.class)
    @Override
    List<Invitation> findByAddressMedium(@Bind("address") String address, @Bind("medium") String medium);
}
