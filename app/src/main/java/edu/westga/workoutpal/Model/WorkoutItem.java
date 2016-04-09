package edu.westga.workoutpal.Model;

/**
 * Created by Cory on 4/7/2016.
 */
public class WorkoutItem {

    public final String id;
    public final String muscle_group;
    public final String details;

    public WorkoutItem(String id, String muscle_group, String details) {
        this.id = id;
        this.muscle_group = muscle_group;
        this.details = details;
    }

    @Override
    public String toString() {
        return muscle_group + "\n\t" + details + "\n";
    }

}
