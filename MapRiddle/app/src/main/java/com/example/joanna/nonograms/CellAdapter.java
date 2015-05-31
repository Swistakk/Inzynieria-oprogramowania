
package com.example.joanna.nonograms;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.StrictMath.min;

/**
 * Created by joanna on 21.04.15.
 */
public class CellAdapter extends BaseAdapter {

    private int size;
    private  int activeBoardSize;
    private Context mContext;
    private CharSequence[] cellList;
    private int cellBorder;

    private int markColor = Color.parseColor("#8ec127");
    private int emptyColor = Color.WHITE;
    private int backgroundColor = Color.parseColor("#424242");
    private int boardColor = Color.GRAY;

    public CellAdapter(Context c, int activeBoardSize, int cellBorder) {
        mContext = c;
        this.activeBoardSize = activeBoardSize;
        this.cellBorder = cellBorder;
        cellList = createBoard();
    }

    public int getCount() {
        return cellList.length;
    }

    public int getSize() {
        return size;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    /**
     * create a new TextView for each item referenced by the Adapter
     * @param position number of build added cell
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        int cellSize = min(width, height) / size - cellBorder;

        if (convertView == null) {
            /* if it's not recycled, initialize some attributes */
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(cellSize, cellSize));
            textView.setBackgroundColor(backgroundColor);
            textView.setTextSize(cellSize / 2);
            textView.setTextColor(Color.WHITE);

        } else {
            textView = (TextView) convertView;
        }

        textView.setText(cellList[position]);
        /* calculate the coordinates of built cell on board size x size */
        int x = position % size; /* 0, 1, ..., size - 1 */
        int y = (position - x) / size; /* 0, 1, ..., size - 1 */

        int frameSize = size - activeBoardSize;

        if ((x > frameSize - 1) && (y > frameSize - 1)) { // numeration from zero
            textView.setBackgroundColor(boardColor);
        }
        return textView;
    }

    public CharSequence[] getCellList() {
        return cellList;
    }

    /* references to our cells */
    private CharSequence[] createBoard() {
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("S");
        int seed = Integer.parseInt(sdf.format(cal.getTime()));

        GameCreator gameCreator = new GameCreator(activeBoardSize, seed);
        size = gameCreator.getTotalSize();
        return gameCreator.getCellList();
    }

    public int getMarkColor() {
        return markColor;
    }
    public int getBackgroundColor() {
        return backgroundColor;
    }
    public int getEmptyColor() {
        return emptyColor;
    }
    public int getBoardColor() {
        return boardColor;
    }
}

