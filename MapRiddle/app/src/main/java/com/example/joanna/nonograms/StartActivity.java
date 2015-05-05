package com.example.joanna.nonograms;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.mapriddle.mapriddle.R;


public class StartActivity extends ActionBarActivity {

    private static final int BOARD_HEIGHT = 10;
    private static final int COLUMN_NR = 14;
    private static final int BOARD_WIDTH = 10;

    private int mark_color = Color.parseColor("#ab47bc");
    private int free_color = Color.WHITE;
    //private int background_color = Color.parseColor("#ffc107");
    private int board_color = Color.GRAY;

    private int[] solution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        GridView gridview = (GridView) findViewById(R.id.grid);
        TextAdapter ta = new TextAdapter(this);
        gridview.setAdapter(ta);
        solution = ta.getSolution;
        gridview.setNumColumns(COLUMN_NR);
        gridview.setBackgroundColor(Color.BLACK);
        gridview.setHorizontalSpacing(1);
        gridview.setVerticalSpacing(1);



        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                int x = position % COLUMN_NR;
                int y = (position - x) / COLUMN_NR + 1;

                if ((x > COLUMN_NR - BOARD_WIDTH - 1) && (y > COLUMN_NR - BOARD_HEIGHT)) {

                    ColorDrawable cd = (ColorDrawable) v.getBackground();
                    int current = cd.getColor();

                    if (current == board_color) {
                        v.setBackgroundColor(mark_color);
                    } else {
                        if (current == mark_color) {
                            v.setBackgroundColor(free_color);
                        } else {
                            v.setBackgroundColor(board_color);
                        }
                    }
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
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
        // Do something in response to button
        //Intent intent = new Intent(this, StartActivity.class);
        //startActivity(intent);
        int s = 0;
        GridView gridview = (GridView) findViewById(R.id.grid);
        for (int i = 3; i < 14; i++) {
            for (int j = 3; j < 14; j++) {
                int k = i * COLUMN_NR + j;
                TextView tv = (TextView) gridview.getChildAt(k);

                ColorDrawable cd = (ColorDrawable) tv.getBackground();
                int current = cd.getColor();
                if (((current == mark_color) && (solution[s] == 1)) ||
                        (((current == board_color) || (current == free_color)) && (solution[s] == 0))) {
                    if (s == 99) {
                        TextView mes = (TextView) findViewById(R.id.message);
                        mes.setVisibility(TextView.VISIBLE);
                        mes.setText("    Congratulations! You win!    ");
                    }
                } else {
                    TextView mes = (TextView) findViewById(R.id.message);
                    mes.setVisibility(TextView.VISIBLE);
                    mes.setText("    Sorry! Try again!    ");
                    break;
                }
                s++;
            }
        }

    }
}

