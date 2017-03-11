package com.company;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import javafx.stage.Stage;



public class Main extends Application {


    //login info
    private TextField loginInput = new TextField("tom");
    private PasswordField passInput = new PasswordField();

    private WebWindow webWindow = new WebWindow();


    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Login page");
        VBox vBox = new VBox(10);
        passInput.setPromptText("Password");
        Button loginButton = new Button("Login");



            loginButton.setOnAction(e-> loginWindow(primaryStage));

            passInput.setOnKeyPressed(e-> {
                if(e.getCode() == KeyCode.ENTER) {

                    loginWindow(primaryStage);

                }
            });

        vBox.getChildren().addAll(loginInput, passInput, loginButton);

        Scene loginScene = new Scene(vBox);
        primaryStage.setScene(loginScene);
        primaryStage.show();




        // primaryStage.setScene(scene);


    }


    public void loginWindow(Stage primaryStage){

        if(loginInput.getText().equals("tom") && passInput.getText().equals("123")) {

            webWindow.webWindow();
            primaryStage.close();

        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Wrong username/password!");
            alert.setContentText("Press 'OK' to try again");

            alert.showAndWait();

        }
    }


    }

    

