package com.example.ahmed.alarm;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.media.MediaPlayer;
import android.widget.Toast;



public class AlarmRec extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent arg1) {

        String extras = arg1.getExtras().getString("send");

        if (extras.equals("start")) {
            Intent alarm_view = new Intent(arg0.getApplicationContext(), Alarm_View.class);
            alarm_view.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            alarm_view.putExtra("send",extras);
            arg0.startActivity(alarm_view);

            Toast.makeText(arg0, "Do NOT panic.. But your time is UP.!", Toast.LENGTH_LONG).show();

        }else{
            Intent alarm_sound = new Intent(arg0.getApplicationContext(), Alarm_View.class);
            alarm_sound.putExtra("send",extras);
            alarm_sound.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            arg0.startActivity(alarm_sound);
        }
    }
}





