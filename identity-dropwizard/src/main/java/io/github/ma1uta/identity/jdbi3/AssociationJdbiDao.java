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

import io.github.ma1uta.identity.dao.AssociationDao;
import io.github.ma1uta.identity.model.Association;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDateTime;
import java.util.List;

/**
 * IMplementation of the {@link AssociationDao} based on the jdbi.
 */
public interface AssociationJdbiDao extends AssociationDao {

    @SqlQuery("select * from only \"association\" where medium = :medium and address = :address")
    @RegisterRowMapper(AssociationRowMapper.class)
    @Override
    List<Association> findByAddressMedium(@Bind("address") String address, @Bind("medium") String medium);

    @SqlUpdate("insert into \"association\"(medium, address, mxid, created, expired, ts) "
        + "values(:medium, :address, :mxid, extract(seconds from now), :expired, extract(seconds from now)) "
        + "on conflict (medium, address) do nothing")
    @Override
    void insertOrIgnore(@Bind("address") String address, @Bind("medium") String medium, @Bind("mxid") String mxid,
                        @Bind("expired") LocalDateTime expired);

    @SqlUpdate("with moved_rows as "
        + "(delete from \"association\" where expired > now returning *) "
        + "insert into \"expired_association\" "
        + "select * from moved_rows")
    @Override
    void expire();
}
