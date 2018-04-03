package io.github.ma1uta.identity.service

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.ma1uta.identity.IdentityProperties
import io.github.ma1uta.identity.jdbc.Query
import io.github.ma1uta.identity.key.KeyService
import io.github.ma1uta.identity.model.Association
import io.github.ma1uta.identity.model.Session
import io.github.ma1uta.jeon.exception.MatrixException
import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupRequest
import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupResponse
import io.github.ma1uta.matrix.identity.model.lookup.LookupResponse
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.sql.ResultSet
import java.time.LocalDateTime

const val M_TOO_MANY_ASSOCIATIONS = "M_TOO_MANY_ASSOCIATIONS"

@Service
class AssociationService(val template: NamedParameterJdbcTemplate, val query: Query, val keyService: KeyService,
                         val objectMapper: ObjectMapper, val props: IdentityProperties) {

    /**
     * Lookup association.
     *
     * @param medium 'email' or 'msisdn'.
     * @param address email address or phone number.
     * @param sign if true then sign result else false.
     */
    fun lookup(medium: String, address: String, sign: Boolean = true): LookupResponse {
        val associations = template.query(query.association.findByAddressMedium,
                mutableMapOf(Pair("address", address), Pair("medium", medium)), AssociationRowMapper())
        val response = LookupResponse()
        if (associations.size > 1) {
            throw MatrixException(M_TOO_MANY_ASSOCIATIONS, "Too many associations.")
        } else if (associations.size == 1) {
            response.address = associations[0].address
            response.medium = associations[0].medium
            response.mxid = associations[0].mxid
            response.notBefore = associations[0].created
            response.notAfter = associations[0].expired
            response.ts = associations[0].ts

            if (sign) {
                val content = objectMapper.writeValueAsString(response)
                response.signatures = keyService.sign(content)
            }
        }
        return response
    }

    /**
     * Bulk lookup.
     *
     * @param request bulk request.
     */
    fun lookup(request: BulkLookupRequest): BulkLookupResponse {
        val bulkResponse = BulkLookupResponse()
        bulkResponse.threepids = request.threepids.map { list -> lookup(list[0], list[1], false) }
                .filter { response -> !response.address.isNullOrBlank() && !response.medium.isNullOrBlank() && !response.mxid.isNullOrBlank() }
                .map { response -> listOf(response.medium, response.address, response.mxid) }
        return bulkResponse
    }

    /**
     * Create new association.
     */
    fun create(session: Session, mxid: String) {
        val expired = LocalDateTime.now().plusSeconds(props.associationTTL)
        template.update(query.association.insertOrIgnore, mutableMapOf(Pair("address", session.address), Pair("medium", session.medium),
                Pair("mxid", mxid), Pair("expired", expired)))
    }

    /**
     * Move expired associations to the "expired_association" table.
     */
    @Scheduled(cron = "%{identity.association.expire}")
    fun expire() {
        template.update(query.association.expire, mutableMapOf<String, String>())
    }

    /**
     * Mapper from a ResultSet to the Association.
     */
    class AssociationRowMapper : RowMapper<Association> {
        override fun mapRow(rs: ResultSet?, rowNum: Int): Association {
            return Association(rs!!.getString("address"),
                    rs.getString("medium"),
                    rs.getString("mxid"),
                    rs.getLong("created"),
                    rs.getLong("rxpired"),
                    rs.getLong("ts"))
        }
    }
}
