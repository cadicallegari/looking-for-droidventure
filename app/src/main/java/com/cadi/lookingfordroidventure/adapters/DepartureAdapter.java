package com.cadi.lookingfordroidventure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cadi.lookingfordroidventure.R;
import com.cadi.lookingfordroidventure.models.Departure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cadi on 13/06/15.
 */
public class DepartureAdapter extends ArrayAdapter<Departure> {

    private List<Departure> departures = new ArrayList<Departure>();


    public DepartureAdapter(Context context, List<Departure> departures) {
        super(context, R.layout.common_list_item, departures);

        if (null != departures) {
            this.departures = departures;
        }
    }

    public void setData(List<Departure> departures) {
        this.departures = departures;
        clear();

        if (null != departures) {
            for (Departure departure : this.departures) {
                add(departure);
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

        holder.time.setText(departures.get(position).getTime());
        holder.dayName.setText(departures.get(position).getCalendar());

        return convertView;
    }


    public static class ViewHolder {
        public final TextView time;
        public final TextView dayName;

        public ViewHolder(View view) {
            time = (TextView) view.findViewById(R.id.first_item);
            dayName = (TextView) view.findViewById(R.id.second_item);
        }
    }

}
