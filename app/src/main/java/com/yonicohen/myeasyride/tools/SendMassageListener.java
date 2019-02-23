package com.yonicohen.myeasyride.tools;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.yonicohen.myeasyride.R;
import java.util.ArrayList;

/**
 * Custom listener to send SMS message.
 */
public class SendMassageListener implements View.OnClickListener {

    private static final String TAG = "SendMassageListener";

    private Context context;
    private ArrayList<String> numbersToSend; //can be just one number or many
    private EditText msg;
    private Button btnSend;
    private AlertDialog dialog;

    /**
     * Constructor
     * @param context current context
     * @param numbersToSend list of numbers to send them message (can be just one number)
     */
    public SendMassageListener(Context context,ArrayList<String> numbersToSend) {
        super();
        this.context = context;
        this.numbersToSend = numbersToSend;
    }

    /**
     * When clicked, open AlertDialog to write the message and send it when 'Send' button click.
     * @param v the view of the button that clicked
     */
    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: Started.");

        //references to the elements of the view
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.dialog_send_message, null);
        msg = (EditText) mView.findViewById(R.id.enter_msg);
        btnSend = (Button) mView.findViewById(R.id.btn_send_msg);

        //config the dialog
        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        //send the message
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textMsg = msg.getText().toString();
                if (textMsg.isEmpty()) {
                    Toast.makeText(context, context.getString(R.string.cant_send_empty_msg), Toast.LENGTH_LONG).show();
                }
                else {
                    sendLongSMS(textMsg);
                    dialog.cancel();
                    Toast.makeText(context, context.getString(R.string.sent_successful), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Send the message to all numbers.
     * @param message the body of the message
     */
    private void sendLongSMS(String message) {
        SmsManager smsManager = SmsManager.getDefault();
        for (String phoneNumber : this.numbersToSend) {
            ArrayList<String> parts = smsManager.divideMessage(message);
            smsManager.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
        }
    }
}