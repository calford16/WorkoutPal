package edu.westga.workoutpal;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.westga.workoutpal.Model.FileReader;

import static org.junit.Assert.assertTrue;

/**
 * Created by Cory on 4/24/2016.
 */
public class testFileReader {

    @Test
    public void testFileReaderSplitsOneLine() throws Exception {
        FileReader fr = new FileReader();
        String testString = "This is a^test";
        InputStream is = new ByteArrayInputStream( testString.getBytes("UTF-8") );
        ArrayList<List<String>> list = fr.ReadFromFile(is);
        assertTrue(list.get(0).get(0).equals("This is a"));
        assertTrue(list.get(0).get(1).equals("test"));
    }

    @Test
    public void testFileReaderSplitsTwoLines() throws Exception {
        FileReader fr = new FileReader();
        String testString = "This is a^test\nThis is line two^test2";
        InputStream is = new ByteArrayInputStream( testString.getBytes() );
        ArrayList<List<String>> list = fr.ReadFromFile(is);

        assertTrue(list.get(0).get(0).equals("This is a"));
        assertTrue(list.get(0).get(1).equals("test"));

        assertTrue(list.get(1).get(0).equals("This is line two"));
        assertTrue(list.get(1).get(1).equals("test2"));
    }

    @Test
    public void testFileReaderHandlesEmptyFile() throws Exception {
        FileReader fr = new FileReader();
        String testString = "";
        InputStream is = new ByteArrayInputStream( testString.getBytes() );
        ArrayList<List<String>> list = fr.ReadFromFile(is);
        assertTrue(list.size() == 0);
    }

    @Test
    public void testFileReaderHandlesNullFile() throws Exception {
        FileReader fr = new FileReader();
        ArrayList<List<String>> list = fr.ReadFromFile(null);
        assertTrue(list == null);
    }
}
