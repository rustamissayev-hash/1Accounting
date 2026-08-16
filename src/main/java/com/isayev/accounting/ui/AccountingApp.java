package com.isayev.accounting.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * Главный класс JavaFX приложения 1Accounting Desktop.
 * Архитектура: JavaFX Client ↔ REST API ↔ Quarkus Backend
 */
@Slf4j
public class AccountingApp extends Application {

    public static final String APP_TITLE = "1Accounting — Бухгалтерия и Отчётность";
    public static final String VERSION = "1.0.0-SNAPSHOT";

    @Override
    public void start(Stage primaryStage) throws Exception {
        log.info("Starting {} v{}", APP_TITLE, VERSION);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/login.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/ui/styles.css").toExternalForm());

        primaryStage.setTitle(APP_TITLE + " | Вход в систему");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
