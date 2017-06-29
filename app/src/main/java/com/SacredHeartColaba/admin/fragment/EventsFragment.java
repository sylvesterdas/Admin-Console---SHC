package com.SacredHeartColaba.admin.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.SacredHeartColaba.admin.R;

/**
 * A simple {@link ModelFragment} subclass.
 */
public class EventsFragment extends ModelFragment implements View.OnClickListener {

    private View rootView;

    private FloatingActionButton fab;


    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_events, container, false);

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab_events);
        fab.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_events:
                Toast.makeText(getActivity(), "Events", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}