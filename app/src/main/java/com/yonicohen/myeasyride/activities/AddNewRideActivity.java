package com.yonicohen.myeasyride.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.yonicohen.myeasyride.R;
import com.yonicohen.myeasyride.model.Ride;
import com.yonicohen.myeasyride.tools.AppResources;
import com.yonicohen.myeasyride.tools.SetTime;
import java.util.ArrayList;

/**
 * add a new ride.
 * extra of incoming intent: 'currentRide', 'rideDate'
 */
public class AddNewRideActivity extends AppCompatActivity {

    private static final String TAG = "AddNewRideActivity";
    private final ArrayList<String> CITIES = AppResources.getCitiesList();

    private String rideDate;
    AutoCompleteTextView autoCompleteFromCity;
    AutoCompleteTextView autoCompleteToCity;
    Button btnSetTime;
    Button btnAddNewRide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_ride_layout);

        Log.d(TAG, "onCreate: Started.");

        //references to the elements of the view
        autoCompleteFromCity = (AutoCompleteTextView) findViewById(R.id.edit_text_from_city);
        autoCompleteToCity = (AutoCompleteTextView) findViewById(R.id.edit_text_to_city);
        btnSetTime = (Button) findViewById(R.id.select_ride_time) ;
        btnAddNewRide = (Button) findViewById(R.id.btn_add_ride);

        //get the date that chosen
        Intent incomingIntent = getIntent();
        rideDate = incomingIntent.getStringExtra("rideDate");

        //set adapters to 'from' and 'to' auto complete fields
        ArrayAdapter<String> cityFromAdapter = new ArrayAdapter<>(AddNewRideActivity.this, R.layout.list_item_layout, CITIES);
        autoCompleteFromCity.setAdapter(cityFromAdapter);
        ArrayAdapter<String> cityToAdapter = new ArrayAdapter<>(AddNewRideActivity.this, R.layout.list_item_layout, CITIES);
        autoCompleteToCity.setAdapter(cityToAdapter);

        //set the ride time
        btnSetTime.setOnClickListener(new SetTime(AddNewRideActivity.this, btnSetTime, true));

        //adding the ride button
        btnAddNewRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateCityField(autoCompleteFromCity) && validateCityField(autoCompleteToCity)) {
                    String fromCity = autoCompleteFromCity.getText().toString();
                    String toCity = autoCompleteToCity.getText().toString();
                    String rideTime = btnSetTime.getText().toString();

                    Ride newRide = new Ride(fromCity, toCity, rideDate, rideTime);

                    //TODO: add the ride to DB
                    //TODO: notify the observers

                    Toast.makeText(AddNewRideActivity.this, R.string.ride_added_successfully, Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(AddNewRideActivity.this, RideDetailActivity.class);
                    intent.putExtra("currentRide", newRide);
                    startActivity(intent);
                    AddNewRideActivity.this.finish();
                }
            }
        });
    }

    /**
     * validate city input field
     * @param cityAutoCompleteTextView the field component
     * @return true if valid
     */
    private boolean validateCityField(AutoCompleteTextView cityAutoCompleteTextView) {
        String cityToValidate = cityAutoCompleteTextView.getText().toString();
        if (cityToValidate.isEmpty() || !CITIES.contains(cityToValidate)) {
            cityAutoCompleteTextView.setError(getString(R.string.must_select_city));
            return false;
        }
        return true;
    }
}
