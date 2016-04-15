package edu.westga.workoutpal.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import edu.westga.workoutpal.Controller.WorkoutController;
import edu.westga.workoutpal.Model.MyDBHandler;
import edu.westga.workoutpal.Model.WorkoutContent;
import edu.westga.workoutpal.Model.WorkoutItem;
import edu.westga.workoutpal.R;

public class MainActivity extends AppCompatActivity implements WorkoutFragmentList.OnListFragmentInteractionListener {

    public static WorkoutController controller;
    public static final String ARMS = "arms";
    public static final String CHEST = "chest";
    public static final String ABS = "abs";
    public static final String LEGS = "legs";
    public static MyDBHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.controller = new WorkoutController();
        this.handler = new MyDBHandler(this, null, null, 1);

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
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        StatsFragment fragment = new StatsFragment();
        fragmentTransaction.replace(R.id.frag_content, fragment);
        fragmentTransaction.commit();

        TextView textView = (TextView) findViewById(R.id.frag_description);
        textView.setText("");
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

    public void onAddStat(View v) {
        MainActivity.handler.addStat(MainActivity.controller.getCurrentMuscle());
    }

    public void onDeleteStat(View v) {
        if (!MainActivity.handler.deleteStat(MainActivity.controller.getCurrentMuscle())){
            Toast.makeText(this, "Nothing to remove.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClearStats(View v) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Clearing Stats")
                .setMessage("Are you sure you want to clear stats?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.handler.clearStats();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
