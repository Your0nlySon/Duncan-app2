package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Ethan Duncan
 */


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Items {

    private final SimpleStringProperty name;
    private final SimpleStringProperty serialNumber;
    private final SimpleStringProperty value;

    public Items (String n, String sN, String v) {
        this.name = new SimpleStringProperty(n);
        this.serialNumber = new SimpleStringProperty(sN);
        this.value = new SimpleStringProperty(v);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String n) {
        name.set(n);
    }
    public StringProperty nameProperty() {
        return name;
    }

    public String getSerialNumber() {
        return serialNumber.get();
    }
    public void setSerialNumber(String sN) {
        serialNumber.set(sN);
    }
    public StringProperty serialNumberProperty() {
        return serialNumber;
    }

    public String getValue() {
        return value.get();
    }
    public void setValue(String v) {
        value.set(v);
    }
    public StringProperty valueProperty() {
        return value;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return ((this.name.get().equals(((Items) obj).name.get())) && (this.serialNumber.get().equals(((Items) obj).serialNumber.get())) && (this.value.get().equals(((Items) obj).value.get())));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.name.get());
        return hash;
    }
}
