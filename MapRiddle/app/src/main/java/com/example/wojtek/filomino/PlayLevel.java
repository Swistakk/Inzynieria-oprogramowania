package com.example.wojtek.filomino;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.mapriddle.mapriddle.R;


public class PlayLevel extends Activity {

    private int[] color = {Color.WHITE, Color.parseColor("#87CEFA") /*blue*/, Color.YELLOW,
            Color.parseColor("#7CFC00") /*light green*/, Color.parseColor("#FF6347") /*light red*/,
            Color.parseColor("#20B2AA") /* sea */,
            Color.CYAN, Color.parseColor("#DDA0DD") /*violet*/, Color.parseColor("#FFA500") /*orange*/,
            Color.parseColor("#D2691E") /*brown*/};

    private int side;
    private int range = 10;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filomino_activity_play_level);

        side = 8;


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);


        final GridView gridview = (GridView) findViewById(R.id.grid);
        final FieldAdapter fa = new FieldAdapter(this, side, color);

        final GridView choose_grid = (GridView) findViewById(R.id.choose_grid);
        final ChoiceAdapter cgfa = new ChoiceAdapter(this);


        gridview.setNumColumns(side);
        gridview.setAdapter(fa);
        gridview.setBackgroundColor(Color.BLACK);
        gridview.setHorizontalSpacing(1);
        gridview.setVerticalSpacing(1);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                fa.click(position, cgfa.getActive());
                fa.notifyDataSetChanged();
                message = fa.checkBoard();
            }
        });


        cgfa.setBoardDesc(range, color);
        choose_grid.setNumColumns(range);
        choose_grid.setAdapter(cgfa);
        choose_grid.setBackgroundColor(Color.BLACK);
        choose_grid.setHorizontalSpacing(1);
        choose_grid.setVerticalSpacing(1);
        choose_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                cgfa.setActive(position);
                cgfa.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.filomino_menu_play_level, menu);
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

    public void check(View view) {
        TextView mes = (TextView) findViewById(R.id.message);
        mes.setVisibility(TextView.VISIBLE);
        if (message == "WOW! Udało Ci się!") {
            if(getIntent().getExtras().getBoolean("INFO_PLEASE")){
                setResult(RESULT_OK, new Intent());
                finish();
            }
        }
        mes.setText(message);
    }

}
