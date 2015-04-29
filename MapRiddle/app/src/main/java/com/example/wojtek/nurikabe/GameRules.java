package com.example.wojtek.nurikabe;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mapriddle.mapriddle.R;

/**
 * Class responsible for displaying rules
 */
public class GameRules extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_rules2);
    }

    public void backToMenu(View view) {
        finish();
    }
}
