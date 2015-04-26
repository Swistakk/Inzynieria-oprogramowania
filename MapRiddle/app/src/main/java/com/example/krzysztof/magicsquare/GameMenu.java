package com.example.krzysztof.magicsquare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mapriddle.mapriddle.R;

//TODO aktywność z obrazkiem jak grać i zbindowanie jej z button'em w menu
public class GameMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
    }

    public void play3x3Level(View view) {
        Intent intent = new Intent(this, FirstLevel.class);
        startActivity(intent);
    }

    public void play4x4Level(View view) {
        Intent intent = new Intent(this, SecondLevel.class);
        startActivity(intent);
    }

    public void displayRules(View view) {
        Intent intent = new Intent(this, GameRules.class);
        startActivity(intent);
    }

    public void exitApplication(View view) {
        finish();
        System.exit(0);
    }
}
