package utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Contains shortcuts for presenting toasts
 */
public class ToastPresenter {
    /**
     * Shows toast
     * @param text text to show
     * @param context application context
     */
    public static void showToast(CharSequence text, Context context){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     * Shows toast
     * @param stringId id of string resource to show
     * @param context application context
     */
    public static void showToast(int stringId, Context context){
        showToast(context.getResources().getString(stringId), context);
    }
}
