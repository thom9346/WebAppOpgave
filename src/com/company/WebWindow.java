package com.company;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.Arrays;

/**
 * Created by Thomas on 09-03-2017.
 */

public class WebWindow {
    private Label copyrightLabel = new Label("©KEA, Thomas Holmegaard");
    private Region region = new Region();
    private Label urlLabel = new Label("Indtast url:   ");
    private VBox vBoxLeft = new VBox(10);
    private TextField webadress = new TextField();
    private Button webButton = new Button("Go to site");
    private Button favButton = new Button("Bookmark page");
    private WebView webView = new WebView();
    private Button addedButton;


    //bookmark buttons
    private Button googleButton = new Button("Google");
    private Button youtubeButton = new Button("Youtube");
    private Button facebookButton = new Button("Facebook");
    private Button redditButton = new Button("Reddit");

    //Button with images
    private Button refreshButton = new Button();
    private Button rightArrow = new Button();
    private Button leftArrow = new Button();


    private Stage mainStage = new Stage();

private BookmarkName bookmarkName = new BookmarkName();

    void openMainWindow() {
        mainStage.setTitle("Web app");

        BorderPane borderPane = new BorderPane();

        //center
        VBox vBoxCenter = new VBox(10);
        borderPane.setCenter(vBoxCenter);
        webView.setPrefWidth(1000.0);
        webView.setPrefHeight(900.0);
        webView.getEngine().load("http://dr.dk");
        vBoxCenter.getChildren().addAll(webView);

        //buttom
        HBox hboxButton = new HBox(10);
        hboxButton.setHgrow(region, Priority.ALWAYS);
        hboxButton.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setBottom(hboxButton);
        hboxButton.getChildren().addAll(copyrightLabel, region,favButton);



        Region regionButton = new Region();
        hboxButton.setHgrow(regionButton, Priority.ALWAYS);

        //left
        borderPane.setLeft(vBoxLeft);
        vBoxLeft.setPadding(new Insets(10, 10, 10, 10));
        vBoxLeft.getChildren().addAll(googleButton, youtubeButton, facebookButton, redditButton);


        favButton.setOnAction(e-> {


            String buttonText = webView.getEngine().getLocation();
            String[] domainPart = buttonText.split("/");
//^ cuts out the ID link behind the domain link so the buttom doesnt show the exact link to the destination



          //  bookmarkName.chooseName(); This method will allow you to choose a name for the bookmarks, BUT i CANNOT iterate through them more than once.
            // i'd liek to know why..

            addedButton = new Button(domainPart[2]);
            vBoxLeft.getChildren().add(addedButton);


            addedButton.setOnAction(a-> webView.getEngine().load(buttonText));

        });



        //top
        HBox hboxTop = new HBox(10);
        borderPane.setTop(hboxTop);
        hboxTop.setPadding(new Insets(10, 10, 10, 10));
        webadress.setPrefWidth(600.0);







        Image refreshImage = new Image(getClass().getResourceAsStream("refresh.png"), 20, 20, true, true);
        ImageView iw = new ImageView(refreshImage);
        refreshButton.setGraphic(iw);

        Image leftArrowPic = new Image(getClass().getResourceAsStream("leftarrow2.png"), 20,20,true,true);
        ImageView leftArrowImage = new ImageView(leftArrowPic);
        leftArrow.setGraphic(leftArrowImage);

        Image rightArrowPic = new Image(getClass().getResourceAsStream("rightArrow.png"), 20,20,true,true);
        ImageView rightArrowImage = new ImageView(rightArrowPic);
        rightArrow.setGraphic(rightArrowImage);



        hboxTop.getChildren().addAll(urlLabel, leftArrow, rightArrow, webadress, refreshButton, regionButton, webButton);


        //all of the buttoms methodes
        onActionButtons();


        refreshButton.setOnAction(e -> {

            webView.getEngine().reload();

        });


        //layout


        Scene scene = new Scene(borderPane);
        scene.getStylesheets().addAll(this.getClass().getResource("WebWindowStylesheet.css").toExternalForm());

        mainStage.setScene(scene);
        mainStage.show();


    }


    private void onActionButtons() {

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




        // below is back and fourth actions

        leftArrow.setOnAction(e-> {

            WebHistory history = webView.getEngine().getHistory();
            history.go(-1);

            //code below is taken from STACKPANE.COM and is NOT original. It's therefore not used in this assigmenets but is an exmaple of how it could also be done
//           final WebHistory history = webView.getEngine().getHistory(); //the final keyword
//            ObservableList<WebHistory.Entry> entryList = history.getEntries();
//            int currentIndex = history.getCurrentIndex();
//
//            Platform.runLater(() ->
//            {
//                history.go(entryList.size() > 1
//                        && currentIndex > 0
//                        ? -1
//                        : 0);
            });



        rightArrow.setOnAction(e->{

            WebHistory history = webView.getEngine().getHistory();
            history.go(1);

       });


    }
}

