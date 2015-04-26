package com.mapriddle.mapriddle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by adam on 26.04.15.
 */
public class MainMenu extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }


    public void play(View view) {
        Intent i = new Intent(this, PlayMenu.class);
        startActivity(i);
    }

    public void create(View view) {
        Intent i = new Intent(this, CreateMenu.class);
        startActivity(i);
    }

}
