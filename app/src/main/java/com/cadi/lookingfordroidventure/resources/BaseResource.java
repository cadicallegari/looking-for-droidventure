package com.cadi.lookingfordroidventure.resources;

import android.util.Base64;

import com.google.gson.GsonBuilder;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by cadi on 13/06/15.
 */
public class BaseResource {

    private static final String ENV      = "staging";
    private static final String ENDPOINT = "https://api.appglu.com";
    private static final String USERNAME = "WKD4N7YMA1uiM8V";
    private static final String PASSWORD = "DtdTtzMLQlA0hk2C1Yi5pLyVIlAQ68";

    public static <T> T getService(Class<T> service) {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                String credentials   = USERNAME + ":" + PASSWORD;
                String authorization = Base64.encodeToString(credentials.getBytes(), Base64.DEFAULT);

                request.addHeader("Authorization", "Basic " + authorization);
                request.addHeader("X-AppGlu-Environment", ENV);
            }
        };

        GsonConverter converter = new GsonConverter(new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create());

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setConverter(converter)
                .setEndpoint(ENDPOINT)
                .setRequestInterceptor(requestInterceptor)
                .build();

        return restAdapter.create(service);
    }

}
