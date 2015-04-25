
package com.example.joanna.nonograms;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import static java.lang.StrictMath.min;

/**
 * Created by joanna on 21.04.15.
 */
public class TextAdapter extends BaseAdapter {
    private static final int COLUMN_NR = 13 ;
    private Context mContext;

    public TextAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        int size = min(width, height) / 13 - 1;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(size, size));
            textView.setBackgroundColor(Color.parseColor("#6a1b9a"));

        } else {
            textView = (TextView) convertView;
        }

        textView.setText(mThumbIds[position]);
        int x = position % COLUMN_NR;
        int y = (position - x) / COLUMN_NR + 1;

        if ((x > COLUMN_NR - 11) && (y > COLUMN_NR - 10)) {
            textView.setBackgroundColor(Color.GRAY);
        }
        return textView;
    }

    // references to our images
    protected CharSequence[] mThumbIds = {
            "", "", "", "", "", "",  "2",  "",  "", "2",  "", "", "",
            "", "", "", "", "", "3", "2", "5", "5", "2", "3", "", "",
            "", "", "",  "", "4", "2", "2", "2", "2", "2", "2", "4", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "",
            "", "", "4", "", "", "", "", "", "", "", "", "", "",
            "", "", "6", "", "", "", "", "", "", "", "", "", "",
            "2", "2", "2", "", "", "", "", "", "", "", "", "", "",
            "", "", "8", "", "", "", "", "", "", "", "", "", "",
            "1", "4", "1", "", "", "", "", "", "", "", "", "", "",
            "", "2", "2", "", "", "", "", "", "", "", "", "", "",
            "", "", "6", "", "", "", "", "", "", "", "", "", "",
            "", "", "4", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "",
    };

    public int[] getSolution = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 1, 1, 1, 1, 0, 0, 0,
        0, 0, 1, 1, 1, 1, 1, 1, 0, 0,
        0, 1, 1, 0, 1, 1, 0, 1, 1, 0,
        0, 1, 1, 1, 1, 1, 1, 1, 1, 0,
        0, 1, 0, 1, 1, 1, 1, 0, 1, 0,
        0, 1, 1, 0, 0, 0, 0, 1, 1, 0,
        0, 0, 1, 1, 1, 1, 1, 1, 0, 0,
        0, 0, 0, 1, 1, 1, 1, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    };
}

