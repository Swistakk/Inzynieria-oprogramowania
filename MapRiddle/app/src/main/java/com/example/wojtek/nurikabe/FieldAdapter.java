package com.example.wojtek.nurikabe;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wojtek on 26.04.15.
 */
public class FieldAdapter extends BaseAdapter {
    private int columnsNum;
    private Context context;
    private CharSequence[] boardDesc;

    public FieldAdapter(Context c) {
        context = c;
    }

    public int getCount() {
        return boardDesc.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public void setBoardDesc(int side, ArrayList<Triple> nonZero) {
        columnsNum = side;
        boardDesc = new CharSequence[side * side];
        for (int i = 0; i < side * side; i++) {
            boardDesc[i] = "";
        }
        for (Triple tr : nonZero) {
            boardDesc[side * (tr.st - 1) + tr.nd - 1] = tr.rd + "";
        }
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        int size = width / columnsNum - 1;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(context);
            textView.setLayoutParams(new GridView.LayoutParams(size, size));
            if (boardDesc[position] != "") {
                textView.setBackgroundColor(Color.parseColor("#FFFF65"));
            } else {
                textView.setBackgroundColor(Color.WHITE);
            }
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(boardDesc[position]);
        return textView;
    }

}
