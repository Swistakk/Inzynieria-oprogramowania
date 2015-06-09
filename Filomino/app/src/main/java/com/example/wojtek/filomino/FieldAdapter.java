package com.example.wojtek.filomino;

/**
 * Created by wojtek on 02.06.15.
 */

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.util.Log.d;

/**
 * Class allowing to create gridview adjusted to Filomino game
 */
public class FieldAdapter extends BaseAdapter {
    private int side;
    private Context context;
    private GameHandler handler;
    private int[] color;

    public FieldAdapter(Context c, int s, int[] color_) {
        color = color_;
        side = s;
        handler = new GameHandler(side);
        ArrayList<Triple> fixed = handler.generateBoard();
        handler.updateFullComps();
        context = c;
    }

    public int getCount() {
        return side * side;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public void click(int position, int active) {
        int r_click = position / side;
        int c_click = position % side;
        Field f_click = handler.board[r_click][c_click];
        if (f_click.type == FieldType.FILLED_FIXED) {
            return;
        }
        FieldType type = FieldType.EMPTY;
        if (active > 0) {
            type = FieldType.FILLED_EDITABLE;
        }
        handler.setField(r_click, c_click, type, active);
        handler.updateFullComps();
    }


    /**
     * Function returning appropriate textview used in gridview
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        //int margins = findViewById(R.id.message);
        int size = width / side - 1;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(context);
            textView.setLayoutParams(new GridView.LayoutParams(size, size));
            textView.setBackgroundColor(Color.YELLOW);
        } else {
            textView = (TextView) convertView;
        }

        int r = position / side;
        int c = position % side;
        Field f = handler.board[r][c];
        if (f.full_comp) {
            textView.setBackgroundColor(color[f.number]);
            d("Color: ", "" + color[f.number]);
        } else {
            textView.setBackgroundColor(color[0]);
        }
        if (f.number != 0) {
            textView.setText("" + f.number);
        } else {
            textView.setText("");
        }
        if (f.type == FieldType.FILLED_FIXED) {
            textView.setTextColor(Color.BLACK);
        }
        d("size: ", "" + size);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(size * 2 / 5);
        return textView;
    }

    public String checkBoard() {
        return handler.checkBoard();
    }

}