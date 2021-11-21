package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class fileTSVTest {

    private fileTSV tsv = new fileTSV();

    @Test
    void testSaveTSV() throws IOException {
        File tmpDir = new File("data\\saveTSV.txt");
        ObservableList<Items> testList = FXCollections.observableArrayList();
        ObservableList<Items> testList1= FXCollections.observableArrayList();
        testList.add(new Items("Xbox Series X", "A-XB1-24A-XY3", "1499.00"));
        testList.add(new Items("Samsung TV", "S-40A-ZBD-E47", "599.99"));

        tsv.listToTSV(tmpDir,testList);

        ObservableList<Items> expectedList = FXCollections.observableArrayList();
        expectedList.add(new Items("Xbox Series X", "A-XB1-24A-XY3", "1499.00"));
        expectedList.add(new Items("Samsung TV", "S-40A-ZBD-E47", "599.99"));

        tsv.TSVToList(tmpDir, testList1);

        assertEquals(expectedList, testList1);
    }

    @Test
    void testOpenTSV() throws IOException {
        File file = new File("data\\actualTest.txt");
        ObservableList<Items> testList = FXCollections.observableArrayList();

        tsv.TSVToList(file, testList);

        ObservableList<Items> expectedList = FXCollections.observableArrayList();
        expectedList.add(new Items("Xbox Series X", "A-XB1-24A-XY3", "1499.00"));
        expectedList.add(new Items("Samsung TV", "S-40A-ZBD-E47", "599.99"));

        assertEquals(expectedList, testList);
    }

}