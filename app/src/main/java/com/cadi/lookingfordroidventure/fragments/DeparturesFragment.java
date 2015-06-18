package com.cadi.lookingfordroidventure.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.cadi.lookingfordroidventure.adapters.DepartureAdapter;
import com.cadi.lookingfordroidventure.models.Departure;
import com.cadi.lookingfordroidventure.providers.ParamProvider;
import com.cadi.lookingfordroidventure.tasks.GetRouteDepartureAsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cadi on 13/06/15.
 */
public class DeparturesFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<Departure>> {

    private static final String PARAM_NAME = "route_id";

    private Long routeId;
    private DepartureAdapter adapter;
    private List<Departure> schedules;

    public DeparturesFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            routeId = savedInstanceState.getLong(PARAM_NAME);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        routeId = ((ParamProvider) activity).getRouteId();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new DepartureAdapter(getActivity(), new ArrayList<Departure>());
        setListAdapter(adapter);

        Bundle args = new Bundle();
        args.putLong(PARAM_NAME, routeId);
        getLoaderManager().initLoader(0, args, this);
    }

    @Override
    public Loader<List<Departure>> onCreateLoader(int id, Bundle args) {
        setListShown(false);
        return new GetRouteDepartureAsyncTaskLoader(getActivity(), args.getLong(PARAM_NAME));
    }

    @Override
    public void onLoadFinished(Loader<List<Departure>> loader, List<Departure> data) {
        schedules = (List<Departure>) data;
        adapter.setData(schedules);
        setListShown(true);
    }

    @Override
    public void onLoaderReset(Loader<List<Departure>> loader) {}

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(PARAM_NAME, routeId);
    }
}

