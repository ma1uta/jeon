package io.github.ma1uta.jidentity.key

import io.github.ma1uta.jidentity.IdentityProperties
import org.bouncycastle.asn1.ASN1ObjectIdentifier
import org.bouncycastle.asn1.x500.X500Name
import org.bouncycastle.asn1.x509.BasicConstraints
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder
import java.math.BigInteger
import java.security.Key
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.SecureRandom
import java.security.Security
import java.util.*

class KeyGenerateSelfCertificate(private val properties: IdentityProperties) : KeyGenerator {
    override fun generate(keyStore: KeyStore, parentPrivateKey: Key?, keyId: String, password: CharArray) {
        val pairGenerator = KeyPairGenerator.getInstance("Ed25519")
        val random = SecureRandom(properties.selfKeyGenerator.secureRandomSeed.toByteArray())

        val notBefore = Date()
        val notAfter = Date(notBefore.time + properties.selfKeyGenerator.certificateValidTs)
        val keyPair = pairGenerator.generateKeyPair()
        val privateKey = if (parentPrivateKey == null) keyPair.private else parentPrivateKey as PrivateKey!!
        val contentSigner = JcaContentSignerBuilder("Ed25519").build(privateKey)

        val provider = BouncyCastleProvider()
        Security.addProvider(provider)

        val certificateBuilder = JcaX509v3CertificateBuilder(
                X500Name(properties.selfKeyGenerator.issuerName),
                BigInteger(properties.selfKeyGenerator.serialNumberLength, random),
                notBefore,
                notAfter,
                X500Name("CN=${properties.selfKeyGenerator.issuerName}"),
                keyPair.public)

        val basicConstraints = BasicConstraints(false)

        certificateBuilder.addExtension(ASN1ObjectIdentifier("2.5.29.19"), true, basicConstraints)

        val certificate = JcaX509CertificateConverter().setProvider(provider).getCertificate(certificateBuilder.build(contentSigner))

        keyStore.setKeyEntry(keyId, keyPair.private, password, arrayOf(certificate))
    }
}
