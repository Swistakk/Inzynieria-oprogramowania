package com.mapriddle.mapriddle;

import android.content.Context;
import android.widget.Toast;

public class GlobalUtils {
    public static void showToast(CharSequence text, Context context){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    public static void showToast(int stringId, Context context){
        showToast(context.getResources().getString(stringId), context);
    }
}
