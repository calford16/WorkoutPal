package edu.westga.workoutpal;

import org.junit.Test;

import edu.westga.workoutpal.Model.WorkoutContent;
import edu.westga.workoutpal.Model.WorkoutItem;

import static org.junit.Assert.assertTrue;

/**
 * Created by Cory on 4/24/2016.
 */
public class testWorkoutContent {

    @Test
    public void testWorkoutContentGetItemCount() throws Exception {
        WorkoutContent.clearItems();
        assertTrue(WorkoutContent.getItemCount() == 0);

        WorkoutItem item = new WorkoutItem("1", "arms", "arms", "arms details");
        WorkoutContent.addItem(item);
        assertTrue(WorkoutContent.getItemCount() == 1);
    }

    @Test
    public void testWorkoutContentGetCurrentMuscleReturnsArms() throws Exception {
        WorkoutContent.clearItems();
        WorkoutItem item = new WorkoutItem("1", "arms", "arms", "arms details");
        WorkoutContent.addItem(item);
        assertTrue(WorkoutContent.getCurrentMuscle().equals("arms"));
    }
}
