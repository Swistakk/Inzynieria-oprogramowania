package com.example.krzysztof.magicsquare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.mapriddle.mapriddle.R;

import utils.BoardHandler;
/**
 * This class provide 4x4 level activity
 */
public class SecondLevel extends LevelActivity {

    private final int SIZE = 60;
    private final int LEVELKIND = 4;
    private static int subLevel = -1;
    private final static int amount = 2;

    private final int[][] gameSolution = {

            {22, 1, 20, 7,
             19, 8, 21, 2,
             5, 18, 3, 24,
             4, 23, 6, 17},

            {22, 12, 18, 87,
             59, 57, 15, 8,
             38, 17, 50, 34,
             20, 53, 56, 10}
    };

    private final int[][] startConf = {

            {-1, 1, -1, 7,
             19, -1, 21, -1,
             5, 18, -1, 24,
             4, -1, 6, 17},

            {22, -1, -1, -1,
             -1, 57, -1, 8,
             38, -1, 50, 34,
             20, 53, -1, -1}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        subLevel++;
        subLevel %= amount;
        setContentView(R.layout.activity_second_level);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.level2_layout);
        BoardHandler.generateBoard(layout, squeres, this, STARTX, STARTY, SIZE,
                R.layout.second_level_properties, LEVELKIND);
        BoardHandler.setSqureValues(squeres, startConf[subLevel]);
        addSquresListeners(squeres, this, gameSolution[subLevel],
                R.layout.level2_prompt);
    }

    /**
     * create 3x3 activity
     * @param view which invoke method, view is from winning alert
     */
    public void playNextLevel(View view) {

        Intent intent = new Intent(this, FirstLevel.class);
        startActivity(intent);
        finish();
    }

    /**
     * create 4x4 activity
     * @param view which invoke method, view is from winning alert
     */
    public void playSublevel(View view) {

        Intent intent = new Intent(this, SecondLevel.class);
        startActivity(intent);
        finish();
    }
}
