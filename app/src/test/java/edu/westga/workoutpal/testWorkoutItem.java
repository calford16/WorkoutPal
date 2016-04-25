package edu.westga.workoutpal;

import org.junit.Test;

import edu.westga.workoutpal.Model.WorkoutItem;

import static org.junit.Assert.assertTrue;

/**
 * Created by Cory on 4/24/2016.
 */
public class testWorkoutItem {

    @Test
    public void testWorkoutItemIsCreated() throws Exception {
        WorkoutItem item = new WorkoutItem("1", "arms", "arms", "arms details");

        assertTrue(item.getId().equals("1"));
        assertTrue(item.getMuscle().equals("arms"));
        assertTrue(item.getMuscle_group().equals("arms"));
        assertTrue(item.getDetails().equals("arms details"));
        assertTrue(item.toString().equals("arms\n\tarms details\n"));
    }
}
