package com.cadi.lookingfordroidventure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cadi.lookingfordroidventure.R;
import com.cadi.lookingfordroidventure.models.Stop;

import java.util.List;

/**
 * Created by cadi on 13/06/15.
 */
public class StopAdapter extends ArrayAdapter<Stop> {

    private List<Stop> stops;


    public StopAdapter(Context context, List<Stop> stops) {
        super(context, R.layout.common_list_item, stops);

        if (null != stops) {
            this.stops = stops;
        }
    }

    public void setData(List<Stop> stops) {
        this.stops = stops;
        clear();
        if (null != stops) {
            for (Stop stop : this.stops) {
                add(stop);
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (null == convertView) {
            convertView = LayoutInflater.from(
                    parent.getContext()).inflate(R.layout.common_list_item, null
            );
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.stopName.setText(stops.get(position).getName());

        return convertView;
    }


    public static class ViewHolder {
        public final TextView stopName;

        public ViewHolder(View view) {

            stopName = (TextView) view.findViewById(R.id.second_item);
        }
    }

}