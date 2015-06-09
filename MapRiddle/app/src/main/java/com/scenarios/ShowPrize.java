package com.scenarios;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.mapriddle.mapriddle.R;
import com.parse.GetCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ShowPrize extends ActionBarActivity {
    private final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_prize);
        String prizeId;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                prizeId= null;
            } else {
                prizeId= extras.getString("PRIZE_ID");
                completeView(prizeId);
            }
        } else {
            prizeId = (String) savedInstanceState.getSerializable("PRIZE_ID");
            completeView(prizeId);
        }
    }

    private void completeView(String prizeId) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("PRIZE");
        Log.d("ok1", "dziala");
        query.getInBackground(prizeId, new GetCallback<ParseObject>() {

            public void done(final ParseObject object, com.parse.ParseException e) {
                Log.d("ok2","dziala");
                if (e == null)
                    handler.post(new Runnable() {
                        private String name = object.getString("prizeName");
                        private String text = object.getString("prizeText");
                        private ParseFile image = (ParseFile) object.getParseFile("prizeImage");

                        @Override
                        public void run() {
                            ((TextView) findViewById(R.id.prize_title)).setText(name);
                            ((TextView) findViewById(R.id.prize_body)).setText(text);
                            byte[] image_bytes;
                            try {
                                image_bytes = image.getData();
                                ((ImageView) findViewById(R.id.image_view)).setImageBitmap(
                                        BitmapFactory.decodeByteArray(image_bytes, 0, image_bytes.length)
                                );
                            } catch (com.parse.ParseException e1) {
                                Log.d("parse", "exception");
                            }
                        }
                    });
                else
                    handler.post(new Runnable() {
                        public void run() {
                            ((TextView) findViewById(R.id.prize_body)).setText("Wystąpił błąd przy " +
                                    "pobieraniu nagrody lub nagroda nie istnieje");
                        }
                    });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_prize, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
