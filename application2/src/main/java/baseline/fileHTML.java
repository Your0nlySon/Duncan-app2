package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Ethan Duncan
 */

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
        FileWriter writer = new FileWriter(file);
        writer.write("<table>\n");
        for (Items items : list) {
            writer.write("\t<tr>\n" +
                    "\t\t <td>" + items.getName() + "</td> \n" +
                    "\t\t <td>" + items.getSerialNumber() + "</td> \n" +
                    "\t\t <td>" + items.getValue() + "</td> \n" +
                    "\t </tr> \n");
        }
        writer.close();

    }

    public void HTMLToList(File file, ObservableList<Items> list) throws IOException {
        //create a document and make it equal to Jsoup.parse
        //nest for loops to get the values from the data and add it to the list
        Document doc = Jsoup.parse(file, "UTF-8");

        for (Element table : doc.select("table")) {
            for (Element row : table.select("tr")) {
                Elements tds = row.select("td");
                list.add(new Items(tds.get(0).text(), tds.get(1).text(), tds.get(2).text()));
            }
        }

    }
}
