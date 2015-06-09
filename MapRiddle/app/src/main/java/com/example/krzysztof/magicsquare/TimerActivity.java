package com.example.krzysztof.magicsquare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mapriddle.mapriddle.R;

import java.util.ArrayList;

import utils.BoardHandler;

public class TimerActivity extends LevelActivity {

    private final int SIZE = 80;
    private final int LEVELKIND = 3;
    private static int subLevel = -1;
    private final static int amount = 5;

    private TextView timer;
    private long startTime = 0;

    public final static String USER_TIME = "Result";

    Handler timerHandler = new Handler();

    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            timer.setText(String.format("%02d:%02d", minutes, seconds));
            timerHandler.postDelayed(this, 500);
        }
    };

    private final int [][] gamesSolutions = {

                    {8, 1, 6,
                    3, 5, 7,
                    4, 9, 2},

                    {6, 15, 3,
                    5, 8, 11,
                    13, 1, 10},

                    {14, 4, 6,
                    0, 8, 16,
                    10, 12, 2},

                    {24, 17, 19,
                    15, 20, 25,
                    21, 23, 16},

                    {11, 13, 3,
                    1, 9, 17,
                    15, 5, 7}
    };

    private final int [][] gamesConf = {

                    {8, -1, -1,
                    3, -1, -1,
                    4, 9, -1},

                    {6, 15, 3,
                    -1, -1, -1,
                    13, -1, -1},

                    {-1, -1, 6,
                    -1, 8, -1,
                    10, 12, -1},

                    {24, -1, 19,
                    -1, 20, -1,
                    21, -1, -1},

                    {11, -1, 3,
                    -1, -1, -1,
                    15, -1, 7}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        subLevel++;
        subLevel %= amount;
        setContentView(R.layout.activity_timer);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.timer_layout);
        BoardHandler.generateBoard(layout, squeres, this,
                STARTX, STARTY, SIZE, R.layout.first_level_properties, LEVELKIND);
        BoardHandler.setSqureValues(squeres, gamesConf[subLevel]);

        timer = (TextView) layout.findViewById(R.id.timer);
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
        addListeners(squeres, this, gamesSolutions[subLevel]);
    }

    /**
     * this method dismissing AlertDialog window
     */
    @Override
    public void onPause() {

        super.onPause();
        if (winningAlert != null) {
            winningAlert.dismiss();
        }
    }

    protected void addListeners(final ArrayList<EditText> squeres, final Context context,
                                      final int[] solution) {

        for(EditText editText : squeres) {

            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (BoardHandler.isEndOfGame(squeres, solution)) {
                        Intent intent = new Intent(context, Winning.class);
                        intent.putExtra(USER_TIME, timer.getText());
                        startActivity(intent);
                        finish();
                        return true;
                    }
                    return false;
                }
            });
        }
    }
}
