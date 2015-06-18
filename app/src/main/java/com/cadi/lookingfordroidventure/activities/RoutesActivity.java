package com.cadi.lookingfordroidventure.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import com.cadi.lookingfordroidventure.R;
import com.cadi.lookingfordroidventure.fragments.RoutesActivityFragment;


public class RoutesActivity extends ActionBarActivity {

    private static final String TAG = "RoutesActivity ";

    private RoutesActivityFragment routesFragment = new RoutesActivityFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        handleIntent(getIntent());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, routesFragment)
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_routes, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }


    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }


    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            routesFragment.doSearch(query);
        }
    }

}
