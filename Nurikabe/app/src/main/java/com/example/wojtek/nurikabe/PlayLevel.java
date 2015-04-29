package com.example.wojtek.nurikabe;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.util.Log.d;


public class PlayLevel extends ActionBarActivity {

    private int free_color = Color.WHITE;
    //private int background_color = Color.parseColor("#ffc107");
    private int island_color = Color.parseColor("#FFFF66");
    private int sea_color = Color.parseColor("#33CCFF");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_level);

        GameHandler handler = new GameHandler(10);
        ArrayList<Triple> islands = handler.generateBoard(23);
        for (Triple tr : islands) {
            System.out.println(tr.st + " " + tr.nd + " " + tr.rd);
        }
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //System.out.println(metrics.widthPixels + " " + metrics.heightPixels);
        d("Width/height", metrics.widthPixels + " " + metrics.heightPixels);

        GridView gridview = (GridView) findViewById(R.id.grid);
        FieldAdapter fa = new FieldAdapter(this);
        fa.setBoardDesc(10, islands);
        gridview.setNumColumns(10);
        gridview.setAdapter(fa);
        gridview.setBackgroundColor(Color.BLACK);
        gridview.setHorizontalSpacing(2);
        gridview.setVerticalSpacing(1);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                    int position, long id) {

                ColorDrawable cd = (ColorDrawable) v.getBackground();
                int current = cd.getColor();

                if (current == free_color) {
                    v.setBackgroundColor(sea_color);
                } else if (current == sea_color) {
                    v.setBackgroundColor(island_color);
                } else if (current == island_color) {
                    v.setBackgroundColor(free_color);
                }
            }
        });

    }

    public void check(View view) {
        GameHandler handler = new GameHandler(10);
        TextView mes = (TextView) findViewById(R.id.message);
        mes.setVisibility(TextView.VISIBLE);
        mes.setText("    Sprawdzanie to be done :)!    ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play_level, menu);
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
}
