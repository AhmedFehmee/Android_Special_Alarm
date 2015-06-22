package com.example.ahmed.alarm;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Alarm_View extends ActionBarActivity {

    EditText answer;
    TextView question;
    Button stop;
    MediaPlayer mP;
    Vibrator vib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm__view);

        question = (TextView) findViewById(R.id.tv_question);
        answer = (EditText) findViewById(R.id.et_answer);
        stop = (Button) findViewById(R.id.btn_stop);


        String operation = null;
        int answerQuestion = 0;
        int num1 = (int) (Math.random() * 10) + 1;
        int num2 = (int) (Math.random() * 10) + 1;
        int operator = (int) (Math.random() * 4) + 1;

        if (operator == 1) {
            operation = "+";
            answerQuestion = num1 + num2;
        }
        if (operator == 2) {
            operation = "-";
            if (num1<num2){
                num1 = num2 + (int) (Math.random() * 10);
                }
            answerQuestion = num1 - num2;
        }
        if (operator == 3){
            operation = "*";
            answerQuestion = num1 * num2;
        }
        if (operator == 4) {
            operation = "/";
            if (num2 == 0){
                num2 =  num1+ (int)(Math.random() * 10) + 2;
            }
            answerQuestion = num1 / num2;
        }

        question.setText(num1 + operation + num2);
        final String finalAnswer = String.valueOf(answerQuestion);

        Intent i = getIntent();
        String extras = i.getExtras().getString("send");

        if (extras.equals("start")) {

            mP = MediaPlayer.create(getApplicationContext(), R.raw.nay);
            mP.setVolume(1.0f, 1.0f);
            mP.setLooping(true);
            mP.start();

            vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            long[] pattern = {1000, 200, 200, 200};
            vib.vibrate(pattern, 0);

        }

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer.getText().toString().equals(finalAnswer)){
                    mP.stop();
                    mP.release();
                    mP = null;
                    vib.cancel();
                    Toast.makeText(getApplicationContext(), "Good Morning", Toast.LENGTH_SHORT).show();
                    Intent new_Alarm = new Intent(Alarm_View.this, AlarmBrodcasting.class);
                    startActivity(new_Alarm);
                }else {
                    Toast.makeText(getApplicationContext(), "wrong Answer", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Disable physical Buttons >> true
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_HOME)) {
            Toast.makeText(this, "can't press the home button!", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_POWER){
            Toast.makeText(this, "can't press power", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_CAMERA) {
            Toast.makeText(this, "Pressed Camera Button", Toast.LENGTH_LONG).show();
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            Toast.makeText(this, "can't Volume Up", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            Toast.makeText(this, "can't Volume Down", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (false)
           // Toast.makeText(this, "can't back", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}

