package com.example.wojtek.nurikabe;

import android.app.Activity;
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

import com.mapriddle.mapriddle.R;

import java.util.ArrayList;
import java.util.List;

import static android.util.Log.d;

/**
 * Class responsible for activity allowing to play in Nurikabe
 */
public class PlayLevel extends Activity {

    private int free_color = Color.WHITE;
    //private int background_color = Color.parseColor("#ffc107");
    private int island_color = Color.parseColor("#FFFF66");
    private int sea_color = Color.parseColor("#33CCFF");
    private int side;


    /**
     * In onCreate method we create a GameHandler generating board and creates a GridView
     * representing it
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_level);

        Bundle b = getIntent().getExtras();
        side = b.getInt("side");
        int seed = b.getInt("seed");

        GameHandler handler = new GameHandler(side);
        ArrayList<Triple> islands = handler.generateBoard(seed);
        for (Triple tr : islands) {
            System.out.println(tr.st + " " + tr.nd + " " + tr.rd);
        }
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //System.out.println(metrics.widthPixels + " " + metrics.heightPixels);
        d("Width/height", metrics.widthPixels + " " + metrics.heightPixels);

        GridView gridview = (GridView) findViewById(R.id.grid);
        FieldAdapter fa = new FieldAdapter(this);
        fa.setBoardDesc(side, islands);
        gridview.setNumColumns(side);
        gridview.setAdapter(fa);
        gridview.setBackgroundColor(Color.BLACK);
        gridview.setHorizontalSpacing(1);
        gridview.setVerticalSpacing(1);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * onItemClick changes colors of active fields with regard to rule
             * free->sea->island->free
             * @param parent
             * @param v
             * @param position
             * @param id
             */
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

    /**
     * check firstly creates a GameHandler and sets current description of board to that handler.
     * Then it calls checkBoard method returning a string informing about current
     * state of board
     * @param view
     */

    public void check(View view) {
        TextView mes = (TextView) findViewById(R.id.message);
        mes.setVisibility(TextView.VISIBLE);
        GridView gridview = (GridView) findViewById(R.id.grid);
        GameHandler handler = new GameHandler(side);
        boolean ok = true;
        for (int r = 0; r < side; r++) {
            for (int c = 0; c < side; c++) {
                int pos = r * side + c;
                TextView tv = (TextView) gridview.getChildAt(pos);
                ColorDrawable cd = (ColorDrawable) tv.getBackground();
                int current = cd.getColor();
                if (current == free_color) {
                    handler.setField(r + 1, c + 1, 0, 0);
                } else if (current == sea_color) {
                    handler.setField(r + 1, c + 1, 2, 0);
                } else {
                    int isl_sz = 0;
                    String sz_str = (tv.getText()).toString();
                    if (sz_str == "") {
                        isl_sz = 0;
                    } else {
                        isl_sz = Integer.parseInt(sz_str);
                    }
                    handler.setField(r + 1, c + 1, 1, isl_sz);
                }
            }
        }
        mes.setText(handler.checkBoard());
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
