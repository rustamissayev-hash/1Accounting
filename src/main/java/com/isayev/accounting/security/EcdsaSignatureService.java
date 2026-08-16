package com.isayev.accounting.security;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.time.ZoneId;
import java.util.Base64;

/**
 * Реализация цифровой подписи через ECDSA (международный стандарт).
 * Используется для тестирования и для систем без казахстанских токенов.
 */
@Slf4j
@ApplicationScoped
public class EcdsaSignatureService implements DigitalSignatureService {

    private static final String ALGORITHM = "EC";
    private static final String SIGNATURE_ALGORITHM = "SHA256withECDSA";
    private static final String CURVE = "secp256r1";

    @Override
    public String sign(String data, String keyAlias) throws Exception {
        KeyPair keyPair = generateKeyPair();
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(keyPair.getPrivate());
        signature.update(data.getBytes());
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    @Override
    public boolean verify(String data, String signatureBase64, String publicKeyBase64) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.update(data.getBytes());
        return signature.verify(Base64.getDecoder().decode(signatureBase64));
    }

    @Override
    public CertificateInfo getCertificateInfo(String certificateBase64) throws Exception {
        byte[] certBytes = Base64.getDecoder().decode(certificateBase64);
        java.security.cert.CertificateFactory factory = java.security.cert.CertificateFactory.getInstance("X.509");
        java.security.cert.X509Certificate cert = (java.security.cert.X509Certificate) factory.generateCertificate(
                new java.io.ByteArrayInputStream(certBytes));
        
        return CertificateInfo.builder()
                .subjectName(cert.getSubjectX500Principal().getName())
                .issuerName(cert.getIssuerX500Principal().getName())
                .serialNumber(cert.getSerialNumber().toString())
                .validFrom(cert.getNotBefore().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .validTo(cert.getNotAfter().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .signatureAlgorithm(cert.getSigAlgName())
                .publicKeyAlgorithm(cert.getPublicKey().getAlgorithm())
                .build();
    }

    @Override
    public CertificateStatus checkCertificateStatus(String certificateBase64) throws Exception {
        return CertificateStatus.builder()
                .valid(true)
                .revoked(false)
                .statusMessage("Self-signed certificate - no OCSP")
                .build();
    }

    public KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        ECGenParameterSpec ecSpec = new ECGenParameterSpec(CURVE);
        keyGen.initialize(ecSpec, new SecureRandom());
        return keyGen.generateKeyPair();
    }

    public String encodePublicKey(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    public String encodePrivateKey(PrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }
}
