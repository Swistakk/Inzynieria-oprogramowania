package com.example.wojtek.nurikabe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mapriddle.mapriddle.R;

public class SetParams extends Activity {

    private SeekBar seekBar1, seekBar2;
    private TextView textView1, textView2;

    private int getSide() {
        return 5 + seekBar1.getProgress();
    }
    private int getSeed() {
        return seekBar2.getProgress();
    }
    private String getSideDescription() {
        return "Rozmiar planszy: " + getSide();
    }
    private String getSeedDescription() {
        return "Identyfikator planszy: " + getSeed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nurikabe_set_params);
        initializeVariables();
        textView1.setText(getSideDescription());
        textView2.setText(getSeedDescription());
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                textView1.setText(getSideDescription());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView1.setText(getSideDescription());
            }
        });
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                textView2.setText(getSeedDescription());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView2.setText(getSeedDescription());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nurikabe_set_params, menu);
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

    public void play(View view) {
        Intent intent = new Intent(this, PlayLevel.class);
        Bundle b = new Bundle();
        b.putInt("side", getSide()); //Your id
        b.putInt("seed", getSeed());
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }

    private void initializeVariables() {
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        textView1 = (TextView) findViewById(R.id.textView1);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        textView2 = (TextView) findViewById(R.id.textView2);
    }


}
