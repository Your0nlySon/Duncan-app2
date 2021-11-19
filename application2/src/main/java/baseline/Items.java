package baseline;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
}
