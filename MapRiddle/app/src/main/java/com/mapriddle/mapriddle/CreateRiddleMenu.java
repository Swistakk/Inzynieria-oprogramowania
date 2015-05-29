package com.mapriddle.mapriddle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Activity presenting list of games that can be created by users. Currently it has only one option, so it is chosen automatically.
 */
public class CreateRiddleMenu extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finish();
        create_riddle(null);

    }

    /**
     * Starts riddle creator
     * @param view view that invoked this method
     */
    public void create_riddle(View view) {
        Intent i = new Intent(this, RiddleMapCreateActivity.class);
        startActivity(i);
    }
}
