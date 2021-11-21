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
        FileWriter writer = new FileWriter(file);
        writer.write("{\n  \"List\" : [\n");
        for (Items items : list) {
            writer.write("    {\"name\": \"" + items.getName() + "\", \"Serial Number\": \"" + items.getSerialNumber() + "\", \"Value\": \"" + items.getValue() + "\" },\n");
        }
        writer.write("  ]\n}");
        writer.close();

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
        Object obj;
        JSONObject jsonObejct;

        JSONParser parser = new JSONParser();
        JSONArray subjects = null;

        obj = parser.parse(new FileReader(file));
        jsonObejct = (JSONObject)obj;

        subjects = (JSONArray)jsonObejct.get("List");

        Iterator iterator = subjects.iterator();

        while (iterator.hasNext()) {
            JSONObject json = (JSONObject) iterator.next();
            list.add(new Items(json.get("name").toString(), json.get("Serial Number").toString(), json.get("Value").toString()));
        }

    }
}
