package com.company;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import javafx.scene.layout.*;
import javafx.stage.Stage;



public class Main extends Application {


    //login info
    private TextField loginInput = new TextField("tom");
    private PasswordField passInput = new PasswordField();
    private Button loginButton = new Button("Login");

    private WebWindow webWindow = new WebWindow();

    private BorderPane borderPane = new BorderPane();

    //LAYOUT
    private HBox hBoxCenter = new HBox(10);
    private VBox vboxInsideHbox = new VBox(10);
    private HBox hBoxInVbox = new HBox();

//regions
private Region regionLeft = new Region();
private Region regionRight = new Region();
private Region regionButtom = new Region();
private Region regionTop = new Region();

//regions inside hbox
    private Region regRight = new Region();
    private Region regLeft = new Region();

    //top of borderPane
    private Menu menu = new Menu("Help");

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Login page");
        passInput.setPromptText("Password");
        passInput.setAlignment(Pos.CENTER);
        loginInput.setAlignment(Pos.CENTER);
        vboxInsideHbox.setPadding(new Insets(10,10,10,10));

        //hboxes
        HBox.setHgrow(regionLeft, Priority.ALWAYS);
        HBox.setHgrow(regionRight, Priority.ALWAYS);

        //vboxes
        VBox.setVgrow(regionButtom, Priority.ALWAYS);
        VBox.setVgrow(regionTop, Priority.ALWAYS);

        //hbox inside vbox
        HBox.setHgrow(regRight, Priority.ALWAYS);
        HBox.setHgrow(regLeft, Priority.ALWAYS);


        //getChildren
        hBoxCenter.getChildren().addAll(regionLeft, vboxInsideHbox, regionRight);
        hBoxInVbox.getChildren().addAll(regRight, loginButton, regLeft);
        vboxInsideHbox.getChildren().addAll(regionTop, loginInput, passInput, hBoxInVbox, regionButtom);

        //setscene
        borderPane.setCenter(hBoxCenter);
        MenuItem help = new Menu("Help");


        menu.getItems().add(help);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu);
        borderPane.setTop(menuBar);

        //methods
        loginButton.setOnAction(e-> loginWindow(primaryStage));

        passInput.setOnKeyPressed(e-> {
            if(e.getCode() == KeyCode.ENTER) {

                loginWindow(primaryStage);

            }
        });

        help.setOnAction(e->{

            Alert helpAlert = new Alert(Alert.AlertType.INFORMATION);
            helpAlert.setTitle("Information box");
            helpAlert.setHeaderText("Default username: tom ");
            helpAlert.setContentText("Password: 123");
            helpAlert.showAndWait();

        });

        Scene loginScene = new Scene(borderPane, 600 ,500);
        loginScene.getStylesheets().addAll(this.getClass().getResource("mainStyleSheet.css").toExternalForm());
        primaryStage.setScene(loginScene);
        primaryStage.show();


    }


    private void loginWindow(Stage primaryStage){

        if(loginInput.getText().equals("tom") && passInput.getText().equals("123")) {

            webWindow.openMainWindow();
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

    

