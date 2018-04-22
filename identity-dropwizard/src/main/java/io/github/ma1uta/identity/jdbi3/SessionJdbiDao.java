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

import io.github.ma1uta.identity.dao.SessionDao;
import io.github.ma1uta.identity.model.Session;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

/**
 * Implementation of the {@link SessionDao} based on the jdbi.
 */
public interface SessionJdbiDao extends SessionDao {

    @SqlUpdate("insert into \"session\"(sid, token, client_secret, medium, address, send_attempt, next_link, created) "
        + "values(:sid, :token, :client_secret, :medium, :address, :send_attempt, :next_link, now) "
        + "on conflict (client_secret, address, medium) do update "
        + "set send_attempt = :send_attempt")
    @Override
    void insertOrUpdate(@Bind("id") String sid, @Bind("token") String token, @Bind("client_secret") String clientSecret,
                        @Bind("medium") String medium, @Bind("address") String address, @Bind("send_attempt") String sendAttempt,
                        @Bind("next_link") String nextLink);

    @SqlQuery("select * from \"session\" where client_secret = :client_secret and address = :address and medium = :medium")
    @RegisterRowMapper(SessionRowMapper.class)
    @Override
    List<Session> findBySecretEmail(@Bind("client_secret") String clientSecret, @Bind("address") String address,
                                    @Bind("medium") String medium);

    @SqlQuery("select * from \"session\" where client_secret = :client_secret and sid = :sid")
    @RegisterRowMapper(SessionRowMapper.class)
    @Override
    List<Session> findBySecretSid(@Bind("client_secret") String clientSecret, @Bind("sid") String sid);

    @SqlUpdate("select * from \"session\" where client_secret = :client_secret and token = :token and sid = :sid")
    @RegisterRowMapper(SessionRowMapper.class)
    @Override
    List<Session> findBySecretSidToken(@Bind("client_secret") String clientSecret, @Bind("sid") String sid, @Bind("token") String token);

    @SqlUpdate("delete from \"session\" where validated is null and created + interval '1 day' < now")
    @Override
    void deleteOldest();

    @SqlUpdate("update \"session\" set validated = now where sid = :sid")
    @Override
    void validate(@Bind("sid") String sid);
}
