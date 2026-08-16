package com.isayev.accounting.ui.controller;

import com.isayev.accounting.ui.AccountingApp;
import com.isayev.accounting.ui.service.ApiClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Контроллер окна авторизации.
 */
@Slf4j
public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    private final ApiClient apiClient = new ApiClient();

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Введите логин и пароль");
            return;
        }

        statusLabel.setText("Подключение...");

        apiClient.loginAsync(username, password)
                .thenAccept(success -> Platform.runLater(() -> {
                    if (success) {
                        log.info("Login successful for user: {}", username);
                        openMainWindow();
                    } else {
                        statusLabel.setText("Неверный логин или пароль");
                        log.warn("Login failed for user: {}", username);
                    }
                }))
                .exceptionally(ex -> {
                    Platform.runLater(() -> statusLabel.setText("Ошибка подключения к серверу"));
                    log.error("Login error: {}", ex.getMessage());
                    return null;
                });
    }

    private void openMainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/main.fxml"));
            Parent root = loader.load();

            MainController mainController = loader.getController();
            mainController.setApiClient(apiClient);

            Scene scene = new Scene(root, 1200, 800);
            scene.getStylesheets().add(getClass().getResource("/ui/styles.css").toExternalForm());

            Stage stage = new Stage();
            stage.setTitle(AccountingApp.APP_TITLE);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();

            // Закрыть окно логина
            Stage loginStage = (Stage) usernameField.getScene().getWindow();
            loginStage.close();

        } catch (IOException e) {
            log.error("Failed to open main window: {}", e.getMessage());
            statusLabel.setText("Ошибка открытия главного окна");
        }
    }
}
