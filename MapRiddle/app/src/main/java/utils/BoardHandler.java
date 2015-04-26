package utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

public class BoardHandler {

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
