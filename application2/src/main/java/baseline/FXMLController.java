package baseline;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Filter;




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

    private ObservableList<Items> list = FXCollections.observableArrayList();

    public void initialize() {
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

        /*
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
         */
    }


    public void openList(ActionEvent event) throws IOException, ParseException {
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

        Stage stage = (Stage)vBox.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        String fileExt = FilenameUtils.getExtension(String.valueOf(file));

        tableList.getItems().clear();
        if (fileExt.matches("txt")) {
            fT.TSVToList(file, list);
        }
        else if (fileExt.matches("html")) {
            fH.HTMLToList(file, list);
        }
        else if (fileExt.matches("json")) {
            fJ.JSONToList(file, list);
        }
    }

    public void saveList(ActionEvent event) throws IOException {
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
        //call the ext class that will save the data
    }

    public void closeList(ActionEvent event) {
        Platform.exit();
    }

    public void removeSelection(ActionEvent event) {
        //get the list and define two variables selectedRows, allItems
        //ObservableList<Items> selectedRows, allItems;
        //allItems = tableList.getItems();
        //selectedRows = tableList.getSelectionModel().getSelectedItems();
        //for (Items item : selectedRows) {allItems.remove(item);}
    }

    public void clearList(ActionEvent event) {
        //tableList.getItems().clear();
    }

    public void addToList(ActionEvent event) {
        String nameTXT = nameField.getText();
        String serialTXT = serialField.getText();
        String valueTXT = valueField.getText();
        //Call a function that will validate the name field
        Boolean validateName = validateName(nameTXT);
        //Call a function that will validate the serial nubmer
        Boolean validateSerial = validateSerial(serialTXT);
        //Call a function that will validate the value
        Boolean validateValue = validateValue(valueTXT);

        //if statement that adds all the values if all are true
        //else; clear the text fields
    }

    private Boolean validateValue(String value) {
        //regex ^[0-9]*\.[0-9]{2,2}$
        if (value.matches("^[0-9]*\\.[0-9]{2}$")) {
            return true;
        }
        else {
            //Alert alert = new Alert(AlertType.ERROR);
            //alert.setTitle("Error Dialog");
            //alert.setHeaderText(null);
            //alert.setContentText("Your value does is not written in the format X.XX");
            //alert.showAndWait();
            return false;
        }
    }

    private Boolean validateSerial(String serial) {
        Boolean regexCheck = false;
        Boolean duplicateCheck = false;

        if (serial.matches("^[a-zA-Z]([-][a-zA-Z0-9]{3}){3}$")) {
            regexCheck = true;
        }
        //create an if function that checks if the Serial Number is a duplicate.
        //If it is create an error message that says it is a duplicate Serial Number
        /* else */ return true;
    }

    private Boolean validateName(String name) {
        if (name.length() < 2 || name.length() > 256) {
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            //alert.setTitle("Error Dialog");
            //alert.setHeaderText(null);
            //alert.setContentText("The length of name is too long or too short. Make sure it is between 2 and 256.");
            //alert.showAndWait();
            return false;
        }
        else {
            return true;
        }
    }

    public void editValue(TableColumn.CellEditEvent<Items, String> itemsStringCellEditEvent) {
        //create an Items object called item and make it equal to tableList.getSelectionModel().getSelectedItem()
        Items item = tableList.getSelectionModel().getSelectedItem();
        //call validateName to make sure that it is edited in a valid format
        Boolean valid = validateValue(itemsStringCellEditEvent.getNewValue());
        //create an if else that allows the edit to happen or doesn't allow it to happen
    }

    public void editSerial(TableColumn.CellEditEvent<Items, String> itemsStringCellEditEvent) {
        //create an Items object called item and make it equal to tableList.getSelectionModel().getSelectedItem()
        Items item = tableList.getSelectionModel().getSelectedItem();
        //call validateSerial to make sure that it is edited in a valid format
        Boolean valid = validateSerial(itemsStringCellEditEvent.getNewValue());
        //create an if else that allows the edit to happen or doesn't allow it to happen
    }

    public void editName(TableColumn.CellEditEvent<Items, String> itemsStringCellEditEvent) {
        //create an Items object called item and make it equal to tableList.getSelectionModel().getSelectedItem()
        Items item = tableList.getSelectionModel().getSelectedItem();
        //call validateName to make sure that it is edited in a valid format
        Boolean valid = validateName(itemsStringCellEditEvent.getNewValue());
        //create an if else that allows the edit to happen or doesn't allow it to happen
    }
}
