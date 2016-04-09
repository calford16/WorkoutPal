package edu.westga.workoutpal.View;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import edu.westga.workoutpal.Controller.WorkoutController;

import edu.westga.workoutpal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FrontViewFragment extends Fragment implements View.OnTouchListener {

    public static final String ARMS = "arms.txt";
    public static final String CHEST = "chest.txt";
    public static final String ABS = "abs.txt";
    public static final String LEGS = "legs.txt";
    public WorkoutController controller;

    public FrontViewFragment() {
        controller = MainActivity.controller;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_front_view, container, false);
        view.setOnTouchListener(this);
        return view;
    }

    @Override
    public boolean onTouch (View v, MotionEvent ev) {
        final int action = ev.getAction();
        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();

        try {
            Activity activity = getActivity();
            MainActivity myActivity = (MainActivity) activity;

            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_UP:
                    int touchColor = getHotspotColor(R.id.image_areas, evX, evY);
                    int tolerance = 25;

                    if (controller.closeMatch(Color.RED, touchColor, tolerance)) {
                        controller.generateWorkoutContent(FrontViewFragment.ARMS, getContext());
                        myActivity.onListButtonPress(getView());
                    } else if (controller.closeMatch(Color.BLUE, touchColor, tolerance)) {
                        controller.generateWorkoutContent(FrontViewFragment.ABS, getContext());
                        myActivity.onListButtonPress(getView());
                    } else if (controller.closeMatch(Color.GREEN, touchColor, tolerance)) {
                        controller.generateWorkoutContent(FrontViewFragment.CHEST, getContext());
                        myActivity.onListButtonPress(getView());
                    } else if (controller.closeMatch(Color.YELLOW, touchColor, tolerance)) {
                        controller.generateWorkoutContent(FrontViewFragment.LEGS, getContext());
                        myActivity.onListButtonPress(getView());
                    }
                    break;
            }
        } catch (NullPointerException npe) {
            return false;
        }
        return true;
    }

    public int getHotspotColor (int hotspotId, int x, int y) {
        ImageView img = (ImageView) getView().findViewById(hotspotId);
        img.setDrawingCacheEnabled(true);
        Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
        img.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
    }
}
