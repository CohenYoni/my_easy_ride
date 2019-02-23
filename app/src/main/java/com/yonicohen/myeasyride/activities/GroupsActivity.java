package com.yonicohen.myeasyride.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.yonicohen.myeasyride.R;
import com.yonicohen.myeasyride.adapters.RidesListAdapter;
import com.yonicohen.myeasyride.model.Ride;
import java.util.ArrayList;

/**
 * show all the rides of the selected date
 * extra of incoming intent: 'currentDate'
 */
public class GroupsActivity extends AppCompatActivity {

    private static final String TAG = "GroupsActivity";

    private RidesListAdapter adapter;
    private ListView ridesList;
    private TextView theDate;
    private ImageButton btnAddNewRide;
    private String currentDate;
    ArrayList<Ride> rides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groups_layout);
        ridesList = (ListView) findViewById(R.id.groups_list_view);
        theDate = (TextView) findViewById(R.id.tv_selected_date);
        btnAddNewRide = (ImageButton) findViewById(R.id.add_ride_btn);

        Log.d(TAG, "onCreate: Started.");

        //set the chosen date at the previous activity as a title
        Intent incoming_intent = getIntent();
        currentDate = incoming_intent.getStringExtra("currentDate");
        theDate.setText(currentDate);

        getRidesFromDB();

        //set all the rides detail into ListView
        adapter = new RidesListAdapter(this, R.layout.adapter_rides_view_layout, rides);
        ridesList.setAdapter(adapter);

        //go to ride details activity when ride selected
        ridesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: Time: " + rides.get(position).getRide_time() + "clicked.");

                Ride currentRide = rides.get(position);
                Intent intent = new Intent(GroupsActivity.this, RideDetailActivity.class);
                intent.putExtra("currentRide", currentRide);
                startActivity(intent);
            }
        });

        //add a new ride
        btnAddNewRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupsActivity.this, AddNewRideActivity.class);
                intent.putExtra("rideDate", currentDate);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: Stopped.");
    }


    /**
     * get all the rides of the current date from DB
     */
    private void getRidesFromDB() {
        //TODO: get the data from DB
        rides= new ArrayList<>();
        rides.add(new Ride("באר שבע", "ראש העין", "24/08/2018", "14:15"));
        rides.add(new Ride("באר שבע", "שדרות", "12/10/2018", "17:00"));
        rides.add(new Ride("ירושלים", "תל אביב", "23/12/2018", "08:30"));
        rides.add(new Ride("רחובות", "באר שבע", "02/01/2019", "20:45"));
    }

    //TODO: set observer when a new ride added to refresh the rides list
}