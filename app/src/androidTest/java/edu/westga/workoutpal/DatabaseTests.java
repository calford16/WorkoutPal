package edu.westga.workoutpal;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import java.util.ArrayList;

import edu.westga.workoutpal.Model.MyDBHandler;
import edu.westga.workoutpal.View.MainActivity;

/**
 * Created by Cory on 4/24/2016.
 */
public class DatabaseTests extends ActivityInstrumentationTestCase2<MainActivity> {

    public DatabaseTests() {
        super(MainActivity.class);
    }

    @Test
    public void testDBHandlerAddsStat() throws Exception {
        MainActivity activity = getActivity();
        MyDBHandler handler = new MyDBHandler(activity.getBaseContext(), null, null, 1);

        handler.clearStats();
        ArrayList<String> list = handler.getStats();
        assertTrue(list.size() == 0);

        handler.addStat("arms");
        list = handler.getStats();
        assertTrue(list.get(0).equals("arms"));
        assertTrue(list.size() == 1);
    }

    @Test
    public void testDBHandlerClearsStats() throws Exception {
        MainActivity activity = getActivity();
        MyDBHandler handler = new MyDBHandler(activity.getBaseContext(), null, null, 1);

        handler.addStat("arms");
        ArrayList<String> list = handler.getStats();
        assertTrue(list.size() > 0);

        handler.clearStats();
        list = handler.getStats();
        assertTrue(list.size() == 0);
    }

    @Test
    public void testDBHandlerDeletesStat() throws Exception {
        MainActivity activity = getActivity();
        MyDBHandler handler = new MyDBHandler(activity.getBaseContext(), null, null, 1);

        handler.clearStats();
        handler.addStat("arms");
        ArrayList<String> list = handler.getStats();

        int currentSize = list.size();
        assertTrue(currentSize > 0);

        handler.deleteStat("arms");
        list = handler.getStats();
        assertTrue(list.size() == currentSize - 1);
    }

    @Test
    public void testDBHandlerDoesntDeleteNonExistentStat() throws Exception {
        MainActivity activity = getActivity();
        MyDBHandler handler = new MyDBHandler(activity.getBaseContext(), null, null, 1);

        handler.clearStats();
        handler.addStat("arms");
        ArrayList<String> list = handler.getStats();

        int currentSize = list.size();
        assertTrue(currentSize > 0);

        handler.deleteStat("legs");
        list = handler.getStats();
        assertTrue(list.size() == currentSize);
    }
}
