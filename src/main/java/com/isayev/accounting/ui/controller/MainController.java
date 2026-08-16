package com.isayev.accounting.ui.controller;

import com.isayev.accounting.ui.service.ApiClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Контроллер главного окна приложения.
 */
@Slf4j
public class MainController implements Initializable {

    @FXML private Label statusLabel;
    @FXML private Label userLabel;
    @FXML private Label timeLabel;
    @FXML private TreeView<String> moduleTree;
    @FXML private ListView<String> activityList;

    private ApiClient apiClient;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initModuleTree();
        updateTime();
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
        statusLabel.setText("Подключено к серверу");
    }

    private void initModuleTree() {
        TreeItem<String> root = new TreeItem<>("Модули 1Accounting");
        root.setExpanded(true);

        TreeItem<String> accounting = new TreeItem<>("📘 Бухгалтерский учёт");
        accounting.getChildren().addAll(
                new TreeItem<>("Учётная политика"),
                new TreeItem<>("План счетов"),
                new TreeItem<>("Типовые проводки")
        );

        TreeItem<String> tax = new TreeItem<>("📗 Налоговый учёт");
        tax.getChildren().addAll(
                new TreeItem<>("Налоговая политика"),
                new TreeItem<>("Формы отчётов"),
                new TreeItem<>("Календарь событий")
        );

        TreeItem<String> docs = new TreeItem<>("📄 Документы");
        docs.getChildren().addAll(
                new TreeItem<>("Входящие"),
                new TreeItem<>("Обработанные"),
                new TreeItem<>("ЭЦП и подписание")
        );

        TreeItem<String> reports = new TreeItem<>("📊 Отчёты");
        reports.getChildren().addAll(
                new TreeItem<>("Управленческие отчёты"),
                new TreeItem<>("Налоговые декларации"),
                new TreeItem<>("Аналитика")
        );

        TreeItem<String> settings = new TreeItem<>("⚙ Настройки");
        settings.getChildren().addAll(
                new TreeItem<>("Права доступа"),
                new TreeItem<>("Язык интерфейса"),
                new TreeItem<>("Интеграции")
        );

        root.getChildren().addAll(accounting, tax, docs, reports, settings);
        moduleTree.setRoot(root);
        moduleTree.setShowRoot(false);

        moduleTree.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal.isLeaf()) {
                log.info("Selected module: {}", newVal.getValue());
                activityList.getItems().add(0, "Открыт модуль: " + newVal.getValue());
            }
        });
    }

    private void updateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        timeLabel.setText(LocalDateTime.now().format(formatter));
    }

    @FXML
    private void handleRefresh() {
        if (apiClient != null) {
            apiClient.healthCheckAsync()
                    .thenAccept(ok -> Platform.runLater(() -> {
                        statusLabel.setText(ok ? "Сервер доступен" : "Сервер недоступен");
                        activityList.getItems().add(0, "Проверка связи: " + (ok ? "OK" : "Ошибка"));
                    }));
        }
    }

    @FXML
    private void handleExit() {
        Platform.exit();
    }
}
