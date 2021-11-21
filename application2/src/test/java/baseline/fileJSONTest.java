package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class fileJSONTest {

    private fileJSON json = new fileJSON();

    @Test
    void testSaveJSON() throws IOException, ParseException {
        File tmpDir = new File("data\\saveJSON.json");
        ObservableList<Items> testList = FXCollections.observableArrayList();
        ObservableList<Items> testList1= FXCollections.observableArrayList();
        testList.add(new Items("Xbox Series X", "A-XB1-24A-XY3", "1499.00"));
        testList.add(new Items("Samsung TV", "S-40A-ZBD-E47", "599.99"));

        json.listToJSON(tmpDir,testList);

        ObservableList<Items> expectedList = FXCollections.observableArrayList();
        expectedList.add(new Items("Xbox Series X", "A-XB1-24A-XY3", "1499.00"));
        expectedList.add(new Items("Samsung TV", "S-40A-ZBD-E47", "599.99"));

        json.JSONToList(tmpDir, testList1);

        assertEquals(expectedList, testList1);
    }

    @Test
    void testOpenJSON() throws IOException, ParseException {
        File file = new File("data\\okljdfskldj.json");
        ObservableList<Items> testList = FXCollections.observableArrayList();

        json.JSONToList(file, testList);

        ObservableList<Items> expectedList = FXCollections.observableArrayList();
        expectedList.add(new Items("Xbox Series X", "A-XB1-24A-XY3", "1499.00"));
        expectedList.add(new Items("Oh Yeah", "A-kjd-939-330", "10.00"));

        assertEquals(expectedList, testList);
    }

}