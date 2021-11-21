package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class fileHTMLTest {

    private fileHTML html = new fileHTML();

    @Test
    void testSaveHTML() throws IOException, ParseException {
        File tmpDir = new File("data\\saveHTML.html");
        ObservableList<Items> testList = FXCollections.observableArrayList();
        ObservableList<Items> testList1= FXCollections.observableArrayList();
        testList.add(new Items("Xbox Series X", "A-XB1-24A-XY3", "1499.00"));
        testList.add(new Items("Samsung TV", "S-40A-ZBD-E47", "599.99"));

        html.listToHTML(tmpDir,testList);

        ObservableList<Items> expectedList = FXCollections.observableArrayList();
        expectedList.add(new Items("Xbox Series X", "A-XB1-24A-XY3", "1499.00"));
        expectedList.add(new Items("Samsung TV", "S-40A-ZBD-E47", "599.99"));

        html.HTMLToList(tmpDir, testList1);

        assertEquals(expectedList, testList1);
    }

    @Test
    void testOpenHTML() throws IOException, ParseException {
        File file = new File("data\\Ooof.html");
        ObservableList<Items> testList = FXCollections.observableArrayList();

        html.HTMLToList(file, testList);

        ObservableList<Items> expectedList = FXCollections.observableArrayList();
        expectedList.add(new Items("Xbox Series X", "A-XB1-24A-XY3", "1499.00"));
        expectedList.add(new Items("Samsung TV", "S-40A-ZBD-E47", "599.99"));

        assertEquals(expectedList, testList);
    }

}