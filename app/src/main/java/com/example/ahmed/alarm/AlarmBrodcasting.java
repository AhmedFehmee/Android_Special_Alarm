package com.example.ahmed.alarm;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class AlarmBrodcasting extends ActionBarActivity {

    EditText etTimeSecondAlarm;
    EditText etTimeMinutAlarm;
    EditText etTimeHourAlarm;

    TextView alarm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_brodcasting);


        etTimeSecondAlarm = (EditText) findViewById(R.id.et_time);
        etTimeMinutAlarm = (EditText) findViewById(R.id.et_minut);
        etTimeHourAlarm = (EditText) findViewById(R.id.et_hour);

        alarm = (TextView) findViewById(R.id.textView);

    }

    public void startAlarm(View v) {
        if (etTimeHourAlarm.getText().toString().isEmpty()) {
            etTimeHourAlarm.setError("hour is required !");
        } else if (etTimeMinutAlarm.getText().toString().isEmpty()) {
            etTimeMinutAlarm.setError("minute is required !");
        } else if (etTimeSecondAlarm.getText().toString().isEmpty()) {
            etTimeSecondAlarm.setError("sec is required !");
        } else {
            Calendar c = Calendar.getInstance();
            int currentSeconds = c.get(Calendar.SECOND);
            int currentMinute = c.get(Calendar.MINUTE);
            int currentHour = c.get(Calendar.HOUR_OF_DAY);

            int sec = Integer.parseInt(etTimeSecondAlarm.getText().toString());
            int min = Integer.parseInt(etTimeMinutAlarm.getText().toString());
            int hou = Integer.parseInt(etTimeHourAlarm.getText().toString());
            int day = 0;

            if (sec >= 60) {
                etTimeSecondAlarm.setError("Max sec is 60 !");
                etTimeSecondAlarm.setText("");
                etTimeSecondAlarm.requestFocus();
            } else if (min >= 60) {
                etTimeMinutAlarm.setError("Max Minute is 60 !");
                etTimeMinutAlarm.setText("");
                etTimeMinutAlarm.requestFocus();
            } else if (hou >= 24) {
                etTimeHourAlarm.setError("Max hou is 24 !");
                etTimeHourAlarm.setText("");
                etTimeHourAlarm.requestFocus();
            } else {
                if (currentSeconds > sec) {
                    sec = sec + 60;
                    min = min - 1;
                }

                if (currentMinute > min) {
                    min = min + 60;
                    hou = hou - 1;
                }
                if (currentHour > hou) {
                    hou = hou + 24;
                }


                long alarmSecond = sec - currentSeconds;
                long alarmMinute = min - currentMinute;
                long alarmHour = hou - currentHour;

                Intent startBroadcastin = new Intent(AlarmBrodcasting.this, AlarmRec.class);
                long timeLeft = (alarmHour * 1000 * 60 * 24) + (alarmMinute * 1000 * 60) + (alarmSecond * 1000);

                startBroadcastin.putExtra("send", "start");

                PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 1, startBroadcastin, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + timeLeft, pendingIntent);

                if (alarmHour == 0 & alarmMinute == 0) {
                    Toast.makeText(AlarmBrodcasting.this, "Alarm will sound in " +
                            alarmSecond + " Second", Toast.LENGTH_LONG).show();
                    alarm.setText("Alarm will sound in " + alarmSecond + " second ");
                } else if (alarmHour == 0) {
                    Toast.makeText(AlarmBrodcasting.this, "Alarm will sound in " +"\n"+
                            alarmMinute + " Minute " +"\n"+
                            alarmSecond + " Second", Toast.LENGTH_LONG).show();
                    alarm.setText("Alarm will sound in " +"\n"+
                            alarmMinute +" Minute  " +"\n"+
                            alarmSecond + " second ");

                } else if (alarmHour != 0 & alarmMinute != 0 & alarmSecond != 0) {
                    Toast.makeText(AlarmBrodcasting.this, "Alarm will sound in " +"\n"+
                            alarmHour + " HOUR /" +"\n"+
                            alarmMinute + " Minute /" +"\n"+
                            alarmSecond + " Second", Toast.LENGTH_LONG).show();
                    alarm.setText("Alarm will sound in " +"\n"+
                            alarmHour + " HOUR  " +"\n"+
                            alarmMinute + " Minute  " +"\n"+
                            alarmSecond + " second ");

                }

                Button start_Alarm = (Button) findViewById(R.id.btn_start);
                start_Alarm.setVisibility(View.GONE);

                Button new_Alarm = (Button) findViewById(R.id.btn_new);
                new_Alarm.setVisibility(View.VISIBLE);

            }
        }
    }


    public void newAlarm(View v) {
        Button new_Alarm = (Button) findViewById(R.id.btn_new);
        new_Alarm.setVisibility(View.GONE);

        Button start_Alarm = (Button) findViewById(R.id.btn_start);
        start_Alarm.setVisibility(View.VISIBLE);

    }
}
