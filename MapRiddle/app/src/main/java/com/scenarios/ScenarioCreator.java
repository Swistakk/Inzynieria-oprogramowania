package com.scenarios;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mapriddle.mapriddle.R;
import com.mapriddle.mapriddle.RiddleMapListActivity;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import static utils.ToastPresenter.showToast;

/**
 * Created by adam on 01.06.15.
 */
public class ScenarioCreator extends Activity {
    private String[] tasks = new String[5];
    private boolean savingStarted = false;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario_creator);
        for(int i = 0; i < 5; i++)
            tasks[i] = "";
    }

    public void taskClicked(View view) {
        for(int i = 0; i < 5; i++)
            if(view.getResources().getResourceName(view.getId()).endsWith(Integer.toString(i))){
                askForTask(i);
                break;
            }
    }

    private void askForTask(int which){
        Intent i = new Intent(this, TaskListActivity.class);
        startActivityForResult(i, which);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            tasks[requestCode] = data.getStringExtra("task");
            ((TextView) findViewById(getResources().getIdentifier("task"+requestCode, "id", getPackageName()))).setText(data.getStringExtra("name"));
            Log.e("zyr", tasks[requestCode]);
        }
    }

    public synchronized void saveScenario(View view) {
        if(savingStarted)
            return;
        savingStarted = true;

        final ParseObject s = new ParseObject("SCENARIO");
        for(int i = 0; i < 5; i++)
            s.put("task"+i, tasks[i]);
        showToast("Zapisywanie...", this);
        s.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    final String id = s.getObjectId();
                    handler.post(new Runnable() {
                        public void run() {
                            showToast("Zrobione! Zanotuj kod scenariusza: " + id, ScenarioCreator.this);
                            ((TextView) findViewById(R.id.scenario_id)).setText("Zanotuj kod scenariusza: " + id);
                        }
                    });
                } else {
                    Log.e("zzz", "Nie zapisano obiektu");
                    Log.e("zzz", e.toString());
                    handler.post(new Runnable() {
                        public void run() {
                            ScenarioCreator.this.finish();
                        }
                    });
                }
            }
        });
    }
}
