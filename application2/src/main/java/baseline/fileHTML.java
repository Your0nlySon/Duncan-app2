package baseline;

import javafx.collections.ObservableList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class fileHTML {
    public void listToHTML(File file, ObservableList<Items> list) throws IOException {
        //create file writer
        //for loop that will write the contents of the list to the file
    }

    public void HTMLToList(File file, ObservableList<Items> list) throws IOException {
        //create a document and make it equal to Jsoup.parse
        //nest for loops to get the values from the data and add it to the list
    }
}
