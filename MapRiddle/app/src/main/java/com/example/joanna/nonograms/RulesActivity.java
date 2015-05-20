package com.example.joanna.nonograms;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mapriddle.mapriddle.R;

import static java.lang.StrictMath.min;

public class RulesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nonograms_activity_rules);
        prepareEmptyRow(R.id.array1);
        prepareEmptyRow(R.id.array2);
        prepareEmptyRow(R.id.array3);
        prepareEmptyRow(R.id.array4);

        int[] painted2 = {7, 9, 13, 17, 19, 21};
        paint(R.id.array2, getResources().getColor(R.color.green), painted2);
        int[] painted3 = {7, 9, 13, 19, 21, 23};
        paint(R.id.array3, getResources().getColor(R.color.green),painted3);
        int[] painted4 = {9, 11, 15, 21, 23, 25};
        paint(R.id.array4, getResources().getColor(R.color.green),painted4);
    }

    private void paint(int arrayId, int color, int[] numberOfCells) {
        LinearLayout linearLayout = (LinearLayout) findViewById(arrayId);
        for (int i = 0; i < numberOfCells.length; i++) {
            TextView tv = (TextView) linearLayout.getChildAt(numberOfCells[i]);
            tv.setBackgroundColor(color);
        }
    }

    private void prepareEmptyRow(int arrayId) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        int size = 15; // because then all cells we can see on the screen
        int cellBorder = 2;
        int cellSize = min(width, height) / size - cellBorder;

        LinearLayout linearLayout = (LinearLayout) findViewById(arrayId);

        TextView[] border = new TextView[14];
        TextView[] cell = new TextView[13];
        for (int i = 0; i < 13; i++) {
            border[i] = new TextView(getApplicationContext());
            border[i].setWidth(cellBorder);
            border[i].setHeight(cellSize);
            border[i].setBackgroundColor(getResources().getColor(R.color.black));
            linearLayout.addView(border[i]);

            cell[i] = new TextView(getApplicationContext());
            cell[i].setBackgroundColor(getResources().getColor(R.color.dark_gray));
            cell[i].setTextSize(cellSize / 2);
            cell[i].setTextColor(Color.WHITE);
            cell[i].setWidth(cellSize);
            cell[i].setHeight(cellSize);

            if (i == 0) {
                cell[i].setText("2");
            } else if (i == 1) {
                cell[i].setText("1");
            } else if (i == 2) {
                cell[i].setText("3");
            } else {
                cell[i].setBackgroundColor(getResources().getColor(R.color.light_gray));
            }

            linearLayout.addView(cell[i]);
        }
        /* border of the last cell */
        border[13] = new TextView(getApplicationContext());
        border[13].setWidth(cellBorder);
        border[13].setHeight(cellSize);
        border[13].setBackgroundColor(getResources().getColor(R.color.black));
        linearLayout.addView(border[13]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rules, menu);
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
