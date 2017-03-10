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

    private TextField webadress = new TextField();
    private Button webButton = new Button("Go to site");
    private WebView webView = new WebView();
    private Label copyrightLabel = new Label("Copyright of Thomas Holmegaard");
    private Region region = new Region();
    private Label urlLabel = new Label("Indtast url:   ");
    private Button editButton = new Button("Edit bookmarks");

    //bookmark buttons
    private Button googleButton = new Button("Google");
    private Button youtubeButton = new Button("Youtube");
    private Button facebookButton = new Button("Facebook");
    private Button redditButton = new Button("Reddit");

    private SecondWindow secondWindow = new SecondWindow();
    private VBox vBoxLeft = new VBox(10);


    //Menu
    private Menu menu = new Menu("File");

    //login info
    private TextField loginInput = new TextField("tom");
    private PasswordField passInput = new PasswordField();
    private  Stage mainStage = new Stage();


    private Button refreshButton = new Button();


    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {



        BorderPane borderPane = new BorderPane();

        //center
        VBox vBoxCenter = new VBox(10);
        borderPane.setCenter(vBoxCenter);
        webView.setPrefWidth(1000.0);
        webView.setPrefHeight(900.0);
        vBoxCenter.getChildren().addAll(webView);

        //buttom
        HBox hboxButton = new HBox(10);
        hboxButton.setHgrow(region, Priority.ALWAYS);
        hboxButton.setPadding(new Insets(10,10,10,10));
        borderPane.setBottom(hboxButton);
        hboxButton.getChildren().addAll(copyrightLabel, region, editButton);

        //left
        borderPane.setLeft(vBoxLeft);
        vBoxLeft.setPadding(new Insets(10,10,10,10));
        vBoxLeft.getChildren().addAll(googleButton,youtubeButton,facebookButton,redditButton);

        //top
        HBox hboxTop = new HBox(10);
        borderPane.setTop(hboxTop);
        hboxTop.setPadding(new Insets(10,10,10,10));
        webadress.setPrefWidth(600.0);

        Region regionButton = new Region();
        hboxButton.setHgrow(regionButton, Priority.ALWAYS);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu);


        Image refreshImage = new Image(getClass().getResourceAsStream("refresh.png"));
        ImageView iw = new ImageView(refreshImage);
        iw.setFitHeight(100);
        iw.setFitWidth(100);
        iw.setPreserveRatio(true);

        refreshButton.setGraphic(iw);
     


        hboxTop.getChildren().addAll(urlLabel, webadress, refreshButton, regionButton, webButton);



        //all of the buttoms methodes
        onActionButtons();

        editBooksMarks();

        refreshButton.setOnAction(e-> {

           webView.getEngine().reload();

        });






        //layout



        Scene scene = new Scene(borderPane);

        mainStage.setScene(scene);


        //Login method
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

    public void onActionButtons(){

        googleButton.setOnAction(e-> webView.getEngine().load("http://google.dk"));
        youtubeButton.setOnAction(e-> webView.getEngine().load("http://youtube.com"));
        facebookButton.setOnAction(e-> webView.getEngine().load("http://facebook.com"));
        redditButton.setOnAction(e-> webView.getEngine().load("http://reddit.com"));


        webButton.setOnAction(e-> webView.getEngine().load("http://" + webadress.getText()));


        //below makes it possible to hit 'enter' instead of pressing the button to goto a specific site
        webadress.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                webView.getEngine().load("http://" + webadress.getText());
            }
        });


    }

    public void editBooksMarks(){


        editButton.setOnAction(e-> {
            secondWindow.edit();


        });
    }
    public void loginWindow(Stage primaryStage){

        if(loginInput.getText().equals("tom") && passInput.getText().equals("123")) {
            mainStage.show();
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

    

