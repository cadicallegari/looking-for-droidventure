package com.cadi.lookingfordroidventure.tasks;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.cadi.lookingfordroidventure.models.Stop;
import com.cadi.lookingfordroidventure.resources.BaseResource;
import com.cadi.lookingfordroidventure.resources.BaseService;
import com.cadi.lookingfordroidventure.resources.params.RouteParam;
import com.cadi.lookingfordroidventure.resources.params.RouteSearch;

import java.util.List;

/**
 * Created by cadi on 13/06/15.
 */
public class GetRouteStopsAsyncTaskLoader extends AsyncTaskLoader<List<Stop>> {

    private Long routeId;
    private List<Stop> stops;

    public GetRouteStopsAsyncTaskLoader(Context context, Long routeId) {
        super(context);
        this.routeId = routeId;
    }

    @Override
    public List<Stop> loadInBackground() {
        final RouteSearch routeSearch = new RouteSearch();
        final RouteParam routeParam   = new RouteParam();

        routeParam.setRouteId(routeId);
        routeSearch.setParams(routeParam);

        BaseService service = BaseResource.getService(BaseService.class);
        List<Stop> stops = service.findStopsByRouteId(routeSearch).getRows();

        return stops;
    }

    @Override
    public void deliverResult(List<Stop> data) {
        stops = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        if (null != stops) {
            deliverResult(stops);
        }

        if (takeContentChanged() || null == stops) {
            forceLoad();
        }
    }
}
