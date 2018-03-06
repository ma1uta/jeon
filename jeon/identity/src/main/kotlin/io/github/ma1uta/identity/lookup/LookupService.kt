package io.github.ma1uta.identity.lookup

import io.github.ma1uta.identity.jdbc.Query
import io.github.ma1uta.identity.key.KeyService
import io.github.ma1uta.identity.model.Association
import io.github.ma1uta.jeon.exception.MatrixException
import io.github.ma1uta.matrix.identity.model.lookup.LookupResponse
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import java.sql.ResultSet

const val M_TOO_MANY_ASSOCIATIONS = "M_TOO_MANY_ASSOCIATIONS"

@Service
class LookupService(val template: NamedParameterJdbcTemplate, val query: Query, val keyService: KeyService) {

    fun lookup(address: String, medium: String): LookupResponse {

        val associations = template.query(query.association.findByAddressMedium,
                mutableMapOf(Pair("address", address), Pair("medium", medium)), AssociationRowMapper())
        val response = LookupResponse()
        if (associations.size > 1) {
            throw MatrixException(M_TOO_MANY_ASSOCIATIONS, "Too many associations")
        } else if (associations.size == 1) {
            response.address = associations[0].address
            response.medium = associations[0].medium
            response.mxid = associations[0].mxid
            response.notBefore = associations[0].created
            response.notAfter = associations[0].expired
            response.ts = associations[0].ts


        }
        return response
    }

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
