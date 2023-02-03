package com.example.databasejavafx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.SqlDatabase;

import java.io.IOException;

public class PersonC {
    @FXML
    private TextField tfID;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfWohnort;

    @FXML
    private Button btSubmit;

    @FXML
    private TableView<?> tvList;

    @FXML
    private TableColumn<?, ?> tcID;

    @FXML
    private TableColumn<?, ?> tcName;

    @FXML
    private TableColumn<?, ?> tcWohnort;

    @FXML
    private void btSubmitOnAction(ActionEvent event) {
        insert();
    }

    public static void show(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(PersonC.class.getResource("insertWindow.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Person");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }


    private void insert(){
        SqlDatabase.insert(tfID.getText(), tfName.getText(), tfWohnort.getText());
        refresh();
    }

    private void refresh(){

    }
}