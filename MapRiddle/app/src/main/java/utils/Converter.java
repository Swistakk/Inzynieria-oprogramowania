package utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * This class provide coverters from Dp unit to Pixels
 * and from Pixels to Dp unit
 */
public class Converter {

    /**
     * this method provide convertion from Dp unit to Pixels
     * @param dp amount of Dp unit to convert to pixels
     * @param context activity in which this method is invoked
     * @return pixels value of dp param
     */
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    /**
     * this method provide convertion from Pixels to Dp unit
     * @param px amount of pixels to convert to Dp unit
     * @param context activity in which this method is invoked
     * @return Dp values of px param
     */
    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);
    }
}
