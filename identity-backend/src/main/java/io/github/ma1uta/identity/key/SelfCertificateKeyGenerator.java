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

package io.github.ma1uta.identity.key;


import io.github.ma1uta.identity.configuration.SelfKeyGeneratorConfiguration;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Generator for new keys.
 * <p/>
 * Also generate certificate for new key pair and store they in the key store.
 */
public class SelfCertificateKeyGenerator implements KeyGenerator {

    private final byte[] secureRandomSeed;
    private final SelfKeyGeneratorConfiguration configuration;

    public SelfCertificateKeyGenerator(String secureRandomSeed, SelfKeyGeneratorConfiguration configuration) {
        this.secureRandomSeed = secureRandomSeed.getBytes(StandardCharsets.UTF_8);
        this.configuration = configuration;
    }

    public SelfKeyGeneratorConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public void generate(KeyStoreProvider keyStoreProvider, Key parentPrivateKey, String keyId) throws NoSuchAlgorithmException,
        OperatorCreationException, IOException, CertificateException, KeyStoreException {
        KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance("Curve25519");
        SecureRandom secureRandom = new SecureRandom(this.secureRandomSeed);

        LocalDateTime notBefore = LocalDateTime.now();
        LocalDateTime notAfter = notBefore.plus(Duration.ofSeconds(getConfiguration().getCertificateValidTs()));

        KeyPair keyPair = pairGenerator.generateKeyPair();
        PrivateKey rootPrivateKey = parentPrivateKey instanceof PrivateKey ? (PrivateKey) parentPrivateKey : keyPair.getPrivate();
        ContentSigner signer = new JcaContentSignerBuilder("Ed25519").build(rootPrivateKey);

        BouncyCastleProvider provider = new BouncyCastleProvider();
        Security.addProvider(provider);

        JcaX509v3CertificateBuilder certificateBuilder = new JcaX509v3CertificateBuilder(
            new X500Name(getConfiguration().getIssuerName()),
            new BigInteger(getConfiguration().getSerialNumberLength(), secureRandom),
            Date.from(Instant.from(notBefore)),
            Date.from(Instant.from(notAfter)),
            new X500Name(String.format("CN=%s", getConfiguration().getIssuerName())),
            keyPair.getPublic()
        );

        certificateBuilder.addExtension(new ASN1ObjectIdentifier("2.5.29.19"), true, new BasicConstraints(false));

        X509Certificate certificate = new JcaX509CertificateConverter().setProvider(provider)
            .getCertificate(certificateBuilder.build(signer));

        keyStoreProvider.addKey(keyId, keyPair, certificate);
    }
}
