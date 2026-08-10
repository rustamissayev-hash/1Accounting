package com.isayev.accounting.messaging;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import java.util.Map;

@Slf4j
@ApplicationScoped
public class DocumentKafkaProcessor {

    @Incoming("document-incoming")
    @Outgoing("document-processed")
    public Map<String, Object> processDocument(Map<String, Object> document) {
        log.info("Processing document via Kafka: {}", document.get("id"));
        document.put("status", "processed");
        document.put("processedAt", java.time.LocalDateTime.now().toString());
        return document;
    }

    @Incoming("document-signatures")
    public void processSignature(Map<String, Object> signatureEvent) {
        log.info("Processing signature event for document: {}", signatureEvent.get("documentId"));
    }
}
