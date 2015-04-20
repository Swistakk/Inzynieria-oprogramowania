package com.example.krzysztof.magicsquare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import utils.BoardHandler;

public abstract class LevelActivity extends Activity {

    protected final int STARTX = 45;
    protected final int STARTY = 75;
    protected ArrayList<EditText> squeres = new ArrayList<EditText>();
    protected AlertDialog winningAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {

        super.onPause();
        if (winningAlert != null) {
            winningAlert.dismiss();
        }
    }

    public void addSquresListeners(final ArrayList<EditText> squeres, final Context context,
                                   final int[] solution, final int resource) {

        for(EditText editText : squeres) {

            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (BoardHandler.isEndOfGame(squeres, solution)) {
                        View promptsView = LayoutInflater.from(context).inflate(resource, null);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        winningAlert = alertDialogBuilder.setView(promptsView).create();
                        winningAlert.show();
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    public void backToMenu(View view) {

        finish();
    }
}
