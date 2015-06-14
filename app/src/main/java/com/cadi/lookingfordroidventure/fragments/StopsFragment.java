package com.cadi.lookingfordroidventure.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.cadi.lookingfordroidventure.adapters.StopAdapter;
import com.cadi.lookingfordroidventure.models.Stop;
import com.cadi.lookingfordroidventure.providers.ParamProvider;
import com.cadi.lookingfordroidventure.tasks.GetRouteStopsAsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cadi on 13/06/15.
 */
public class StopsFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<Stop>> {

    private static final String PARAM_NAME = "route_id";

    private Long routeId;
    private List<Stop> stops;
    private StopAdapter adapter;

    public StopsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            routeId = savedInstanceState.getLong(PARAM_NAME);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new StopAdapter(getActivity(), new ArrayList<Stop>());
        setListAdapter(adapter);

        Bundle args = new Bundle();
        args.putLong(PARAM_NAME, routeId);
        getLoaderManager().initLoader(0, args, this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        routeId = ((ParamProvider) activity).getRouteId();
    }

    @Override
    public Loader<List<Stop>> onCreateLoader(int id, Bundle args) {
        setListShown(false);
        return new GetRouteStopsAsyncTaskLoader(getActivity(), args.getLong(PARAM_NAME));
    }

    @Override
    public void onLoadFinished(Loader<List<Stop>> loader, List<Stop> stopsList) {
        stops = stopsList;
        adapter.setData(stops);
        setListShown(true);
    }

    @Override
    public void onLoaderReset(Loader<List<Stop>> loader) {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(PARAM_NAME, routeId);
    }

}
