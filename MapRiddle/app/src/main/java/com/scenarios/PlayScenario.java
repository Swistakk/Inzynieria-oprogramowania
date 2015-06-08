package com.scenarios;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.joanna.nonograms.StartActivity;
import com.example.krzysztof.magicsquare.FirstLevel;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.mapriddle.mapriddle.R;
import com.mapriddle.mapriddle.RiddleMapPlayActivity;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import static utils.ToastPresenter.showToast;

/**
 * Created by adam on 01.06.15.
 */
public class PlayScenario extends Activity {
    private String code = "";
    private String[] tasks = new String[5];
    private Handler handler = new Handler();
    private boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario_play);

        ((EditText) findViewById(R.id.scenario_code)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                start(null);
                return false; //hides keyboard
            }
        });


        if (savedInstanceState != null) {
            // Restore value of members from saved state
            tasks = savedInstanceState.getStringArray("tasks");
            started = savedInstanceState.getBoolean("started");
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putStringArray("tasks", tasks);
        savedInstanceState.putBoolean("started", started);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void start(View view){
        if(started)
            return;
        started = true;

        code = ((EditText)findViewById(R.id.scenario_code)).getText().toString().replaceAll("\\s", "");

        showToast("Pobieranie scenariusza", this);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("SCENARIO");
        query.getInBackground(code, new GetCallback<ParseObject>() {
            public void done(final ParseObject object, ParseException e) {
                if (e == null)
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            showToast("Scenariusz pobrany", PlayScenario.this);
                            for (int i = 0; i < 5; i++)
                                tasks[i] = object.getString("task" + i);
                            onActivityResult(-1, Activity.RESULT_OK, null);
                        }
                    });
                else
                    showToast("Nie ma takiego scenariusza", PlayScenario.this);
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode != -1){
                showToast("Gratulacje!", this);
            }

            Log.e("ZOOOOOOO", ""+requestCode);
            for(int i = 0; i < 5; i++)
                Log.e("ISNULL", ""+i+":"+ (tasks[i] == null));
            while(++requestCode < 5 && tasks[requestCode].equals(""));

            if(requestCode == 5){
                showToast("Wygrałeś/liście!", this);
                finish();
                return;
            }

            if(tasks[requestCode].equals("NO")){
                Intent i = new Intent(this, StartActivity.class);
                i.putExtra("INFO_PLEASE", true);
                startActivityForResult(i, requestCode);
            }
            else if(tasks[requestCode].equals("NU")){
        //        Intent i = new Intent(this, RiddleMapPlayActivity.class);//TODO
      //          i.putExtra("INFO_PLEASE", true);
     //           startActivityForResult(i, requestCode);
            }
            else if(tasks[requestCode].equals("MK")){
                Intent i = new Intent(this, FirstLevel.class);
                i.putExtra("INFO_PLEASE", true);
                startActivityForResult(i, requestCode);
            }
            else if(tasks[requestCode].equals("FI")){
                //        Intent i = new Intent(this, RiddleMapPlayActivity.class);//TODO
                //          i.putExtra("INFO_PLEASE", true);
                //           startActivityForResult(i, requestCode);
            }
            else{
                Intent i = new Intent(this, RiddleMapPlayActivity.class);
                i.putExtra("RIDDLE_CODE", tasks[requestCode]);
                startActivityForResult(i, requestCode);
            }
        }
        else{
            showToast("Looser!", this);
            started = false;
        }
    }
}
