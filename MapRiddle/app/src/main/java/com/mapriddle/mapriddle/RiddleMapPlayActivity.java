package com.mapriddle.mapriddle;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

import static utils.ToastPresenter.showToast;

/**
 * Presents riddle to the user and allows solving it
 */
public class RiddleMapPlayActivity extends Activity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private final Handler handler = new Handler();
    private String answer = "faddadfafdsaewfs" + Math.random(); //so nobody can guess the "answer" while downloading riddle
    private boolean hasLocation = false;
    private boolean locationReached = false;
    private double latitude, longitude;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddle_map_play);
        final TextView name_textView = (TextView) findViewById(R.id.riddle_name);
        final TextView body_textView = (TextView) findViewById(R.id.riddle_text);

        String objectId = getIntent().getExtras().getString("RIDDLE_CODE");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("RIDDLE");
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            public void done(final ParseObject object, ParseException e) {
                if (e == null)
                    handler.post(new Runnable() {
                        private String name = object.getString("riddleName");
                        private String body = object.getString("riddleText");
                        private String ans = object.getString("riddleAnswer");
                        private boolean hasLoc = object.getBoolean("riddleHasLocation");
                        private double lat = object.getDouble("riddleLatitude");
                        private double lon = object.getDouble("riddleLongitude");

                        @Override
                        public void run() {
                            name_textView.setText(name);
                            body_textView.setText(body);
                            answer = ans;
                            hasLocation = hasLoc;
                            latitude = lat;
                            longitude = lon;
                            if(hasLoc) { //the riddle requires moving to a specified point
                                ((TextView) findViewById(R.id.riddle_location)).setText(R.string.question_obtaining_location);
                                googleApiClient = new GoogleApiClient.Builder(RiddleMapPlayActivity.this)
                                        .addApi(LocationServices.API)
                                        .addConnectionCallbacks(RiddleMapPlayActivity.this)
                                        .addOnConnectionFailedListener(RiddleMapPlayActivity.this)
                                        .build();
                                googleApiClient.connect();

                               requestLocationUpdates();
                            }

                            ((EditText) findViewById(R.id.question_answer_edit)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
                                @Override
                                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                    answerRiddle(null);
                                    return false; //hides keyboard
                                }
                            });
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

    @Override
    protected void onDestroy(){
        if(googleApiClient != null)
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        super.onDestroy();
    }

    private void requestLocationUpdates(){
        if(!googleApiClient.isConnected()) {
            handler.postDelayed(new Runnable(){
                @Override
                public void run(){
                    requestLocationUpdates();
                    Log.e("req", "rqw");
                }
            }, 1000);
            return;
        }

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, mLocationRequest, this);
    }

    private String normalize(CharSequence s){
        return s.toString().toLowerCase().replaceAll("\\s", "");
    }

    /**
     * Gets user's answer and accepts it or not
     * @param view view that invoked this method
     */
    public void answerRiddle(View view){
        if(hasLocation && !locationReached) {
            showToast(R.string.question_not_reached, this);
        }
        else if(normalize(((EditText) findViewById(R.id.question_answer_edit)).getText()).equals(normalize(answer))){
            showToast(R.string.question_correct_answer, this);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("RIDDLE_ANSWERED", true);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
        else{
            showToast(R.string.question_bad_answer, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        float[] d = new float[1];
        Location.distanceBetween(latitude, longitude, location.getLatitude(), location.getLongitude(), d);
        float dist = d[0]; //in meters
        TextView textView = (TextView) findViewById(R.id.riddle_location);
        if(dist > 1000)
            textView.setText(R.string.question_far_away);
        else if(dist > 100)
            textView.setText(R.string.question_rather_far);
        else if(dist > 20)
            textView.setText(R.string.question_closer);
        else{
            textView.setText(R.string.question_reached);
            locationReached = true;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
