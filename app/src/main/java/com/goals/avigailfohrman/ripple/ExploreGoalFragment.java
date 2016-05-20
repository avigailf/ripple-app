package com.goals.avigailfohrman.ripple;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ExploreGoalFragment extends GoalFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private List<Goal> goals;
    private ProgressBar goalProgressBar;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ExploreGoalFragment() {

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ExploreGoalFragment newInstance(int columnCount) {
        ExploreGoalFragment fragment = new ExploreGoalFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        goals = new ArrayList<Goal>();
        goals.add(new Goal("Help someone cross the street", 7, "Miranda", 1));
        goals.add(new Goal("Start a charity", 98, "Bracha", 2));
        goals.add(new Goal("Donate $1 to starving children", 130, "Zoe", 3));
        goals.add(new Goal("Save leftovers", 100, "Zoe", 3));
        goals.add(new Goal("Smile at a homeless person", 4, "Alison", 4));
        goals.add(new Goal("Start a school", 3, "Miranda", 1));
        goals.add(new Goal("Rescue a heart attack victim", 9, "Colleen", 5));
        goals.add(new Goal("Start a school", 3, "Miranda", 1));
        goals.add(new Goal("Rescue a heart attack victim", 9, "Colleen", 5));
        goals.add(new Goal("Rescue a heart attack victim", 9, "Colleen", 5));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore_goal_list, container, false);

        // Set the adapter.
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.setAdapter(new ExploreGoalRecyclerViewAdapter(goals, (GoalFragment.OnListFragmentInteractionListener) mListener));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Goal goalItem);
    }

}
