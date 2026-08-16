package com.isayev.accounting.config;

import com.isayev.accounting.security.DigitalSignatureService;
import com.isayev.accounting.security.EcdsaSignatureService;
import com.isayev.accounting.security.KazakhstanEcpService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Конфигурация провайдера ЭЦП.
 * Поддерживает:
 * - ecdsa (по умолчанию) — программная подпись
 * - kazakhstan — подпись через НУЦ РК (KalkanCrypt + токен)
 */
@Slf4j
public class EcpConfig {

    @ConfigProperty(name = "accounting.ecp.provider", defaultValue = "ecdsa")
    String ecpProvider;

    @Produces
    @ApplicationScoped
    public DigitalSignatureService digitalSignatureService() {
        log.info("Initializing ECP provider: {}", ecpProvider);
        
        switch (ecpProvider.toLowerCase()) {
            case "kazakhstan":
                log.info("Using Kazakhstan ECP (GOST) via KalkanCrypt");
                return new KazakhstanEcpService();
            case "ecdsa":
            default:
                log.info("Using ECDSA (software signing)");
                return new EcdsaSignatureService();
        }
    }
}
