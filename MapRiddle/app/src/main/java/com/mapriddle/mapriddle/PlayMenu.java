package com.mapriddle.mapriddle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        Intent i = new Intent(this, PlayMenu.class);
        startActivity(i);
    }

    public void play_nonograms(View view) {
        Intent i = new Intent(this, PlayMenu.class);
        startActivity(i);
    }

    public void play_nurikabe(View view) {
        Intent i = new Intent(this, PlayMenu.class);
        startActivity(i);
    }

    public void play_riddles(View view) {
        Intent i = new Intent(this, RiddleMapListActivity.class);
        startActivity(i);
    }
}
