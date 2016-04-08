package edu.westga.workoutpal.Controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import edu.westga.workoutpal.Model.ColorTool;
import edu.westga.workoutpal.Model.FileReader;
import edu.westga.workoutpal.Model.WorkoutContent;

/**
 * Created by Cory on 4/7/2016.
 */
public class WorkoutController {

    public WorkoutController() {

    }

    public void generateWorkoutList(String fileName, Context c) {
        WorkoutContent workContent = new WorkoutContent();
        FileReader reader = new FileReader();
        ArrayList<List<String>> listInfo = new ArrayList<List<String>>();

        listInfo = reader.ReadFromFile(fileName, c);

        //TODO: Populate workout content

    }

    public boolean closeMatch (int color1, int color2, int tolerance) {
        ColorTool ct = new ColorTool();
        return ct.closeMatch(color1, color2, tolerance);
    }
}
