package com.example.joanna.nonograms;

/**
 * Created by joanna on 25.04.15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mapriddle.mapriddle.R;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "com.example.joanna.myapplication.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nonograms_activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /** Called when the user clicks the Start button */
    public void start(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);

    }
    /** Called when the user clicks the Quit button */
    public void quit(View view) {
        finish();
        System.exit(0);
    }
    /** Called when the user clicks the Rules button */
    public void showRules(View view)  {
        Intent intent = new Intent(this, RulesActivity.class);
        startActivity(intent);
    }
}
