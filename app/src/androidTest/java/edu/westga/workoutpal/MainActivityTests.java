package edu.westga.workoutpal;

import android.annotation.TargetApi;
import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

import edu.westga.workoutpal.Controller.WorkoutController;
import edu.westga.workoutpal.View.FrontViewFragment;
import edu.westga.workoutpal.View.MainActivity;
import edu.westga.workoutpal.View.WorkoutFragmentList;

/**
 * Created by Cory on 4/9/2016.
 */
public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTests() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void testTitleIsDisplayed() {
        MainActivity activity = getActivity();

        final TextView titleText = (TextView) activity.findViewById(R.id.header);
        assertEquals("Workout Pal!", titleText.getText());
    }

    public void testFragmentDescriptionIsDisplayed() {
        MainActivity activity = getActivity();

        final TextView fragDescription = (TextView) activity.findViewById(R.id.frag_description);
        assertNotNull(fragDescription);
    }

    public void testFrontViewFragmentIsCreatedAtStart() {
        Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.frag_content);
        assertTrue(currentFragment instanceof FrontViewFragment);
    }

    public void testWorkoutListIsCreatedWhenListButtonClicked() {
        MainActivity activity = getActivity();

        getInstrumentation().waitForIdleSync();
        ImageButton listButton = (ImageButton) activity.findViewById(R.id.listButton);
        TouchUtils.clickView(this, listButton);

        Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.frag_content);
        assertTrue(currentFragment instanceof WorkoutFragmentList);
    }

    public void testBodyIsShownWhenBodyButtonClicked() {
        MainActivity activity = getActivity();

        getInstrumentation().waitForIdleSync();
        ImageButton listButton = (ImageButton) activity.findViewById(R.id.listButton);
        TouchUtils.clickView(this, listButton);
        ImageButton bodyButton = (ImageButton) activity.findViewById(R.id.bodyButton);
        TouchUtils.clickView(this, bodyButton);

        Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.frag_content);
        assertTrue(currentFragment instanceof FrontViewFragment);
    }

    public void testWorkoutListHasNonZeroContentForBodyPart() {
        MainActivity activity = getActivity();
        WorkoutController controller = new WorkoutController();

        getInstrumentation().waitForIdleSync();
        try {
            controller.generateWorkoutContent(this.getActivity().getBaseContext().getAssets().open(FrontViewFragment.ARMS + ".txt"));
        } catch (IOException ioe) {
            fail();
        }
        ImageButton listButton = (ImageButton) activity.findViewById(R.id.listButton);
        TouchUtils.clickView(this, listButton);

        Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.frag_content);
        if (currentFragment instanceof WorkoutFragmentList) {
            assertTrue(((WorkoutFragmentList) currentFragment).getItemCount() > 0);
        } else {
            fail();
        }
    }


    public void testWorkoutListHasZeroContentBeforeBodyPartClicked() {
        MainActivity activity = getActivity();
        WorkoutController controller = new WorkoutController();

        getInstrumentation().waitForIdleSync();

        controller.clearContent();
        ImageButton listButton = (ImageButton) activity.findViewById(R.id.listButton);
        TouchUtils.clickView(this, listButton);

        Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.frag_content);
        if (currentFragment instanceof WorkoutFragmentList) {
            assertTrue(((WorkoutFragmentList) currentFragment).getItemCount() == 0);
        } else {
            fail();
        }
    }

}
