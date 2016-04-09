package edu.westga.workoutpal.View;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import edu.westga.workoutpal.Controller.WorkoutController;
import edu.westga.workoutpal.Model.WorkoutContent;
import edu.westga.workoutpal.Model.WorkoutItem;
import edu.westga.workoutpal.R;

public class MainActivity extends AppCompatActivity implements WorkoutFragmentList.OnListFragmentInteractionListener {

    public static WorkoutController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.controller = new WorkoutController();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FrontViewFragment fragment = new FrontViewFragment();
        fragmentTransaction.add(R.id.frag_content, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onListFragmentInteraction(WorkoutItem item) {

    }

    public void onBodyButtonPress(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FrontViewFragment fragment = new FrontViewFragment();
        fragmentTransaction.replace(R.id.frag_content, fragment);
        fragmentTransaction.commit();

        TextView textView = (TextView) findViewById(R.id.frag_description);
        textView.setText(R.string.body_frag_string);
    }

    public void onStatButtonPress(View v) {
        Toast.makeText(MainActivity.this, "Stats coming soon!", Toast.LENGTH_SHORT).show();
    }

    public void onListButtonPress(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        WorkoutFragmentList fragment = new WorkoutFragmentList();
        fragmentTransaction.replace(R.id.frag_content, fragment);
        fragmentTransaction.commit();

        if (controller.getWorkoutContentSize() <= 0) {
            TextView textView = (TextView) findViewById(R.id.frag_description);
            textView.setText("Select a body part to see workouts!");
        } else {
            TextView textView = (TextView) findViewById(R.id.frag_description);
            textView.setText("");
        }
    }
}
