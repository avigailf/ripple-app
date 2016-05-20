package com.goals.avigailfohrman.ripple;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goals.avigailfohrman.ripple.GoalFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Goal} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ExploreGoalRecyclerViewAdapter extends RecyclerView.Adapter<ExploreGoalRecyclerViewAdapter.ViewHolder> {

    private final List<Goal> mGoalList;
    private final OnListFragmentInteractionListener mListener;

    public ExploreGoalRecyclerViewAdapter(List<Goal> goalListItems, OnListFragmentInteractionListener listener) {
        mGoalList = goalListItems;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_explore_goal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mGoalList.get(position);
        int targetAmount = mGoalList.get(position).getGoalTargetAmount();
        String stringTarget = targetAmount + "";
        holder.mIdView.setText(stringTarget);
        holder.mContentView.setText(mGoalList.get(position).getGoalName());
        holder.mGoalCreatorName.setText(mGoalList.get(position).getUserName());
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Snackbar.make(v, "The goal was added to your goals.", Snackbar.LENGTH_LONG).show();
                holder.mView.setBackgroundColor(v.getResources()
                        .getColor(R.color.cardview_shadow_start_color));
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGoalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mGoalCreatorName;
        public Goal mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mGoalCreatorName = (TextView) view.findViewById(R.id.GoalCreatorName);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
