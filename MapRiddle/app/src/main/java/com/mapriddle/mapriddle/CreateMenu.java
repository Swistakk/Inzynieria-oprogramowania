package com.mapriddle.mapriddle;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.scenarios.ScenarioCreator;


public class CreateMenu extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_create);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_create, menu);
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

    public void createRiddle(View view) {
        Intent i = new Intent(this, CreateRiddleMenu.class);
        startActivity(i);
    }

    public void createPrize(View view) {
        Intent i = new Intent(this, CreatePrizeMenu.class);
        startActivity(i);
    }

    public void createScenario(View view) {
        Intent i = new Intent(this, ScenarioCreator.class);
        startActivity(i);
    }
}
