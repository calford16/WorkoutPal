package edu.westga.workoutpal;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.westga.workoutpal.Controller.WorkoutController;
import edu.westga.workoutpal.Model.WorkoutContent;
import edu.westga.workoutpal.View.MainActivity;

/**
 * Created by Cory on 4/10/2016.
 */
public class WorkoutControllerTests extends ActivityInstrumentationTestCase2<MainActivity> {

    public WorkoutControllerTests() {
        super(MainActivity.class);
    }

    @Test
    public void testWorkoutControllerAddsTwoWorkoutItems() throws Exception {
        WorkoutController controller = new WorkoutController();

        controller.clearContent();
        assertTrue(controller.getWorkoutContentSize() == 0);

        String testString = "This is a^test\nThis is line two^test2";
        InputStream is = new ByteArrayInputStream( testString.getBytes() );
        controller.generateWorkoutContent(is, "test");
        assertTrue(controller.getWorkoutContentSize() == 2);
        assertTrue(controller.getCurrentMuscle().equals("test"));
    }

    @Test
    public void testWorkoutControllerAddsNoWorkoutItems() throws Exception {
        WorkoutController controller = new WorkoutController();

        controller.clearContent();
        assertTrue(controller.getWorkoutContentSize() == 0);

        String testString = "";
        InputStream is = new ByteArrayInputStream( testString.getBytes() );
        controller.generateWorkoutContent(is, "test");
        assertTrue(controller.getWorkoutContentSize() == 0);
    }

    @Test
    public void testWorkoutControllerClearsWorkoutItems() throws Exception {
        WorkoutController controller = new WorkoutController();

        controller.clearContent();
        assertTrue(controller.getWorkoutContentSize() == 0);

        String testString = "This is a^test\nThis is line two^test2";
        InputStream is = new ByteArrayInputStream( testString.getBytes() );
        controller.generateWorkoutContent(is, "test");
        assertTrue(controller.getWorkoutContentSize() == 2);

        controller.clearContent();
        assertTrue(controller.getWorkoutContentSize() == 0);
    }
}
