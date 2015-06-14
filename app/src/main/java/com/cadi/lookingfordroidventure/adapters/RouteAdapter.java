package com.cadi.lookingfordroidventure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cadi.lookingfordroidventure.R;
import com.cadi.lookingfordroidventure.models.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cadi on 13/06/15.
 */
public class RouteAdapter extends ArrayAdapter<Route> {

    private List<Route> routes = new ArrayList<Route>();


    public RouteAdapter(Context context, List<Route> routes) {
        super(context, R.layout.common_list_item, routes);

        if (null != routes) {
            setData(routes);
        }
    }


    public void setData(List<Route> routes) {
        this.routes = routes;
        clear();
        if (null != routes) {
            for (Route route : this.routes) {
                add(route);
            }
        }

        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(
                    parent.getContext()).inflate(R.layout.common_list_item, null
            );
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.longName.setText(routes.get(position).getLongName());
        holder.shortName.setText(routes.get(position).getShortName());

        return convertView;
    }


    public static class ViewHolder {
        public final TextView longName;
        public final TextView shortName;

        public ViewHolder(View view) {
            shortName = (TextView) view.findViewById(R.id.first_item);
            longName  = (TextView) view.findViewById(R.id.second_item);
        }
    }

}
