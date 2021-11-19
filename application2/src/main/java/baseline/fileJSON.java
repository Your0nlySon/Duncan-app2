package baseline;

import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class fileJSON {

    public void listToJSON(File file, ObservableList<Items> list) throws IOException {
        //create file writer
        //for loop that will write the contents of the list to the file in the format of JSON
    }

    public void JSONToList(File file, ObservableList<Items> list) throws IOException, ParseException {
        //Create Object and JSONObject
        //Create one json parser object
        //Create a JSON Array
        //Read json file using parser and store it in obj
        //Create json object to read internal values
        //Reading products array from  the file
        //Create iterator for products array
        //Loop through the iterator
        //Create another json
        //add JSON objects to the list
    }
}
