package com.yonicohen.myeasyride.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.yonicohen.myeasyride.R;
import com.yonicohen.myeasyride.model.Ride;
import java.util.ArrayList;

/**
 * Adapter class to customize the ListView of rides list
 */
public class RidesListAdapter extends ArrayAdapter<Ride> {

    private Context context;
    private int resource;

    /**
     * static inner class of the view of each row in the list view
     */
    static class ViewHolder {
        TextView time;
        TextView from_title;
        TextView to_title;
        TextView from_value;
        TextView to_value;
    }

    /**
     * Constructor
     * @param context the current context
     * @param resource the resource ID
     * @param objects array list of rides
     */
    public RidesListAdapter(Context context, int resource, ArrayList<Ride> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    /**
     * get the details of the item that selected
     * @param position the index of the item that selected
     * @return the convert view
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        //get the ride information
        String from_city = getItem(position).getFrom_city();
        String to_city = getItem(position).getTo_city();
        String ride_date = getItem(position).getRide_date();
        String ride_time = getItem(position).getRide_time();

        //create the ride object with the information
        Ride ride = new Ride(from_city, to_city, ride_date, ride_time);

        //ViewHolder object
        ViewHolder holder;
        //get references to the convert view
        LayoutInflater inflater = LayoutInflater.from(context);
//        if (convertView == null)
        convertView = inflater.inflate(resource, parent, false);
        holder = new ViewHolder();
        holder.time = (TextView) convertView.findViewById(R.id.ride_time);
        holder.from_title = (TextView) convertView.findViewById(R.id.ride_from);
        holder.to_title = (TextView) convertView.findViewById(R.id.ride_to);
        holder.from_value = (TextView) convertView.findViewById(R.id.ride_from_value);
        holder.to_value = (TextView) convertView.findViewById(R.id.ride_to_value);

        convertView.setTag(holder);

        holder.time.setText(ride.getRide_time());
        holder.from_value.setText(ride.getFrom_city());
        holder.to_value.setText(ride.getTo_city());

        return convertView;
    }
}