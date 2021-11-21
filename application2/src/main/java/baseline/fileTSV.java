package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Ethan Duncan
 */

import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class fileTSV {

    void listToTSV(File file, ObservableList<Items> list) throws IOException {
        //create file writer
        //for loop that will write the contents of the list to the file
        FileWriter writer = new FileWriter(file);
        for (Items items : list) {
            writer.write(items.getName() + "\t" + items.getSerialNumber() + "\t" + items.getValue() + "\n");
        }
        writer.close();

    }

    void TSVToList(File file, ObservableList<Items> list) throws IOException {
        //create a buffered reader that will get the path of the selected value
        //create a string called line
        //have a while loop that will add the TSV values into the list
        BufferedReader reader = Files.newBufferedReader(Paths.get(String.valueOf(file)));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] names = line.split("\t");

            // Add the student to the list
            list.add(new Items(names[0],names[1],names[2]));
        }
    }
}
