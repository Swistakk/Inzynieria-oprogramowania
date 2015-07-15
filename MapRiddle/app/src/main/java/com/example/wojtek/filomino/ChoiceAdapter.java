package com.example.wojtek.filomino;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by wojtek on 09.06.15.
 */
public class ChoiceAdapter extends BaseAdapter {
    private int columnsNum;
    private Context context;
    private CharSequence[] boardDesc;
    private int[] color;
    int active;

    public ChoiceAdapter(Context c) {
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

    public void setBoardDesc(int len, int[] color_) {
        active = 0;
        color = new int[len];
        columnsNum = len;
        boardDesc = new CharSequence[len];
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                boardDesc[i] = "X";
            } else {
                boardDesc[i] = "" + i;
            }
            color[i] = color_[i];
        }
        color[0] = Color.parseColor("#DCDCDC");
    }

    public void setActive(int position) {
        active = position;
        Log.d("Active=", "" + active);
    }

    public int getActive() {
        return active;
    }

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
        } else {
            textView = (TextView) convertView;
        }

        if (position == active) {
            textView.setBackgroundColor(color[position]);
        } else {
            textView.setBackgroundColor(Color.WHITE);
        }
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(size * 2 / 5);
        textView.setText(boardDesc[position]);
        return textView;
    }

}
