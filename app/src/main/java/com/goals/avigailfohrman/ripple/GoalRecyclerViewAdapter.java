package com.goals.avigailfohrman.ripple;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.goals.avigailfohrman.ripple.GoalFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Goal} and makes a call to the
 * specified {@link GoalFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class GoalRecyclerViewAdapter extends RecyclerView.Adapter<GoalRecyclerViewAdapter.ViewHolder> {

    private final List<Goal> mGoalList;
    private final OnListFragmentInteractionListener mListener;

    public GoalRecyclerViewAdapter(List<Goal> goalListItems, OnListFragmentInteractionListener listener) {
        mGoalList = goalListItems;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_goal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mGoalList.get(position);
        int amountCompleted = mGoalList.get(position).getAmountCompleted();
        String stringAmountCompleted = amountCompleted + "";
        holder.mIdView.setText(stringAmountCompleted);
        holder.mContentView.setText(mGoalList.get(position).getGoalName());
        holder.mProgressBar.setMax(mGoalList.get(position).getGoalTargetAmount());
        holder.mProgressBar.setProgress(calculatePercentage(mGoalList.get(position).getAmountCompleted(),
                mGoalList.get(position).getGoalTargetAmount()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                    holder.mIdView.setText(holder.mItem.getAmountCompleted() + "");
                    holder.mProgressBar.incrementProgressBy(5);
                    holder.mProgressBar.setProgress(mGoalList.get(position).getAmountCompleted());
                    if (mGoalList.get(position).getAmountCompleted() % 12 == 0) {
                        showMotivationalSnackbar(v, "Keep it up!");
                    } else if (mGoalList.get(position).getAmountCompleted() % 5 == 0) {
                        showMotivationalSnackbar(v, "Way to go!");
                    } else if (mGoalList.get(position).getGoalTargetAmount()
                            == mGoalList.get(position).getAmountCompleted()) {
                        holder.mView.setBackgroundColor(v.getResources()
                                .getColor(R.color.cardview_shadow_start_color));
                        Snackbar.make(v, "Goal complete! Congratulations!", Snackbar.LENGTH_LONG)
                                .show();
                    }
                    holder.mProgressBar.invalidate();
                }
            }

            private void showMotivationalSnackbar(View v, String s) {
                Snackbar.make(v, s + " Only " +
                        (mGoalList.get(position).getGoalTargetAmount() -
                                mGoalList.get(position).getAmountCompleted())
                        + " times left to go!", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private int calculatePercentage(int amountCompleted, int target) {
        // Return an amount based on 100
        int progressPercentage = amountCompleted;
        int ratioFactor = 100 / target;
        progressPercentage = ratioFactor * amountCompleted;
        return progressPercentage;
    }

    @Override
    public int getItemCount() {
        return mGoalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ProgressBar mProgressBar;
        public Goal mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mProgressBar = (ProgressBar) view.findViewById(R.id.GoalProgressBar);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
