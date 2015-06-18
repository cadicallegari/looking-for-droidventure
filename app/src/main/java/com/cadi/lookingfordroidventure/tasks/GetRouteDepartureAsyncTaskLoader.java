package com.cadi.lookingfordroidventure.tasks;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.cadi.lookingfordroidventure.models.Departure;
import com.cadi.lookingfordroidventure.resources.BaseResource;
import com.cadi.lookingfordroidventure.resources.BaseService;
import com.cadi.lookingfordroidventure.resources.params.RouteParam;
import com.cadi.lookingfordroidventure.resources.params.RouteSearch;

import java.util.List;

/**
 * Created by cadi on 13/06/15.
 */
public class GetRouteDepartureAsyncTaskLoader extends AsyncTaskLoader<List<Departure>> {

    private Long routeId;
    private List<Departure> departures;

    public GetRouteDepartureAsyncTaskLoader(Context context, Long routeId) {
        super(context);
        this.routeId = routeId;
    }

    @Override
    public List<Departure> loadInBackground() {
        final RouteSearch routeSearch = new RouteSearch();
        final RouteParam routeParam = new RouteParam();

        routeParam.setRouteId(routeId);
        routeSearch.setParams(routeParam);

        BaseService service = BaseResource.getService(BaseService.class);
        departures = service.findDeparturesByRouteId(routeSearch).getRows();

        return departures;
    }

    @Override
    public void deliverResult(List<Departure> data) {
        departures = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        if (null != departures) {
            deliverResult(departures);
        }

        if (takeContentChanged() || null == departures) {
            forceLoad();
        }
    }
}