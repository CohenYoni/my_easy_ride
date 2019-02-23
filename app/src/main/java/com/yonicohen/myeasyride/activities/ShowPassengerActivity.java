package com.yonicohen.myeasyride.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.yonicohen.myeasyride.R;
import com.yonicohen.myeasyride.model.Passenger;
import com.yonicohen.myeasyride.tools.PassengerRatingDialogListener;
import com.yonicohen.myeasyride.tools.SendMassageListener;
import java.util.ArrayList;

/**
 * Show the details of the passenger, rate, call or send SMS to him or remove him from the ride
 */
public class ShowPassengerActivity extends AppCompatActivity {

    private static final String TAG = "ShowPassengerActivity";

    private Passenger currentPassenger;
    private Button btnCall;
    private Button btnSendMsg;
    private Button btnDelete;
    private Button btnRate;
    private TextView tvFullName;
    private TextView tvPhone;
    private TextView tvAddress;
    private TextView tvCity;
    private TextView tvRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_passenger_layout);
        btnCall = (Button) findViewById(R.id.btn_call);
        btnSendMsg = (Button) findViewById(R.id.btn_private_msg);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnRate = (Button) findViewById(R.id.btn_rate);
        tvFullName = (TextView) findViewById(R.id.full_name);
        tvPhone = (TextView) findViewById(R.id.phone);
        tvAddress = (TextView) findViewById(R.id.address);
        tvCity = (TextView) findViewById(R.id.city);
        tvRating = (TextView) findViewById(R.id.rating);

        Log.d(TAG, "onCreate: Started.");

        //get the passenger details from incoming intent
        Intent incoming_intent = getIntent();
        currentPassenger = (Passenger) incoming_intent.getSerializableExtra("currentPassenger");

        //show the passenger details
        String fullName = currentPassenger.getFirst_name() + " " + currentPassenger.getLast_name();
        tvFullName.setText(fullName);
        String address = currentPassenger.getStreet() + " " + currentPassenger.getApt_number();
        tvAddress.setText(address);
        tvCity.setText(currentPassenger.getCity());
        tvPhone.setText(currentPassenger.getPhone_number());
        tvRating.setText(String.valueOf(currentPassenger.getRating()));
        //TODO: set observer when the rating changed

        //send SMS message to the passenger
        ArrayList<String> phoneNumber = new ArrayList<>();
        phoneNumber.add(currentPassenger.getPhone_number());
        btnSendMsg.setOnClickListener(new SendMassageListener(ShowPassengerActivity.this, phoneNumber));

        //call the passenger
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + currentPassenger.getPhone_number()));
                startActivity(callIntent);
            }
        });

        //remove the passenger from the ride
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: delete from the current ride- from database
                //TODO: notify the observers

                Toast.makeText(ShowPassengerActivity.this, getString(R.string.deleted_successfully), Toast.LENGTH_LONG).show();
                finish();
            }
        });

        //rate the passenger
        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PassengerRatingDialogListener dialog = new PassengerRatingDialogListener(ShowPassengerActivity.this, currentPassenger);
                dialog.showRatingDialog();
            }
        });
    }
}