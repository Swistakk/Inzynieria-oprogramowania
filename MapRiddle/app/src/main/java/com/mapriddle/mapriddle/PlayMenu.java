package com.mapriddle.mapriddle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.joanna.nonograms.StartActivity;
import com.example.krzysztof.magicsquare.GameMenu;
import com.example.wojtek.nurikabe.*;
import com.example.wojtek.nurikabe.MainActivity;

/**
 * Created by adam on 26.04.15.
 */
public class PlayMenu extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_menu);
    }

    public void play_magic_squares(View view) {
        Intent i = new Intent(this, GameMenu.class);
        startActivity(i);
    }

    public void play_nonograms(View view) {
        Intent i = new Intent(this, com.example.joanna.nonograms.MainActivity.class);
        startActivity(i);
    }

    public void play_nurikabe(View view) {
        Intent i = new Intent(this, com.example.wojtek.nurikabe.MainActivity.class);
        startActivity(i);
    }

    public void play_riddles(View view) {
        Intent i = new Intent(this, RiddleMapListActivity.class);
        startActivity(i);
    }
}
