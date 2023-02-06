package com.example.databasejavafx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty wohnort = new SimpleStringProperty();

    public Person(IntegerProperty id, StringProperty name, StringProperty wohnort) {
        this.id = id;
        this.name = name;
        this.wohnort = wohnort;
    }

    public Person(int id, String name, String wohnort) {
        setId(id);
        setName(name);
        setWohnort(wohnort);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getWohnort() {
        return wohnort.get();
    }

    public StringProperty wohnortProperty() {
        return wohnort;
    }

    public void setWohnort(String wohnort) {
        this.wohnort.set(wohnort);
    }
}
