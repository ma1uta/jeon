package io.github.ma1uta.identity.api;

import io.github.ma1uta.identity.service.AssociationService;
import io.github.ma1uta.jeon.exception.MatrixException;
import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.identity.api.LookupApi;
import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupRequest;
import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupResponse;
import io.github.ma1uta.matrix.identity.model.lookup.LookupResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * Implementation of the {@link LookupApi}.
 */
public class Lookup implements LookupApi {

    private final AssociationService associationService;

    public Lookup(AssociationService associationService) {
        this.associationService = associationService;
    }

    public AssociationService getAssociationService() {
        return associationService;
    }

    @Override
    public LookupResponse lookup(String medium, String address) {
        if (StringUtils.isAnyBlank(medium, address)) {
            throw new MatrixException(ErrorResponse.Code.M_BAD_JSON, "Missing medium or address.");
        }
        return getAssociationService().lookup(address, medium, true);
    }

    @Override
    public BulkLookupResponse bulkLookup(BulkLookupRequest request) {
        if (request == null) {
            throw new MatrixException(ErrorResponse.Code.M_BAD_JSON, "Missing medium or address.");
        }
        return getAssociationService().lookup(request);
    }
}
