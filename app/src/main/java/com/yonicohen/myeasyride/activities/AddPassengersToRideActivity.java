package com.yonicohen.myeasyride.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.yonicohen.myeasyride.R;
import com.yonicohen.myeasyride.model.Passenger;
import com.yonicohen.myeasyride.model.Ride;
import com.yonicohen.myeasyride.tools.AppResources;
import java.util.ArrayList;

/**
 * add aa new passenger to ride
 */
public class AddPassengersToRideActivity extends AppCompatActivity {

    private static final String TAG = "AddPassengersActivity";
    private final ArrayList<String> CITIES = AppResources.getCitiesList();

    private AutoCompleteTextView autoCompletePhones;
    private EditText etFirstName;
    private EditText etLastName;
    private AutoCompleteTextView autoCompletePassengerCity;
    private AutoCompleteTextView autoCompletePassengerStreet;
    private EditText etPassengerAptNumber;
    private Button btnAddPassenger;
    private Button btnAddTheRide;
    private Ride currentRide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_passengers);
        autoCompletePhones = (AutoCompleteTextView) findViewById(R.id.auto_complete_tv_enter_phone);
        etFirstName = (EditText) findViewById(R.id.edit_text_first_name);
        etLastName = (EditText) findViewById(R.id.edit_text_last_name);
        autoCompletePassengerCity = (AutoCompleteTextView) findViewById(R.id.auto_complete_passenger_city);
        autoCompletePassengerStreet = (AutoCompleteTextView) findViewById(R.id.auto_complete_passenger_street);
        etPassengerAptNumber = (EditText) findViewById(R.id.edit_text_apt_number);
        btnAddPassenger = (Button) findViewById(R.id.add_passenger);
        btnAddTheRide = (Button) findViewById(R.id.add_the_ride);

        Intent incomingIntent = getIntent();
        currentRide = (Ride) incomingIntent.getSerializableExtra("currentRide");

        Log.d(TAG, "onCreate: " + currentRide);

        //TODO: add autoComplete to phones from DB, if exists- fill all the data in other fields

        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(AddPassengersToRideActivity.this, R.layout.list_item_layout, CITIES);
        autoCompletePassengerCity.setAdapter(citiesAdapter);
        
        //TODO: add autoComplete to the street (according to the city??)
        
        //TODO: add autoComplete to the aptNum according to the city, street??

        //add the passenger
        btnAddPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (validateForm()) {
                String phone = autoCompletePhones.getText().toString();
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String city = autoCompletePassengerCity.getText().toString();
                String street = autoCompletePassengerStreet.getText().toString();
                String aptNumberStr = etPassengerAptNumber.getText().toString();
                int aptNumberInt = Integer.parseInt(aptNumberStr);
                Passenger newPassengerToAdd = new Passenger(phone, firstName, lastName, city, street, aptNumberInt, 0);

                Log.d(TAG, "onClick: new " + newPassengerToAdd);

                //TODO: add to DB
                //TODO: notify the observers

                Toast.makeText(AddPassengersToRideActivity.this, getString(R.string.passenger_added_successfully), Toast.LENGTH_SHORT).show();
                clearField();
                }
            }
        });

        btnAddTheRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if the fields are empty
                if(confirmFinish()) {
                    AddPassengersToRideActivity.this.finish();
                }
                else {
                    //ask the user if finish the activity also if there are some fields that are no empty
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddPassengersToRideActivity.this);
                    builder.setMessage(R.string.fields_not_empty_warning);
                    builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AddPassengersToRideActivity.this.finish();
                        }
                    });
                    builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    });
                    AlertDialog yesNoDialog = builder.create();
                    yesNoDialog.show();
                }
            }
        });
    }

    /**
     * check if the form is valid
     * @return true if valid
     */
    private boolean validateForm() {
        String phone = autoCompletePhones.getText().toString();
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String city = autoCompletePassengerCity.getText().toString();
        String street = autoCompletePassengerStreet.getText().toString();
        String aptNumberStr = etPassengerAptNumber.getText().toString();

        boolean valid = true;

        if (phone.isEmpty()) {
            autoCompletePhones.setError(getString(R.string.required_error_msg));
            valid = false;
        }
        if (firstName.isEmpty()) {
            etFirstName.setError(getString(R.string.required_error_msg));
            valid = false;
        }
        if (lastName.isEmpty()) {
            etLastName.setError(getString(R.string.required_error_msg));
            valid = false;
        }
        if (city.isEmpty()) {
            autoCompletePassengerCity.setError(getString(R.string.required_error_msg));
            valid = false;
        }
        if (!CITIES.contains(city)) {
            autoCompletePassengerCity.setError(getString(R.string.must_select_city));
            valid = false;
        }
        if (street.isEmpty()) {
            autoCompletePassengerStreet.setError(getString(R.string.required_error_msg));
            valid = false;
        }
        if (aptNumberStr.isEmpty()) {
            etPassengerAptNumber.setError(getString(R.string.required_error_msg));
            valid = false;
        }
        try {
            Integer.parseInt(aptNumberStr);
        }
        catch (NumberFormatException e) {
            etPassengerAptNumber.setError(getString(R.string.must_be_integer_error_msg));
            valid = false;
        }

        //TODO: check if auto complete from the list only for the rest of fields

        return valid;
    }

    /**
     * clear all the fields
     */
    private void clearField() {
        autoCompletePhones.getText().clear();
        etFirstName.getText().clear();
        etLastName.getText().clear();
        autoCompletePassengerCity.getText().clear();
        autoCompletePassengerStreet.getText().clear();
        etPassengerAptNumber.getText().clear();
    }

    /**
     * check if all the fields are empty
     * @return true if empty
     */
    private boolean confirmFinish() {
        return autoCompletePhones.getText().toString().isEmpty() &&
        etFirstName.getText().toString().isEmpty() &&
        etLastName.getText().toString().isEmpty() &&
        autoCompletePassengerCity.getText().toString().isEmpty() &&
        autoCompletePassengerStreet.getText().toString().isEmpty() &&
        etPassengerAptNumber.getText().toString().isEmpty();
    }
}