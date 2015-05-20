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

    private int boardSize;
    private int cellBorder;
    private int activeBoardSize;
    private int boardColor;
    private int markColor;
    private int emptyColor;
    private CharSequence[] previousCellList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nonograms_activity_start);

        cellBorder = 2;
        activeBoardSize = 10;

        CellAdapter cellAdapter = new CellAdapter(this, activeBoardSize, cellBorder); // board size,cell border
        boardSize = cellAdapter.getSize();
        markColor = cellAdapter.getMarkColor();
        boardColor = cellAdapter.getBoardColor();
        emptyColor = cellAdapter.getEmptyColor();
        previousCellList = cellAdapter.getCellList();

        GridView gridview = (GridView) findViewById(R.id.grid);
        gridview.setAdapter(cellAdapter);
        gridview.setNumColumns(boardSize);
        gridview.setBackgroundColor(Color.BLACK);
        gridview.setHorizontalSpacing(cellBorder);
        gridview.setVerticalSpacing(cellBorder);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                int x = position % boardSize;
                int y = (position - x) / boardSize;
                int framesSize = boardSize - activeBoardSize - 1;
                if ((x > framesSize) && (y > framesSize)) {

                    ColorDrawable cd = (ColorDrawable) v.getBackground();
                    int current = cd.getColor();

                    if (current == boardColor) {
                        v.setBackgroundColor(markColor);
                    } else {
                        if (current == markColor) {
                            v.setBackgroundColor(emptyColor);
                        } else {
                            v.setBackgroundColor(boardColor);
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
    /** change the active board in the cell sequence and compere it to the previous version,
     *  return true if they are identical and false otherwise
     */
    private boolean checkBoard() {
        int[][] paintedBoard = new int[activeBoardSize][activeBoardSize];
        GridView gridview = (GridView) findViewById(R.id.grid);
        int frameSize = boardSize - activeBoardSize;
        for (int i = 0; i < activeBoardSize; i++) {
            for (int j = 0; j < activeBoardSize; j++) {
                int k = (i + frameSize) * boardSize + j + frameSize;
                TextView tv = (TextView) gridview.getChildAt(k);

                ColorDrawable cd = (ColorDrawable) tv.getBackground();
                int current = cd.getColor();
                if (current == markColor) {
                    paintedBoard[j][i] = 1;
                } else {
                    paintedBoard[j][i] = 0;
                }
            }
        }
        GameCreator gc = new GameCreator(activeBoardSize, paintedBoard);
        CharSequence[] checkedCellList = gc.getCellList();
        for (int i = 0; i < previousCellList.length; i++) {
            if (previousCellList[i] != checkedCellList[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * check the correctness of filling board and show right message
     * @param view
     */
    public void check(View view) {
        if (checkBoard()) {
            TextView mes = (TextView) findViewById(R.id.message);
            mes.setVisibility(TextView.VISIBLE);
            mes.setText("    Congratulations! You win!    ");
        } else {
            TextView mes = (TextView) findViewById(R.id.message);
            mes.setVisibility(TextView.VISIBLE);
            mes.setText("    Sorry! Try again!    ");
        }

    }

    public void clear(View view) {
        GridView gridview = (GridView) findViewById(R.id.grid);
        int frameSize = boardSize - activeBoardSize;
        for (int i = frameSize; i < boardSize; i++) {
            for (int j = frameSize; j < boardSize; j++) {
                int k = i * boardSize + j;
                TextView tv = (TextView) gridview.getChildAt(k);
                tv.setBackgroundColor(boardColor);
            }
        }
    }
}

