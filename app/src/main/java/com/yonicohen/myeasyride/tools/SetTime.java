package com.yonicohen.myeasyride.tools;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import com.yonicohen.myeasyride.R;
import java.util.Calendar;

/**
 * Dialog to get the time from the user.
 */
public class SetTime implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private Button selectTime;
    private Calendar myCalendar;
    private boolean is24Hour;
    private Context context;

    /**
     * Constructor
     * @param context the current context
     * @param selectTime reference to the button that clicked, for changing it time according the user choice
     * @param is24Hour true for 24H time, false for AM-PM
     */
    public SetTime(Context context, Button selectTime, boolean is24Hour) {
        this.context = context;
        this.selectTime = selectTime;
        this.is24Hour = is24Hour;
        this.myCalendar = Calendar.getInstance();
        int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = myCalendar.get(Calendar.MINUTE);
        this.setTime(hour, minute);
    }

    /**
     * When clicked, show dialog to select time, opened on the current time.
     * @param v the view of the button that was clicked
     */
    @Override
    public void onClick(View v) {
        int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = myCalendar.get(Calendar.MINUTE);
        new TimePickerDialog(context, this, hour, minute, is24Hour).show();
    }

    /**
     * config what happen when the user select time
     * @param view the TimePicker view
     * @param hourOfDay the hour that selected
     * @param minute the minute that selected
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.setTime(hourOfDay, minute);
    }

    /**
     * set the time that selected on parent view
     * @param hour the hour that selected
     * @param minute the minute that selected
     */
    private void setTime(int hour, int minute) {
        Resources res = this.context.getResources();
        this.selectTime.setText(String.format(res.getString(R.string.time_24_format), hour, minute));
    }
}