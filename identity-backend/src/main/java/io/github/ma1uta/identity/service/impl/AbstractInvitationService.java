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

import io.github.ma1uta.identity.configuration.InvitationServiceConfiguration;
import io.github.ma1uta.identity.dao.InvitationDao;
import io.github.ma1uta.identity.model.Invitation;
import io.github.ma1uta.identity.service.AssociationService;
import io.github.ma1uta.identity.service.InvitationService;
import io.github.ma1uta.identity.service.KeyService;
import io.github.ma1uta.identity.service.RestService;
import io.github.ma1uta.identity.service.SerializerService;
import io.github.ma1uta.jeon.exception.MatrixException;
import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.Id;
import io.github.ma1uta.matrix.identity.model.invitation.InvitationResponse;
import io.github.ma1uta.matrix.identity.model.lookup.LookupResponse;
import io.github.ma1uta.matrix.server.model.bind.Invite;
import io.github.ma1uta.matrix.server.model.bind.OnBindRequest;
import io.github.ma1uta.matrix.server.model.bind.Signed;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Default implementation.
 * <p/>
 * There are default implementation for all methods of the {@link InvitationService}.
 * <p/>
 * You can create you own class for example with annotation @Transactional (jpa) or another and invoke the same method with suffix "Internal".
 * <pre>
 * {@code
 *     @literal @Service
 *     public class MyInvitationService extends AbstractInvitationService {
 *         ...
 *         @literal @Override
 *         @literal @Transactional
 *         @literal @MyFavouriteAnnotation
 *         public String create(String address, String medium, String roomId, String sender) {
 *             // wrap next link to transaction via annotation or code.
 *             return super.createInternal(address, medium, roomId, sender);
 *         }
 *         ...
 *     }
 * }
 * </pre>
 */
public abstract class AbstractInvitationService implements InvitationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractInvitationService.class);

    private final AssociationService associationService;
    private final KeyService keyService;
    private final InvitationDao invitationDao;
    private final SerializerService serializer;
    private final InvitationServiceConfiguration configuration;
    private final RestService restService;

    public AbstractInvitationService(AssociationService associationService, KeyService keyService,
                                     InvitationDao invitationDao, SerializerService serializer,
                                     InvitationServiceConfiguration configuration, RestService restService) {
        this.associationService = associationService;
        this.keyService = keyService;
        this.invitationDao = invitationDao;
        this.serializer = serializer;
        this.configuration = configuration;
        this.restService = restService;
    }

    protected AssociationService getAssociationService() {
        return associationService;
    }

    protected KeyService getKeyService() {
        return keyService;
    }

    protected InvitationDao getInvitationDao() {
        return invitationDao;
    }

    protected SerializerService getSerializer() {
        return serializer;
    }

    protected InvitationServiceConfiguration getConfiguration() {
        return configuration;
    }

    protected RestService getRestService() {
        return restService;
    }

    /**
     * {@link InvitationService#create(String, String, String, String)}
     */
    protected InvitationResponse createInternal(String address, String medium, String roomId, String sender) {
        if (!"email".equals(medium)) {
            throw new MatrixException(ErrorResponse.Code.M_BAD_JSON, "Wrong medium.", 400);
        }
        int index = address.indexOf('@');
        if (index == -1) {
            throw new MatrixException(ErrorResponse.Code.M_BAD_JSON, "Wrong address", 400);
        }
        LookupResponse lookup = getAssociationService().lookup(medium, address, false);
        if (lookup.getMxid() != null) {
            throw new MatrixException(ErrorResponse.Code.M_THREEPID_IN_USE, "Medium and address are used.", 400);
        }
        String token = UUID.randomUUID().toString();
        String ephemeralKey;
        String longTermKey;
        try {
            ephemeralKey = getKeyService().nextKey(false);
            longTermKey = getKeyService().nextKey(true);
        } catch (KeyStoreException | UnrecoverableKeyException | OperatorCreationException | CertificateException | IOException | NoSuchAlgorithmException e) {
            String msg = "Failed get long-term or short-term keys";
            LOGGER.error(msg, e);
            throw new MatrixException(MatrixException.M_INTERNAL, "msg");
        }
        String displayName = address.substring(0, index);

        getInvitationDao().insert(address, medium, roomId, sender, token, Arrays.asList(ephemeralKey, longTermKey), displayName);

        InvitationResponse response = new InvitationResponse();
        response.setDisplayName(displayName);
        response.setToken(token);
        response.setPublicKeys(Arrays.asList(ephemeralKey, longTermKey));
        return response;
    }

    /**
     * {@link InvitationService#sendInvite(String, String, String)}
     */
    protected void sendInviteInternal(String address, String medium, String mxid) {

        List<Invitation> invitationList = getInvitationDao().findByAddressMedium(address, medium);

        if (!invitationList.isEmpty()) {
            OnBindRequest request = new OnBindRequest();
            request.setAddress(address);
            request.setMedium(medium);
            request.setMxid(mxid);
            request.setInvites(invitationList.stream().map(item -> {
                Invite invite = new Invite();
                invite.setAddress(item.getAddress());
                invite.setMedium(item.getMedium());
                invite.setMxid(mxid);
                invite.setRoomId(item.getRoomId());
                invite.setSender(item.getSender());

                Signed signed = new Signed();
                signed.setMxid(mxid);
                signed.setToken(item.getToken());
                try {
                    Optional<Map<String, Map<String, String>>> signature = getKeyService().sign(getSerializer().serialize(signed), true);
                    signed.setSignatures(
                        signature.orElseThrow(() -> new MatrixException(MatrixException.M_INTERNAL, "Cannot find signature")));
                } catch (CertificateException | UnrecoverableKeyException | NoSuchAlgorithmException | OperatorCreationException
                    | KeyStoreException | SignatureException | IOException | InvalidKeyException e) {
                    String msg = "Failed sign invites";
                    LOGGER.error(msg, e);
                    throw new MatrixException(MatrixException.M_INTERNAL, msg);
                }
                invite.setSigned(signed);
                return invite;
            }).collect(Collectors.toList()));
            String domain = Id.domain(mxid);
            String bindUrl = String
                .format("%s://%s:%s/%s", getConfiguration().getOnBindProtocol(), domain, getConfiguration().getOnBindPort(),
                    getConfiguration().getOnBindUrl());
            getRestService().get(bindUrl, request);
        }
    }
}
