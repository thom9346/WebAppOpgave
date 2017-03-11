package com.company;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Created by Thomas on 09-03-2017.
 */
public class WebWindow {
    private Label copyrightLabel = new Label("Copyright of Thomas Holmegaard");
    private Region region = new Region();
    private Label urlLabel = new Label("Indtast url:   ");
    private VBox vBoxLeft = new VBox(10);
    private Button refreshButton = new Button();
    private TextField webadress = new TextField();
    private Button webButton = new Button("Go to site");
    private WebView webView = new WebView();

    //bookmark buttons
    private Button googleButton = new Button("Google");
    private Button youtubeButton = new Button("Youtube");
    private Button facebookButton = new Button("Facebook");
    private Button redditButton = new Button("Reddit");

    private Stage mainStage = new Stage();

    void webWindow() {
        mainStage.setTitle("Web app");

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
        hboxButton.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setBottom(hboxButton);
        hboxButton.getChildren().addAll(copyrightLabel, region);

        //left
        borderPane.setLeft(vBoxLeft);
        vBoxLeft.setPadding(new Insets(10, 10, 10, 10));
        vBoxLeft.getChildren().addAll(googleButton, youtubeButton, facebookButton, redditButton);

        //top
        HBox hboxTop = new HBox(10);
        borderPane.setTop(hboxTop);
        hboxTop.setPadding(new Insets(10, 10, 10, 10));
        webadress.setPrefWidth(600.0);

        Region regionButton = new Region();
        hboxButton.setHgrow(regionButton, Priority.ALWAYS);




        Image refreshImage = new Image(getClass().getResourceAsStream("refresh.png"), 20, 20, true, true);
        ImageView iw = new ImageView(refreshImage);


        refreshButton.setGraphic(iw);
        hboxTop.setSpacing(10);


        hboxTop.getChildren().addAll(urlLabel, webadress, refreshButton, regionButton, webButton);


        //all of the buttoms methodes
        onActionButtons();


        refreshButton.setOnAction(e -> {

            webView.getEngine().reload();

        });


        //layout


        Scene scene = new Scene(borderPane);

        mainStage.setScene(scene);
        mainStage.show();


    }


    public void onActionButtons() {

        googleButton.setOnAction(e -> webView.getEngine().load("http://google.dk"));
        youtubeButton.setOnAction(e -> webView.getEngine().load("http://youtube.com"));
        facebookButton.setOnAction(e -> webView.getEngine().load("http://facebook.com"));
        redditButton.setOnAction(e -> webView.getEngine().load("http://reddit.com"));


        webButton.setOnAction(e -> webView.getEngine().load("http://" + webadress.getText()));


        //below makes it possible to hit 'enter' instead of pressing the button to goto a specific site
        webadress.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                webView.getEngine().load("http://" + webadress.getText());
            }
        });


    }
}

