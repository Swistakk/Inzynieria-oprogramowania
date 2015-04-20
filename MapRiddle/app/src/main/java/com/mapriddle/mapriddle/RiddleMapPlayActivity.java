package com.mapriddle.mapriddle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class RiddleMapPlayActivity extends Activity {
    private final Handler handler = new Handler();
    private String answer = "faddadfafdsaewfs" + Math.random(); //so nobody can guess the "answer" while downloading riddle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddle_map_play);
        final TextView name_textView = (TextView) findViewById(R.id.riddle_name);
        final TextView body_textView = (TextView) findViewById(R.id.riddle_text);

        String objectId = getIntent().getExtras().getString("RIDDLE_CODE");

        Parse.initialize(this, "pCndNpLBkQMRykaaOclYxYHXUzYZ8iYCySTzvqGF", "6ZgGtMmvbW0nCBivgEXhUsoOET6UNhJWQQW4qPOL");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("RIDDLE");
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            public void done(final ParseObject object, ParseException e) {
                if (e == null)
                    handler.post(new Runnable() {
                        private String name = object.getString("riddleName");
                        private String body = object.getString("riddleText");
                        private String ans = object.getString("riddleAnswer");

                        public void run() {
                            name_textView.setText(name);
                            body_textView.setText(body);
                            answer = ans;
                        }
                    });
                else
                    handler.post(new Runnable() {
                        public void run() {
                            name_textView.setText("Wystąpił błąd przy pobieraniu zagadki lub zagadka nie istnieje");
                        }
                    });
            }
        });


    }

    private String normalize(CharSequence s){
        return s.toString().toLowerCase().replaceAll("\\s", "");
    }

    public void answerRiddle(View view){
        if(normalize(((EditText) findViewById(R.id.question_answer_edit)).getText()).equals(normalize(answer))){
            Intent resultIntent = new Intent();
            resultIntent.putExtra("RIDDLE_ANSWERED", true);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = getResources().getText(R.string.question_bad_answer);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }


}
