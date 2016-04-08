package edu.westga.workoutpal.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.westga.workoutpal.Model.WorkoutItem;
import edu.westga.workoutpal.R;
import edu.westga.workoutpal.View.WorkoutFragmentList.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link edu.westga.workoutpal.Model.WorkoutItem} and makes a call to the
 * specified {@link WorkoutFragmentList.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class WorkoutRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutRecyclerViewAdapter.ViewHolder> {

    private final List<WorkoutItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public WorkoutRecyclerViewAdapter(List<WorkoutItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_workout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mMuscleGroupView.setText(mValues.get(position).muscle_group);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mMuscleGroupView;
        public WorkoutItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mMuscleGroupView = (TextView) view.findViewById(R.id.muscle_group);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mMuscleGroupView.getText() + "'";
        }
    }
}
