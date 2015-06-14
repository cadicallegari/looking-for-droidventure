package com.cadi.lookingfordroidventure.resources;

import com.cadi.lookingfordroidventure.resources.collections.Departures;
import com.cadi.lookingfordroidventure.resources.collections.Routes;
import com.cadi.lookingfordroidventure.resources.collections.Stops;
import com.cadi.lookingfordroidventure.resources.params.RouteSearch;
import com.cadi.lookingfordroidventure.resources.params.StopSearchParam;

import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by cadi on 13/06/15.
 */
public interface BaseService {

    @POST("/v1/queries/findRoutesByStopName/run")
    Routes findRoutesByName(@Body StopSearchParam params);

    @POST("/v1/queries/findStopsByRouteId/run")
    Stops findStopsByRouteId(@Body RouteSearch search);

    @POST("/v1/queries/findDeparturesByRouteId/run")
    Departures findDeparturesByRouteId(@Body RouteSearch search);
}


