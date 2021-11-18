package com.example.unidad2_9sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;

public class MySmSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String cadena = null;
        if(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            SmsMessage[] mensaje = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            Log.d("mensaje", mensaje[0].getMessageBody());

            MainActivity.tv.setText(mensaje[0].getMessageBody());
        }
    }
}
