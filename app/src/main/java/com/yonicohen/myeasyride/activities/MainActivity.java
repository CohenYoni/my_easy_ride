package com.yonicohen.myeasyride.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import com.yonicohen.myeasyride.R;

/**
 * The main activity.
 * Show calendar to select ride
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE = 1;
    private static final String[] PERMISSIONS = {
            Manifest.permission.CALL_PHONE,
            Manifest.permission.SEND_SMS
    };

    private CalendarView main_calendar;

    /**
     * check if all the permissions confirmed
     * @param context the current context
     * @param permissions string array
     * @return true if all the permissions confirmed
     */
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * request the missing permissions
     */
    private void verifyPermissions() {
        Log.d(TAG, "verifyPermissions: Checking Permissions.");

        if (!hasPermissions(MainActivity.this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS, REQUEST_CODE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_calendar = (CalendarView) findViewById(R.id.calendarMainView);

        Log.d(TAG, "onCreate: Started.");

        //go to groups activity to show the rides of the selected date
        main_calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month+1) + "/" + year;
                Intent intent = new Intent(MainActivity.this, GroupsActivity.class);
                intent.putExtra("currentDate", date);
                startActivity(intent);
            }
        });

        verifyPermissions();
    }
}