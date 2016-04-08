package edu.westga.workoutpal.Model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Cory on 4/7/2016.
 */
public class FileReader {

    public FileReader() {

    }

    public ArrayList<List<String>> ReadFromFile(String fileName, Context c) {
        BufferedReader reader = null;
        ArrayList<List<String>> stringList = new ArrayList<List<String>>();
        try {
            reader = new BufferedReader(new InputStreamReader(c.getAssets().open(fileName), "UTF-8"));

            String mLine;
            while ((mLine = reader.readLine()) != null) {
                List<String> entry = Arrays.asList(mLine.split(","));
                stringList.add(entry);
            }
            return stringList;
        } catch (IOException e) {
            //log the exception
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
    }

}
