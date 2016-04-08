package edu.westga.workoutpal.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.westga.workoutpal.Controller.WorkoutController;
import edu.westga.workoutpal.Model.WorkoutItem;
import edu.westga.workoutpal.R;

public class MainActivity extends AppCompatActivity implements WorkoutFragmentList.OnListFragmentInteractionListener {

    public static WorkoutController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onListFragmentInteraction(WorkoutItem item) {

    }
}
