package com.cadi.lookingfordroidventure.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;

import com.cadi.lookingfordroidventure.R;
import com.cadi.lookingfordroidventure.activities.RouteScheduleActivity;
import com.cadi.lookingfordroidventure.adapters.RouteAdapter;
import com.cadi.lookingfordroidventure.models.Route;
import com.cadi.lookingfordroidventure.tasks.GetRoutesAsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class RoutesActivityFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<Route>> {

    private static final String TAG = "RoutesActivityFragment ";
    private static final String PARAM_NAME = "query_param";

    private RouteAdapter adapter;
    private List<Route> routes = new ArrayList<Route>();

    public RoutesActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (null == adapter) {
            adapter = new RouteAdapter(getActivity(), routes);
            setListAdapter(adapter);
        }

        setEmptyText(getString(R.string.no_routes));
        setListShown(true);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getActivity(), RouteScheduleActivity.class);
        intent.putExtra(RouteScheduleActivity.PARAM_ROUTE_ID, routes.get(position).getId());
        intent.putExtra(RouteScheduleActivity.PARAM_ROUTE_NAME, routes.get(position).getLongName());

        getActivity().startActivity(intent);
        super.onListItemClick(l, v, position, id);
    }


    public void doSearch(String query) {
        setListShown(true);
        Bundle args = new Bundle();
        args.putString(PARAM_NAME, query);
        getLoaderManager().restartLoader(0, args, this);
    }


    @Override
    public Loader<List<Route>> onCreateLoader(int id, Bundle args) {
        setListShown(false);
        return new GetRoutesAsyncTaskLoader(getActivity(), args.getString(PARAM_NAME));
    }

    @Override
    public void onLoadFinished(Loader<List<Route>> loader, List<Route> data) {
        routes = (List<Route>) data;

        adapter.setData(routes);
        setListShown(true);
    }

    @Override
    public void onLoaderReset(Loader<List<Route>> loader) {
    }
}
