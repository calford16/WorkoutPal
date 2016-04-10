package edu.westga.workoutpal.Controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import edu.westga.workoutpal.Model.ColorTool;
import edu.westga.workoutpal.Model.FileReader;
import edu.westga.workoutpal.Model.WorkoutContent;
import edu.westga.workoutpal.Model.WorkoutItem;

/**
 * Created by Cory on 4/7/2016.
 */
public class WorkoutController {

    public WorkoutController() {

    }

    public void generateWorkoutContent(String fileName, Context c) {
        this.clearContent();
        FileReader reader = new FileReader();
        ArrayList<List<String>> listInfo = new ArrayList<List<String>>();
        WorkoutItem item;

        try {
            listInfo = reader.ReadFromFile(fileName, c);
            int id = 1;
            String muscle_group = "";
            String details = "";

            for (List<String> ls : listInfo) {
                muscle_group = ls.get(0);
                details = ls.get(1);
                item = new WorkoutItem(Integer.toString(id), muscle_group, details);
                WorkoutContent.addItem(item);
                id++;
            }
        } catch (Exception e) {
            // log exception
        }
    }

    public boolean closeMatch (int color1, int color2, int tolerance) {
        ColorTool ct = new ColorTool();
        return ct.closeMatch(color1, color2, tolerance);
    }

    public int getWorkoutContentSize() {
        return WorkoutContent.getItemCount();
    }

    public void clearContent() {
        WorkoutContent.clearItems();
    }
}
