package com.example.databasejavafx;



import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<Person> tvList;

    @FXML
    private TableColumn<Person, Integer> idColumn;

    @FXML
    private TableColumn<Person, String> nameColumn;

    @FXML
    private TableColumn<Person, String> wohnortColumn;

    @FXML
    private void btSubmitOnAction(ActionEvent event) {
        insert();
    }

    private ObservableList<Person> personenList = FXCollections.observableArrayList();

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

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        wohnortColumn.setCellValueFactory(new PropertyValueFactory<>("wohnort"));



        personenList.addAll(SqlDatabase.getAllPersons());
        tvList.setItems(personenList);
    }


    private void insert() {
        try {
            SqlDatabase.insert(Integer.parseInt(tfID.getText()), tfName.getText(), tfWohnort.getText());
            add();
            refresh();
        }catch (Exception ex){
            showError();
            refresh();
        }
    }

    private void add() {
        personenList.add(new Person(Integer.parseInt(tfID.getText()), tfName.getText(), tfWohnort.getText()));
    }

    private void refresh() {
        tfID.setText("");
        tfName.setText("");
        tfWohnort.setText("");
    }

    private void showError(){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Ein Fehler ist aufgetreten!");
        alert.showAndWait();
    }
}