package com.example.krzysztof.magicsquare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mapriddle.mapriddle.R;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class Winning extends Activity {

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning);
        Intent intent = getIntent();
        String userTime = intent.getStringExtra(TimerActivity.USER_TIME);
        TextView result = (TextView) findViewById(R.id.result);
        result.setText(userTime);
    }

    public void saveResult(View view) {

        EditText editName = (EditText) findViewById(R.id.gamerName);
        String name = editName.getText().toString();
        TextView textResult = (TextView) findViewById(R.id.result);
        String result = textResult.getText().toString();
        final ParseObject gameResult = new ParseObject("SQUARESRESULT");
        gameResult.put("GamerName", name);
        gameResult.put("GamerResult", result);
        gameResult.saveInBackground(new SaveCallback() {

            @Override
            public void done(ParseException e) {

                final Context context = getApplicationContext();
                final int duration = Toast.LENGTH_SHORT;
                if(e == null) {
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            Toast toast = Toast.makeText(context, "Pomyślnie zapisano wynik twojej gry", duration);
                            toast.show();
                            EditText editName = (EditText) findViewById(R.id.gamerName);
                            editName.setFocusable(false);
                            Button saveButton = (Button) findViewById(R.id.save);
                            saveButton.setClickable(false);
                        }
                    });
                } else {
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            Toast toast = Toast.makeText(context, "Nie zapisano wyniku twojej gry. " +
                                    "Sprawdź swoje połączenie z Internetem", duration);
                            toast.show();
                        }
                    });
                }
            }
        });
    }

    public void showBestResults(View view) {

        Intent intent = new Intent(this, BestGamers.class);
        startActivity(intent);
        finish();
    }

    public void backToMenu(View view) {

        finish();
    }
}
