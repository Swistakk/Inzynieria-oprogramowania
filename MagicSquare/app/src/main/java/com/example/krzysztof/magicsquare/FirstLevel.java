package com.example.krzysztof.magicsquare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import utils.BoardHandler;

public class FirstLevel extends LevelActivity {

    private final int SIZE = 80;
    private final int LEVELKIND = 3;
    private static int subLevel = -1;
    private final static int amount = 5;

    private final int [][] gamesSoutions = {
            {8, 1, 6, 3, 5, 7, 4, 9, 2},
            {6, 15, 3, 5, 8, 11, 13, 1, 10},
            {14, 4, 6, 0, 8, 16, 10, 12, 2},
            {24, 17, 19, 15, 20, 25, 21, 23, 16},
            {11, 13, 3, 1, 9, 17, 15, 5, 7}
    };
    private final int [][] gamesConf = {
            {8, 1, 6, 3, 5, 7, 4, 9, -1},
            {6, 15, 3, 5, 8, 11, 13, 1, -1},
            {14, 4, 6, 0, 8, 16, 10, 12, -1},
            {24, 17, 19, 15, 20, 25, 21, 23, -1},
            {11, 13, 3, 1, 9, 17, 15, 5, -1}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        subLevel++;
        subLevel %= amount;
        setContentView(R.layout.activity_first_level);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.level1_layout);
        BoardHandler.generateBoard(layout, squeres, this,
                STARTX, STARTY, SIZE, R.layout.first_level_properties, LEVELKIND);
        BoardHandler.setSqureValues(squeres, gamesConf[subLevel]);
        addSquresListeners(squeres, this, gamesSoutions[subLevel],
                R.layout.level1_prompt);
    }

    public void playNextLevel(View view) {

        Intent intent = new Intent(this, SecondLevel.class);
        startActivity(intent);
        finish();
    }

    public void playSublevel(View view) {

        Intent intent = new Intent(this, FirstLevel.class);
        startActivity(intent);
        finish();
    }
}
