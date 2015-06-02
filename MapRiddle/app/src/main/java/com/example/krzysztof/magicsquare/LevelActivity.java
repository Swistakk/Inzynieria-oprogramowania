package com.example.krzysztof.magicsquare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Level;

import utils.BoardHandler;

/**
 * Abstract class which provide common basic properties of level game activity
 */
public abstract class LevelActivity extends Activity {

    protected final int STARTX = 45;
    protected final int STARTY = 75;
    protected ArrayList<EditText> squeres = new ArrayList<EditText>();
    protected AlertDialog winningAlert;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    /**
     * this method dismissing AlertDialog window
     */
    @Override
    public void onPause() {

        super.onPause();
        if (winningAlert != null) {
            winningAlert.dismiss();
        }
    }

    protected void addSquresListeners(final ArrayList<EditText> squeres, final Context context,
                                   final int[] solution, final int resource) {

        for(EditText editText : squeres) {

            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (BoardHandler.isEndOfGame(squeres, solution)) {
                        if(getIntent().getExtras().getBoolean("INFO_PLEASE")){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    LevelActivity.this.setResult(RESULT_OK, new Intent());
                                    LevelActivity.this.finish();
                                }
                            });
                            return false;
                        }

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

    /**
     * this method disables level activity
     * @param view which invoke method
     */
    public void backToMenu(View view) {

        finish();
    }
}
