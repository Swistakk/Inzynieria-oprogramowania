package com.mapriddle.mapriddle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by adam on 26.04.15.
 */
public class CreateMenu extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finish();
        create_riddle(null);

    }


    public void create_riddle(View view) {
        Intent i = new Intent(this, RiddleMapCreateActivity.class);
        startActivity(i);
    }
}
