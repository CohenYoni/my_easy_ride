package com.yonicohen.myeasyride.activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import com.yonicohen.myeasyride.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    private Button btnSearchPassenger;
    private DatePickerDialog.OnDateSetListener datePickerListener;
    private TextView tvChooseAnotherDate;

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
        tvChooseAnotherDate = (TextView) findViewById(R.id.tv_choose_another_date);
        btnSearchPassenger = (Button) findViewById(R.id.btn_search_passenger);

        //TODO: add view to search a passenger (by phone or name) and watch his details
        //TODO: share into the app from facebook, whatsapp, SMS message or from contacts
        //TODO: add back button to the head of each activity

        Log.d(TAG, "onCreate: Started.");

        //go to groups activity to show the rides of the selected date
        main_calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String date = String.format(Locale.getDefault(),"%02d/%02d/%04d", dayOfMonth, (month + 1), year);
                Intent intent = new Intent(MainActivity.this, GroupsActivity.class);
                intent.putExtra("currentDate", date);
                startActivity(intent);
            }
        });

        //jump to another date dialog
        tvChooseAnotherDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String selectedDate = sdf.format(new Date(main_calendar.getDate()));
                String parts[] = selectedDate.split("/");
                int day = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]) - 1;
                int year = Integer.parseInt(parts[2]);
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        R.style.CustomDatePickerDialogTheme,
                        datePickerListener,
                        year, month, day);
                dialog.show();
            }
        });

        //jump to another date listener. change the date in the calendar when another date selected in the dialog.
        datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                long millisecondTime = calendar.getTimeInMillis();
                main_calendar.setDate(millisecondTime, false, true);
            }
        };

        //search a passenger
        btnSearchPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: search passenger activity
            }
        });

        verifyPermissions();
    }
}