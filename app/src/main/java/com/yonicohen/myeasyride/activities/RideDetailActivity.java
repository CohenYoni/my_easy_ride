package com.yonicohen.myeasyride.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.yonicohen.myeasyride.R;
import com.yonicohen.myeasyride.model.Passenger;
import com.yonicohen.myeasyride.model.Ride;
import com.yonicohen.myeasyride.tools.SendMassageListener;
import java.util.ArrayList;

/**
 * This activity shows the details of the ride, such as: from, to, date, time, passengers list and so on.
 */
public class RideDetailActivity extends AppCompatActivity {

    private static final String TAG = "RideDetailActivity";

    private Button btnSendToAll;
    private ListView lvOfNames;
    private TextView tvCurrentRide;
    private ImageButton btnAddNewPassenger;
    private Ride currentRide;
    private ArrayList<Passenger> passengers;
    private ArrayList<String> passengersNames;
    private ArrayList<String> passengersPhones;
    ArrayAdapter lvAdapter;

    /**
     * get all the passengers of the current ride from DB
     */
    private void getPassengersFromDB() {

        //TODO: get the data from DB

        passengers= new ArrayList<>();
        passengers.add(new Passenger("0527542891", "יוני", "כהן", "באר שבע", "זלמן שניאור", 8, 4));
        passengers.add(new Passenger("0527333454", "שיר", "סולומונוב", "באר שבע", "זלמן שניאור", 8, 5));
        passengers.add(new Passenger("1111111111", "שי", "בדיחי", "באר שבע", "ביאליק", 12, 2));
        passengers.add(new Passenger("1111111111", "דנה", "גורן", "באר שבע", "אברהם אבינו", 37, 0));

        //TODO: try to create ArrayList of Passengers and pass it to the adapter, instead of 3 ArrayLists
        passengersNames= new ArrayList<>();
        passengersPhones= new ArrayList<>();
        for (Passenger obj : passengers) {
            passengersNames.add(obj.getLast_name() + " " + obj.getFirst_name());
            passengersPhones.add(obj.getPhone_number());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ride_detail_layout);
        btnSendToAll = (Button) findViewById(R.id.btn_send_to_all);
        lvOfNames = (ListView) findViewById(R.id.list_of_names);
        tvCurrentRide = (TextView)  findViewById(R.id.current_ride_detail);
        btnAddNewPassenger = (ImageButton) findViewById(R.id.add_passenger_btn);

        Log.d(TAG, "onCreate: Started.");

        getPassengersFromDB();

        //show the current ride details
        Intent incoming_intent = getIntent();
        currentRide = (Ride) incoming_intent.getSerializableExtra("currentRide");
        Resources res = getResources();
        String details = String.format(res.getString(R.string.current_ride_details),
                currentRide.getFrom_city(),
                currentRide.getTo_city(),
                currentRide.getRide_time(),
                currentRide.getRide_date());
        tvCurrentRide.setText(details);

        //listener for send message button
        btnSendToAll.setOnClickListener(new SendMassageListener(RideDetailActivity.this, passengersPhones));

        //set all the passengers details into ListView
        lvAdapter = new ArrayAdapter<>(RideDetailActivity.this, R.layout.list_item_layout, passengersNames);
        lvOfNames.setAdapter(lvAdapter);

        //go to showPassenger activity when the user click on a passenger
        lvOfNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: Phone: " + passengers.get(position).getPhone_number() + "clicked.");

                Passenger currentPassenger = passengers.get(position);
                Intent intent = new Intent(RideDetailActivity.this, ShowPassengerActivity.class);
                intent.putExtra("currentPassenger", currentPassenger);
                startActivity(intent);
            }
        });

        //add a new passenger to the current ride
        btnAddNewPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RideDetailActivity.this, AddPassengersToRideActivity.class);
                intent.putExtra("currentRide", currentRide);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {

        //TODO: set observer when a new passenger added to refresh the passenger list, instead of onResume method

        super.onResume();
        Log.d(TAG, "onResume: resume.");
        getPassengersFromDB();
        lvAdapter.clear();
        lvAdapter.addAll(passengersNames);
        lvAdapter.notifyDataSetChanged();
    }
}
