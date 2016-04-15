package edu.westga.workoutpal.Model;

/**
 * Created by Cory on 4/7/2016.
 */
public class WorkoutItem {

    public final String id;
    public final String muscle;
    public final String muscle_group;
    public final String details;

    public WorkoutItem(String id, String muscle, String muscle_group, String details) {
        this.id = id;
        this.muscle = muscle;
        this.muscle_group = muscle_group;
        this.details = details;
    }

    @Override
    public String toString() {
        return muscle_group + "\n\t" + details + "\n";
    }

    public String getId() {
        return id;
    }

    public String getMuscle_group() {
        return muscle_group;
    }

    public String getDetails() {
        return details;
    }

    public String getMuscle(){return muscle;}
}
