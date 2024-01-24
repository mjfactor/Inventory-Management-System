package com.example.invetorysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class main extends Application {
    double x = 0;
    double y = 0;
    @Override
    public void start(Stage stage) throws IOException {

        stage.initStyle(StageStyle.UNDECORATED); // Remove Stage Buttons (Minimize, maximize, close)
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 752, 551);

        scene.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
            scene.setCursor(Cursor.CLOSED_HAND);
        });
        scene.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
            stage.setOpacity(.9);
        });
        scene.setOnMouseReleased(mouseEvent -> {
            stage.setOpacity(1);
            scene.setCursor(Cursor.DEFAULT);
        });

        stage.setTitle("Inventory System");
        stage.setScene(scene);

        stage.show();

        // Make it appear it center of screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

    }

    public static void main(String[] args) {
        File file = new File(System.getProperty("user.home"), "inventory_system.lock");
        try {
            // Try to get the channel of the file
            FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
            // Try to get the lock
            FileLock lock = channel.tryLock();
            if(lock == null) {
                // If the lock is null, another instance of the application is running
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Another instance of the application is running");
                System.exit(1);
            }
            // If the lock is not null, you can start your application
            launch(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}