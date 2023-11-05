package com.example.invetorysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.*;



import java.io.IOException;

public class HelloApplication extends Application {
    double x = 0;
    double y = 0;
    @Override
    public void start(Stage stage) throws IOException {


        stage.initStyle(StageStyle.UNDECORATED); // Remove Stage Buttons (Minimize, maximize, close)
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
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

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        // Make it appear it center of screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);


    }

    public static void main(String[] args) {
        launch();
    }
}