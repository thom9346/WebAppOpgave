package com.company;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Thomas on 16-03-2017.
 */

//IMPORTANT NOTE: THIS CLASS IS NEVER USED, AS IT CAN'T ITERATE MORE THAN ONCE. DID NOT FIND THE SOLUTION FOR THIS ONE

public class BookmarkName {

    private  VBox vBox = new VBox(10);
    private TextField textField = new TextField();
    private Button button = new Button("Submit");
    private Stage bookmarkStage = new Stage();

    String bookmarkName;

        void chooseName(){



        button.setOnAction(e->{
            bookmarkName = textField.getText();
            bookmarkStage.close();
        });

        textField.setText("");
        vBox.getChildren().addAll(textField, button);
        Scene scene = new Scene(vBox);
        bookmarkStage.setScene(scene);
        bookmarkStage.showAndWait();

    }

}
