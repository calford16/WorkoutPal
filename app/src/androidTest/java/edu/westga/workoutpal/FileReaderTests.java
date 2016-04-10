package edu.westga.workoutpal;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.List;

import edu.westga.workoutpal.Model.FileReader;
import edu.westga.workoutpal.View.MainActivity;

/**
 * Created by Cory on 4/10/2016.
 */
public class FileReaderTests extends ActivityInstrumentationTestCase2<MainActivity> {

    public FileReaderTests() {
        super(MainActivity.class);
    }

    public void testFileReaderHandlesNullFileName() throws Exception {
        FileReader reader = new FileReader();
        ArrayList<List<String>> test = reader.ReadFromFile(null);
        assertTrue(test == null);
    }
}
