package com.example.krzysztof.magicsquare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mapriddle.mapriddle.R;

/**
 * This Activity provide the Main Menu of game
 */
public class GameMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
    }

    /**
     * this method creates 3x3 game activity
     * @param view which invoke this method
     */
    public void play3x3Level(View view) {
        Intent intent = new Intent(this, FirstLevel.class);
        startActivity(intent);
    }

    /**
     * this method creates 4x4 game activity
     * @param view which invoke this method
     */
    public void play4x4Level(View view) {
        Intent intent = new Intent(this, SecondLevel.class);
        startActivity(intent);
    }

    /**
     * this method creates 4x4
     * @param view which invoke this method
     */
    public void playTime(View view) {
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }

    /**
     * this method creates game rules activity
     * @param view which invoke this method
     */
    public void displayRules(View view) {
        Intent intent = new Intent(this, GameRules.class);
        startActivity(intent);
    }

    /**
     * this method creates best results activity
     * @param view which invoke this method
     */
    public void showBest(View view) {
        Intent intent = new Intent(this, BestGamers.class);
        startActivity(intent);
    }

    /**
     * this method disables application
     * @param view which invoke this method
     */
    public void exitApplication(View view) {
        finish();
        System.exit(0);
    }
}
