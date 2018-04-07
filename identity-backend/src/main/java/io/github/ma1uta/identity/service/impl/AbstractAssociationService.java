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

package io.github.ma1uta.identity.service.impl;

import io.github.ma1uta.identity.configuration.AssociationConfiguration;
import io.github.ma1uta.identity.dao.AssociationDao;
import io.github.ma1uta.identity.model.Association;
import io.github.ma1uta.identity.model.Session;
import io.github.ma1uta.identity.service.AssociationService;
import io.github.ma1uta.identity.service.KeyService;
import io.github.ma1uta.identity.service.SerializerService;
import io.github.ma1uta.jeon.exception.MatrixException;
import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupRequest;
import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupResponse;
import io.github.ma1uta.matrix.identity.model.lookup.LookupResponse;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.operator.OperatorCreationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Default implementation.
 * <p/>
 * There are default implementation for all methods of the {@link AssociationService}.
 * <p/>
 * You can create you own class for example with annotation @Transactional (jpa) or another and invoke the same method with suffix "Internal".
 * <pre>
 * {@code
 *     @literal @Service
 *     public class MyAssociationService extends AbstractAssociationService {
 *         ...
 *         @literal @Override
 *         @literal @Transactional
 *         @literal @MyFavouriteAnnotation
 *         public void expire() {
 *             // wrap next link to transaction via annotation or code.
 *             super.expire();
 *         }
 *         ...
 *     }
 * }
 * </pre>
 */
public abstract class AbstractAssociationService implements AssociationService {

    /**
     * Error message when too mant associations.
     */
    public static final String M_TOO_MANY_ASSOCIATIONS = "M_TOO_MANY_ASSOCIATIONS";

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAssociationService.class);

    /**
     * Association dao.
     */
    private final AssociationDao associationDao;

    /**
     * Key service to sign response.
     */
    private final KeyService keyService;

    /**
     * Configuration.
     */
    private final AssociationConfiguration configuration;

    /**
     * Object serializer.
     * <p/>
     * Often is Jackson's ObjectMapper.
     */
    private final SerializerService serializer;

    public AbstractAssociationService(AssociationDao associationDao, KeyService keyService,
                                      AssociationConfiguration configuration, SerializerService serializer) {
        this.associationDao = associationDao;
        this.keyService = keyService;
        this.configuration = configuration;
        this.serializer = serializer;
    }

    public AssociationDao getAssociationDao() {
        return associationDao;
    }

    public KeyService getKeyService() {
        return keyService;
    }

    public AssociationConfiguration getConfiguration() {
        return configuration;
    }

    public SerializerService getSerializer() {
        return serializer;
    }

    /**
     * {@link AssociationService#lookup(String, String, boolean)}
     */
    protected LookupResponse lookupInternal(String address, String medium, boolean sign) {
        List<Association> associationList = getAssociationDao().findByAddressMedium(address, medium);
        LookupResponse response = new LookupResponse();
        if (associationList.size() > 1) {
            throw new MatrixException(M_TOO_MANY_ASSOCIATIONS, "Too many associations.");
        } else if (associationList.size() == 1) {
            Association association = associationList.get(0);
            response.setAddress(association.getAddress());
            response.setMedium(association.getMedium());
            response.setMxid(association.getMxid());
            response.setNotBefore(association.getCreated());
            response.setNotAfter(association.getExpired());
            response.setTs(association.getTs());

            if (sign) {
                try {
                    Optional<Map<String, Map<String, String>>> signature = getKeyService().sign(getSerializer().serialize(response), true);
                    if (!signature.isPresent()) {
                        throw new MatrixException(MatrixException.M_INTERNAL, "Cannot find key to sign");
                    }
                    response.setSignatures(signature.get());
                } catch (CertificateException | UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException
                    | IOException | OperatorCreationException | InvalidKeyException | SignatureException e) {
                    String message = e.getMessage();
                    LOGGER.error(message, e);
                    throw new MatrixException(MatrixException.M_INTERNAL, message);
                }
            }
        }
        return response;
    }

    /**
     * {@link AssociationService#lookup(BulkLookupRequest)}
     */
    protected BulkLookupResponse lookupInternal(BulkLookupRequest request) {
        BulkLookupResponse bulkResponse = new BulkLookupResponse();
        bulkResponse.setThreepids(request.getThreepids().stream().map(list -> lookup(list.get(0), list.get(1), false))
            .filter(response -> StringUtils.isNotBlank(response.getAddress()) && StringUtils.isNotBlank(response.getMedium())
                && StringUtils.isNotBlank(response.getMxid())).map(response -> Arrays.asList(response.getMedium(), response.getAddress(),
                response.getMxid())).collect(Collectors.toList()));
        return bulkResponse;
    }

    /**
     * {@link AssociationService#create(Session, String)}
     */
    protected void createInternal(Session session, String mxid) {
        LocalDateTime expired = LocalDateTime.now().plusSeconds(getConfiguration().getAssociationTTL());
        getAssociationDao().insertOrIgnore(session.getAddress(), session.getMedium(), mxid, expired);
    }

    /**
     * {@link AssociationService#expire()}
     */
    protected void expireInternal() {
        getAssociationDao().expire();
    }
}
