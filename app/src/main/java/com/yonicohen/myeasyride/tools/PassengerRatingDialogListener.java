package com.yonicohen.myeasyride.tools;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;
import com.yonicohen.myeasyride.R;
import com.yonicohen.myeasyride.model.Passenger;
import java.util.Arrays;

public class PassengerRatingDialogListener implements RatingDialogListener {

    private FragmentActivity context;
    private Passenger passengerToRate;
    private AppRatingDialog ratingDialog;

    public PassengerRatingDialogListener(FragmentActivity context, Passenger passengerToRate) {
        this.context = context;
        this.passengerToRate = passengerToRate;
        AppRatingDialog.Builder builder = new AppRatingDialog.Builder()
                .setPositiveButtonText(context.getString(R.string.send_msg))
                .setNegativeButtonText(context.getString(R.string.cancel))
                .setNoteDescriptions(Arrays.asList(context.getString(R.string.very_bad_rating),
                        context.getString(R.string.not_good_rating),
                        context.getString(R.string.quite_ok),
                        context.getString(R.string.very_good),
                        context.getString(R.string.excellent)))
                .setDefaultRating(4)
                .setTitle(R.string.rate_the_passenger_title)
                .setDescription(R.string.rate_passenger_description)
                .setCommentInputEnabled(false)
                .setStarColor(R.color.starColor)
                .setNoteDescriptionTextColor(R.color.noteDescriptionTextColor)
                .setTitleTextColor(R.color.titleTextColor)
                .setDescriptionTextColor(R.color.commentTextColor)
                .setHintTextColor(R.color.hintTextColor)
                .setCommentTextColor(R.color.commentTextColor)
                .setCommentBackgroundColor(R.color.colorPrimaryDark)
                .setWindowAnimation(R.style.MyDialogFadeAnimation)
                .setCancelable(false)
                .setCanceledOnTouchOutside(false);
        ratingDialog = builder.create(context);
    }

    public void showRatingDialog() {
        ratingDialog.show();
    }

    @Override
    public void onPositiveButtonClicked(int rate, @NonNull String comment) { //TODO: not working!!!!!!!!!!!

        //TODO: add the rating to DB
        //TODO: notify the observer

        Log.d("LLLLL", "onPositiveButtonClicked: " + comment);
        Toast.makeText(context, context.getString(R.string.rating_sent), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNegativeButtonClicked() {}

    @Override
    public void onNeutralButtonClicked() {}

    //TODO: add special comment field
}