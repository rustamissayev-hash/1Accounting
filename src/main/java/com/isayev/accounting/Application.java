package com.isayev.accounting;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@QuarkusMain
public class Application {

    public static void main(String[] args) {
        log.info("╔══════════════════════════════════════════════╗");
        log.info("║          1Accounting (1A) Starting...         ║");
        log.info("║  Open-source Accounting & Reporting System   ║");
        log.info("╚══════════════════════════════════════════════╝");
        Quarkus.run(args);
    }
}
