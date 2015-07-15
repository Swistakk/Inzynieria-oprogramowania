package com.example.wojtek.filomino;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mapriddle.mapriddle.R;


public class MainFilomino extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filomino_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.filomino_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Function launching activity of playing
     * @param view
     */
    public void play(View view) {
        Intent intent = new Intent(this, SetParams.class);
        startActivity(intent);
    }

    /**
     * Function launching activity of displaying rules
     */
    public void displayRules(View view) {
        Intent intent = new Intent(this, GameRules.class);
        startActivity(intent);
    }

    /**
     * Function closing Filomino
     * @param view
     */
    public void exitApplication(View view) {
        finish();
        System.exit(0);
    }
}
