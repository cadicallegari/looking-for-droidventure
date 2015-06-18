package com.cadi.lookingfordroidventure.tasks;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.cadi.lookingfordroidventure.models.Route;
import com.cadi.lookingfordroidventure.resources.BaseResource;
import com.cadi.lookingfordroidventure.resources.BaseService;
import com.cadi.lookingfordroidventure.resources.params.StopParam;
import com.cadi.lookingfordroidventure.resources.params.StopSearchParam;

import java.util.List;

/**
 * Created by cadi on 13/06/15.
 */
public class GetRoutesAsyncTaskLoader extends AsyncTaskLoader<List<Route>> {

    private static final String TAG = "GetRoutesAsyncTaskLoader ";
    private String queryParam;
    private List<Route> routes;

    public GetRoutesAsyncTaskLoader(Context context, String query) {
        super(context);
        queryParam = query;
    }

    @Override
    public List<Route> loadInBackground() {
        final StopSearchParam param = new StopSearchParam ();

        param.setParams(new StopParam());
        param.getParams().setStopName(queryParam);

        BaseService service = BaseResource.getService(BaseService.class);
        List<Route> routes = service.findRoutesByName(param).getRows();

        return routes;
    }

    @Override
    public void deliverResult(List<Route> data) {
        routes = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        if (null != routes) {
            deliverResult(routes);
        }

        if (takeContentChanged() || routes == null) {
            forceLoad();
        }
    }
}

