package com.isayev.accounting.i18n;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@ApplicationScoped
public class I18nService {

    private static final Map<String, Map<String, String>> MESSAGES = Map.of(
        "ru", Map.of(
            "app.title", "1Accounting - Бухгалтерия",
            "document.created", "Документ создан",
            "document.posted", "Документ проведен",
            "document.signed", "Документ подписан",
            "error.notFound", "Не найдено",
            "error.validation", "Ошибка валидации"
        ),
        "kz", Map.of(
            "app.title", "1Accounting - Бухгалтерия",
            "document.created", "Құжат жасалды",
            "document.posted", "Құжат өткізілді",
            "document.signed", "Құжат қол қойылды",
            "error.notFound", "Табылмады",
            "error.validation", "Тексеру қатесі"
        ),
        "en", Map.of(
            "app.title", "1Accounting - Accounting System",
            "document.created", "Document created",
            "document.posted", "Document posted",
            "document.signed", "Document signed",
            "error.notFound", "Not found",
            "error.validation", "Validation error"
        )
    );

    public String getMessage(String key, String language) {
        Map<String, String> langMessages = MESSAGES.getOrDefault(language, MESSAGES.get("ru"));
        return langMessages.getOrDefault(key, key);
    }

    public boolean isSupportedLanguage(String language) {
        return MESSAGES.containsKey(language);
    }
}
