package utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * This class provide static method for handling board
 */
public class BoardHandler {

    /** Generate board for MagicSquare game
     * @param viewGroup layout in which board will be generated
     * @param squeres this list will contain generated squared EditText
     * @param context activity in which this method is invoked
     * @param startX x coordinate of left upper board angle
     * @param startY y coordinate of left upper board angle
     * @param size size of square
     * @param resource properties of squared EditText
     * @param levelKind  size of board*/
    public static void generateBoard(ViewGroup viewGroup,
        ArrayList<EditText> squeres, Context context, int startX, int startY,
        int size, int resource, int levelKind) {

        int x = startX;
        int y = startY;
        int row = 1;
        int column = 1;
        EditText greenSquare;
        for (int i = 0; i < levelKind * levelKind; i++) {
            greenSquare = (EditText) LayoutInflater.from(context)
                    .inflate(resource, viewGroup, false);
            squeres.add(greenSquare);
            greenSquare.setY(Converter.convertDpToPixel(y, context));
            greenSquare.setX(Converter.convertDpToPixel(x, context));
            viewGroup.addView(greenSquare);
            if (column == levelKind) {
                x = startX;
                y += size;
                row++;
                column = 1;
            } else {
                x += size;
                column++;
            }
        }
    }

    /**
     * Sets text and Focusable property for every square
     * @param squeres squares in which this method sets properties
     * @param startConf gameConfiguration
     */
    public static void setSqureValues(ArrayList<EditText> squeres ,int[] startConf) {

        for (int i = 0; i < squeres.size(); i++) {
            if (startConf[i] != -1) {
                squeres.get(i).setText("" + startConf[i]);
                squeres.get(i).setFocusable(false);
            } else {
                squeres.get(i).setText("");
                squeres.get(i).setFocusable(true);
            }
        }
    }

    /**
     * check is a end of current game
     * @param squeres squares of current level
     * @param solution winning configuration
     * @return true if squares are in winning configuration
     */
    public static boolean isEndOfGame(final ArrayList<EditText> squeres, final int[] solution) {

        for (int i = 0; i < squeres.size(); i++) {
            if (!squeres.get(i).getText().toString().equals("" +
                    solution[i])) {
                return false;
            }
        }
        return true;
    }
}
