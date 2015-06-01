package com.mapriddle.mapriddle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import static utils.ToastPresenter.showToast;

/**
 * Riddle creator
 */
public class RiddleMapCreateActivity extends Activity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private final Handler handler = new Handler();
    private boolean savingStarted = false;
    private GoogleApiClient googleApiClient;
    private boolean waitingForLocation = false;
    private Location lastLocation = null;
    private TextView locationState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddle_map_create);
        locationState = (TextView) findViewById(R.id.question_location_state);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        googleApiClient.connect();
    }

    /**
     * Validates and saves the riddle if possible
     * @param view view that invoked the method
     */
    public synchronized void saveRiddle(View view){
        if (savingStarted)
            return;
        if(waitingForLocation) {
            showToast(R.string.question_obtaining_location, getApplicationContext());
            return;
        }
        savingStarted = true;

        CharSequence riddleName = ((EditText) findViewById(R.id.question_edit)).getText();
        CharSequence riddleText = ((EditText) findViewById(R.id.question_body_edit)).getText();
        CharSequence riddleAnswer = ((EditText) findViewById(R.id.question_answer_edit)).getText();
        if(riddleName.length() == 0 || riddleText.length() == 0 || riddleAnswer.length() == 0){
            showToast(R.string.question_save_toast_bad_data, getApplicationContext());
            return;
        }

        Context context = getApplicationContext();
        CharSequence text = getResources().getText(R.string.question_saving);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        final ParseObject riddle = new ParseObject("RIDDLE");
        riddle.put("riddleName", riddleName.toString());
        riddle.put("riddleText", riddleText.toString());
        riddle.put("riddleAnswer", riddleAnswer.toString());
        if(lastLocation != null) {
            riddle.put("riddleHasLocation", true);
            riddle.put("riddleLatitude", lastLocation.getLatitude());
            riddle.put("riddleLongitude", lastLocation.getLongitude());
        }
        riddle.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    final String id = riddle.getObjectId();
                    Log.e("zzz", "The object id is: " + id);
                    handler.post(new Runnable() {
                        public void run() {
                            Context context = getApplicationContext();
                            CharSequence text = getResources().getText(R.string.question_saved);
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("RIDDLE_ID", id);
                            RiddleMapCreateActivity.this.setResult(Activity.RESULT_OK, resultIntent);
                            RiddleMapCreateActivity.this.finish();
                        }
                    });
                } else {
                    Log.e("zzz", "Nie zapisano obiektu");
                    Log.e("zzz", e.toString());
                    handler.post(new Runnable() {
                        public void run() {
                            RiddleMapCreateActivity.this.finish();
                        }
                    });
                }
            }
        });
    }

    /**
     * Gets user location if possible
     * @param view view that invoked the method
     */
    public synchronized void setLocation(View view){
        if(savingStarted)
            return;
        if(waitingForLocation && view != null) //called by user second time
            return;
        if(!waitingForLocation && view == null) { //called by handler, but user-cancelled
            locationState.setText(getResources().getString(R.string.question_no_location));
            return;
        }
        locationState.setText(getResources().getString(R.string.question_obtaining_location));
        waitingForLocation = true;

        if(!googleApiClient.isConnected()) {
            handler.postDelayed(new Runnable(){
                @Override
                public void run(){
                    setLocation(null);
                    Log.e("fds", "sdf");
                }
            }, 1000);
            return;
        }

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, mLocationRequest, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        if(savingStarted)
            return;
        locationState.setText(locationState.getText()+".");
        if((location.hasAccuracy() && location.getAccuracy() <= 10)
            || (lastLocation != null && location.distanceTo(lastLocation) <= 10)){
            waitingForLocation = false;
            locationState.setText(getResources().getString(R.string.question_location_obtained));
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
        lastLocation = location;
    }

    /**
     * Clears location or/and stops getting current location
     * @param view view that invoked the method
     */
    public void clearLocation(View view){
        if(savingStarted)
            return;
        waitingForLocation = false;
        lastLocation = null;
        locationState.setText(getResources().getString(R.string.question_no_location));
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
