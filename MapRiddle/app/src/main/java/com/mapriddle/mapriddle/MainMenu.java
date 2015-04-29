package com.mapriddle.mapriddle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Main menu of the application
 */
public class MainMenu extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    /**
     * Opens menu containing available games
     * @param view view that invoked the method
     */
    public void play(View view) {
        Intent i = new Intent(this, PlayMenu.class);
        startActivity(i);
    }

    /**
     * Opens menu containing available game creators
     * @param view view that invoked the method
     */
    public void create(View view) {
        Intent i = new Intent(this, CreateMenu.class);
        startActivity(i);
    }

}
