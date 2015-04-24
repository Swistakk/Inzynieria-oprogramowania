
package com.example.joanna.nonograms;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by joanna on 21.04.15.
 */
public class TextAdapter extends BaseAdapter {
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
        
        DisplayMetrics displayMetrics=getResources().getDisplayMetrics();
        screen_width=displayMetrics.widthPixels;    //width of the device screen
        screen_height=displayMetrics.heightPixels;   //height of device screen

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(60, 30));
            //textView.setScaleType(TextView.ScaleType.CENTER_CROP);
            //textView.setPadding(1, 1, 1, 1);
            textView.setBackgroundColor(Color.WHITE);
            //textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
            //textView.setWidth(30);
            //textView.setMinimumHeight(30);
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(mThumbIds[position]);
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

            /*R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7*/
    };
}

