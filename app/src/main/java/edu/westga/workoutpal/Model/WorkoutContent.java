package edu.westga.workoutpal.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cory Alford on 4/7/2016
 */
public class WorkoutContent {

    /**
     * An array of workout items.
     */
    public static final List<WorkoutItem> ITEMS = new ArrayList<WorkoutItem>();

    /**
     * A map of workout items, by ID.
     */
    public static final Map<String, WorkoutItem> ITEM_MAP = new HashMap<String, WorkoutItem>();

    public static void addItem(WorkoutItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        builder.append("\n" + ITEMS.get(position).toString());
        return builder.toString();
    }

    public static void clearItems() {
        ITEMS.clear();
        ITEM_MAP.clear();
    }

    public static int getItemCount() {
        return ITEMS.size();
    }

    public static String getCurrentMuscle() {
        if (ITEMS.size() == 0) {
            return null;
        }
        return ITEMS.get(0).getMuscle();
    }
}
