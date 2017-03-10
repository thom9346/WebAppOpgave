package com.company;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Thomas on 09-03-2017.
 */
public class SecondWindow {

    private BorderPane borderPane = new BorderPane();
    private TextField whatToEdit = new TextField();
    private Button button = new Button("Add button");
     Button newButton;

    public void edit(){




        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(whatToEdit,button);

        button.setOnAction(e-> {

            newButton = new Button(whatToEdit.getText());


        });

        borderPane.setCenter(hBox);



        Scene scene = new Scene(borderPane);
        Stage window = new Stage();
        window.setScene(scene);
        window.show();

    }

}
