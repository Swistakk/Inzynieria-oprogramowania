package com.example.krzysztof.magicsquare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import utils.BoardHandler;

public class SecondLevel extends LevelActivity {

    private final int SIZE = 60;
    private final int LEVELKIND = 4;
    private static int subLevel = -1;
    private final static int amount = 2;

    private final int[][] gameSolution = {
            {22, 1, 20, 7, 19, 8, 21, 2, 5, 18, 3, 24, 4, 23, 6, 17},
            {22, 12, 18, 87, 59, 57, 15, 8, 38, 17, 50, 34, 20, 53, 56, 10}
    };

    private final int[][] startConf = {
            {22, 1, 20, 7, 19, 8, 21, 2, 5, 18, 3, 24, 4, 23, 6, -1},
            {22, 12, 18, 87, 59, 57, 15, 8, 38, 17, 50, 34, 20, 53, 56, -1}
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

    public void playNextLevel(View view) {

        Intent intent = new Intent(this, FirstLevel.class);
        startActivity(intent);
        finish();
    }

    public void playSublevel(View view) {

        Intent intent = new Intent(this, SecondLevel.class);
        startActivity(intent);
        finish();
    }
}
