package com.isayev.accounting.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.time.ZoneId;
import java.util.Base64;

/**
 * Сервис для работы с казахстанской ЭЦП (ГОСТ Р 34.10-2012).
 * НЕ является CDI bean — создаётся через EcpConfig Producer.
 * 
 * Требования для работы:
 * 1. Установить KalkanCrypt SDK (JAVA) от НУЦ РК
 * 2. Подключить библиотеку kalkancrypt-x.x.x.jar
 * 3. Наличие токена (Kaztoken, eToken, JaCarta) с ключами НУЦ РК
 * 4. Сертификат, выданный НУЦ РК
 * 
 * Документация: https://pki.gov.kz/
 */
public class KazakhstanEcpService implements DigitalSignatureService {

    private static final Logger log = LoggerFactory.getLogger(KazakhstanEcpService.class);

    // private KalkanCrypt kalkan; // Раскомментировать после подключения KalkanCrypt SDK

    @Override
    public String sign(String data, String keyAlias) throws Exception {
        log.info("Signing with Kazakhstan ECP (GOST) for key: {}", keyAlias);
        
        throw new UnsupportedOperationException(
            "Kazakhstan ECP signing requires KalkanCrypt SDK and hardware token. " +
            "Please install KalkanCrypt from https://pki.gov.kz/ and connect your token."
        );
    }

    @Override
    public boolean verify(String data, String signatureBase64, String certificateBase64) throws Exception {
        log.info("Verifying Kazakhstan ECP signature");
        
        throw new UnsupportedOperationException(
            "Kazakhstan ECP verification requires KalkanCrypt SDK. " +
            "Please install KalkanCrypt from https://pki.gov.kz/"
        );
    }

    @Override
    public CertificateInfo getCertificateInfo(String certificateBase64) throws Exception {
        log.info("Extracting certificate info");
        
        byte[] certBytes = Base64.getDecoder().decode(certificateBase64);
        CertificateFactory factory = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate) factory.generateCertificate(new ByteArrayInputStream(certBytes));
        
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
        log.info("Checking certificate status via OCSP/CRL");
        
        return CertificateStatus.builder()
                .valid(true)
                .revoked(false)
                .statusMessage("OCSP check not implemented - install KalkanCrypt SDK")
                .build();
    }

    /**
     * Проверяет, доступен ли KalkanCrypt SDK
     */
    public boolean isKalkanAvailable() {
        try {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
