package com.cadi.lookingfordroidventure.activities;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cadi.lookingfordroidventure.R;
import com.cadi.lookingfordroidventure.fragments.StopsFragment;
import com.cadi.lookingfordroidventure.fragments.DeparturesFragment;
import com.cadi.lookingfordroidventure.providers.ParamProvider;

public class RouteScheduleActivity extends ActionBarActivity implements ParamProvider {

    public static final String PARAM_ROUTE_ID   = "route_id";
    public static final String PARAM_ROUTE_NAME = "route_name";

    private Long routeId;
    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        getSupportActionBar().setElevation(0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra(PARAM_ROUTE_NAME));

        routeId = getIntent().getLongExtra(PARAM_ROUTE_ID, 0L);

        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getString(R.string.schedules)),
                DeparturesFragment.class, null
        );

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getString(R.string.itinerary)),
                StopsFragment.class, null
        );

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(PARAM_ROUTE_ID, routeId);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_schedule, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public Long getRouteId() {

        return routeId;
    }

}
