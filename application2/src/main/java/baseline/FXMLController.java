package baseline;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;



public class FXMLController {

    @FXML private VBox vBox;
    @FXML private TextField searchField;
    @FXML private TableView<Items> tableList;
    @FXML private TableColumn<Items, String> name;
    @FXML private TableColumn<Items, String> serialNumber;
    @FXML private TableColumn<Items, String> value;
    @FXML private TextField nameField;
    @FXML private TextField serialField;
    @FXML private TextField valueField;
    @FXML private Label viewLabel;

    ObservableList<Items> list = FXCollections.observableArrayList();

    public void initialize() {
        viewLabel.setText("Normal");

        name.setCellValueFactory(new PropertyValueFactory<Items, String>("name"));
        serialNumber.setCellValueFactory(new PropertyValueFactory<Items, String>("serialNumber"));
        value.setCellValueFactory(new PropertyValueFactory<Items, String>("value"));

        list.add(new Items("Xbox Series X", "A-XB1-24A-XY3", "1499.00"));
        list.add(new Items("Samsung TV", "S-40A-ZBD-E47", "599.99"));

        tableList.setItems(list);
        
        tableList.setEditable(true);
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        serialNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        value.setCellFactory(TextFieldTableCell.forTableColumn());

        tableList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    public void openList(ActionEvent event) throws IOException, ParseException {

        try {
            fileTSV fT = new fileTSV();
            fileHTML fH = new fileHTML();
            fileJSON fJ = new fileJSON();

            //create file chooser and allow it to save the following extenstions:
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open List");
            //tsv(txt), HTML, and JSON
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");

            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.getExtensionFilters().add(extFilter1);
            fileChooser.getExtensionFilters().add(extFilter2);

            Stage stage = (Stage) vBox.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);

            String fileExt = FilenameUtils.getExtension(String.valueOf(file));

            tableList.setItems(list);
            tableList.getItems().clear();
            if (fileExt.matches("txt")) {
                fT.TSVToList(file, list);
            } else if (fileExt.matches("html")) {
                fH.HTMLToList(file, list);
            } else if (fileExt.matches("json")) {
                fJ.JSONToList(file, list);
            }
        } catch (UnsupportedOperationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You can't open list in search view. Try again in normal view");
            alert.showAndWait();
        }
    }

    public void saveList(ActionEvent event) throws IOException {

        try {
            fileTSV fT = new fileTSV();
            fileHTML fH = new fileHTML();
            fileJSON fJ = new fileJSON();

            //create file chooser and allow it to save the following extenstions:
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save List");
            //tsv(txt), HTML, and JSON
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");

            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.getExtensionFilters().add(extFilter1);
            fileChooser.getExtensionFilters().add(extFilter2);

            Stage stage = (Stage)vBox.getScene().getWindow();
            File file = fileChooser.showSaveDialog(stage);

            //figure out where the file is saved and get the extension convert the extension to a string
            String fileExt = FilenameUtils.getExtension(String.valueOf(file));
            //create an if statement that checks if the string matches one of the file formats
            if (fileExt.matches("txt")) {
                fT.listToTSV(file, list);
            }
            else if (fileExt.matches("html")) {
                fH.listToHTML(file, list);
            }
            else if (fileExt.matches("json")) {
                fJ.listToJSON(file, list);
            }
        } catch (UnsupportedOperationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You can't save list in search view. Try again in normal view");
            alert.showAndWait();
        }

        //call the ext class that will save the data
    }

    public void closeList(ActionEvent event) {
        Platform.exit();
    }

    public void removeSelection(ActionEvent event) {
        //get the list and define two variables selectedRows, allItems
        try {
        ObservableList<Items> selectedRows, allItems;
        allItems = tableList.getItems();
        selectedRows = tableList.getSelectionModel().getSelectedItems();
        for (Items item : selectedRows) {allItems.remove(item);}
        } catch (UnsupportedOperationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You can't remove values in search view. Try again in normal view");
            alert.showAndWait();
        }
    }

    public void clearList(ActionEvent event) {
        try {
            tableList.getItems().clear();
        }
        catch (UnsupportedOperationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You can't clear list in search view. Try again in normal view");
            alert.showAndWait();
        }
    }

    public void addToList(ActionEvent event) {
        String nameTXT = nameField.getText();
        String serialTXT = serialField.getText();
        String valueTXT = valueField.getText();
        //Call a function that will validate the name field
        Boolean validateName = validateName(nameTXT);
        //Call a function that will validate the serial nubmer
        Boolean validateSerial = validateSerial(serialTXT, list);
        //Call a function that will validate the value
        Boolean validateValue = validateValue(valueTXT);

        //if statement that adds all the values if all are true
        try {
            if (validateName == true && validateSerial == true && validateValue == true) {
                addItems(nameTXT, serialTXT, valueTXT, list);
            }
            //clear the text fields
            nameField.clear();
            serialField.clear();
            valueField.clear();
        } catch (UnsupportedOperationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You can't add items to a list in search view. Try again in normal view");
            alert.showAndWait();
        }
    }

    public void addItems(String nameTXT, String serialTXT, String valueTXT, ObservableList<Items> list) {
        Items newItem = new Items(nameTXT, serialTXT, valueTXT);
        list.add(newItem);
    }

    public Boolean validateValue(String value) {
        //regex ^[0-9]*\.[0-9]{2,2}$
        if (value.matches("^[0-9]*\\.[0-9]{2}$")) {
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Your value does is not written in the format X.XX");
            alert.showAndWait();
            return false;
        }
    }

    public Boolean validateSerial(String serial, ObservableList<Items> list) {
        Boolean regexCheck;
        Boolean duplicateCheck = true;

        if (serial.matches("^[a-zA-Z]([-][a-zA-Z0-9]{3}){3}$")) {
            regexCheck = true;
        }
        else {
            regexCheck= false;
        }
        //create an if function that checks if the Serial Number is a duplicate.
        for (Items items : list) {
            if (serial.matches(items.getSerialNumber())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("The Serial Number you entered is a duplicate");
                alert.showAndWait();
                duplicateCheck = false;
                break;
            }
        }
        //If it is create an error message that says it is a duplicate Serial Number
        if (regexCheck == false || duplicateCheck == false) {
            return false;
        }
        return true;
    }

    public Boolean validateName(String name) {
        if (name.length() < 2 || name.length() > 256) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("The length of name is too long or too short. Make sure it is between 2 and 256.");
            alert.showAndWait();
            return false;
        }
        else {
            return true;
        }
    }

    public void editValue(TableColumn.CellEditEvent<Items, String> itemsStringCellEditEvent) {
        try {
            //create an Items object called item and make it equal to tableList.getSelectionModel().getSelectedItem()
            Items item = tableList.getSelectionModel().getSelectedItem();
            //call validateName to make sure that it is edited in a valid format
            Boolean valid = validateValue(itemsStringCellEditEvent.getNewValue());
            //create an if else that allows the edit to happen or doesn't allow it to happen
            if (valid == true) {
                item.setValue(itemsStringCellEditEvent.getNewValue());
            } else {
                item.setValue(itemsStringCellEditEvent.getOldValue());
                tableList.refresh();
            }
        } catch (UnsupportedOperationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You edit values of a list in search view. Try again in normal view");
            alert.showAndWait();
        }
    }

    public void editSerial(TableColumn.CellEditEvent<Items, String> itemsStringCellEditEvent) {

        try {
            //create an Items object called item and make it equal to tableList.getSelectionModel().getSelectedItem()
            Items item = tableList.getSelectionModel().getSelectedItem();
            //call validateSerial to make sure that it is edited in a valid format
            Boolean valid = validateSerial(itemsStringCellEditEvent.getNewValue(), list);
            //create an if else that allows the edit to happen or doesn't allow it to happen
            if (valid == true) {
                item.setSerialNumber(itemsStringCellEditEvent.getNewValue());
            } else {
                item.setSerialNumber(itemsStringCellEditEvent.getOldValue());
                tableList.refresh();
            }
        }
        catch (UnsupportedOperationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You edit values of a list in search view. Try again in normal view");
            alert.showAndWait();
        }
    }

    public void editName(TableColumn.CellEditEvent<Items, String> itemsStringCellEditEvent) {

        try {
            //create an Items object called item and make it equal to tableList.getSelectionModel().getSelectedItem()
            Items item = tableList.getSelectionModel().getSelectedItem();
            //call validateName to make sure that it is edited in a valid format
            Boolean valid = validateName(itemsStringCellEditEvent.getNewValue());
            //create an if else that allows the edit to happen or doesn't allow it to happen
            if (valid == true) {
                item.setName(itemsStringCellEditEvent.getNewValue());
            } else {
                item.setName(itemsStringCellEditEvent.getOldValue());
                tableList.refresh();
            }
        }
        catch (UnsupportedOperationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You edit values of a list in search view. Try again in normal view");
            alert.showAndWait();
        }
    }

    public void showNormal(ActionEvent event) {
        viewLabel.setText("Normal");
        tableList.setItems(list);
    }

    public void showSearch(ActionEvent event) {
        viewLabel.setText("Search");
        FilteredList<Items> filteredData = new FilteredList<>(list, b -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Items -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Items.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else if (Items.getSerialNumber().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<Items> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableList.comparatorProperty());

        tableList.setItems(sortedData);
    }

}
