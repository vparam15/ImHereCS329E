package com.example.thu.imhere;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by Thu on 5/3/2016.
 */
public class MyAlarmService extends Service {

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub

        Toast.makeText(this, "Your message is on the way!", Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        Toast.makeText(this, "MyAlarmService.onBind()", Toast.LENGTH_LONG).show();
        return null;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Toast.makeText(this, "MyAlarmService.onDestroy()", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);

        Toast.makeText(this, "Sent!", Toast.LENGTH_LONG).show();

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(Message.getNumber(), null, Message.getTextString(), null, null);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        Toast.makeText(this, "MyAlarmService.onUnbind()", Toast.LENGTH_LONG).show();
        return super.onUnbind(intent);
    }


}
