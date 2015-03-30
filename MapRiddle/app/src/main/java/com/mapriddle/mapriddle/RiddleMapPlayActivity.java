package com.mapriddle.mapriddle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;


public class RiddleMapPlayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddle_map_play);
        final TextView textView = (TextView) findViewById(R.id.question);

        String objectId = getIntent().getExtras().getString("RIDDLE_CODE");

        // Enable Local Datastore.
//        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "pCndNpLBkQMRykaaOclYxYHXUzYZ8iYCySTzvqGF", "6ZgGtMmvbW0nCBivgEXhUsoOET6UNhJWQQW4qPOL");


      //  ParseObject testObject = new ParseObject("TestObject");
      //  testObject.put("foo", "zar");
      //  testObject.saveInBackground();

        //String s = testObject.getString("foo");


  /*      final ParseObject gameScore = new ParseObject("RIDDLE");
        gameScore.put("riddleName", "Zagadka Puchatka");
        gameScore.put("riddleText", "Na wzgórzu lub w drzwiach");
        gameScore.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    String id = gameScore.getObjectId();
                    Log.e("zzz", "The object id is: " + id);
                } else {
                    Log.e("zzz", "Nie zapisano obiektu");
                    Log.e("zzz", e.toString());
                }
            }
        });

//*/
        ParseQuery<ParseObject> query = ParseQuery.getQuery("RIDDLE");
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    textView.setText(object.getString("riddleName"));
                } else {
                    textView.setText("Wystąpił błąd");
                }
            }
        });


    }


}
