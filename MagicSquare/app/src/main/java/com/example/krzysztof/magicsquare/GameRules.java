package com.example.krzysztof.magicsquare;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import utils.Converter;

public class GameRules extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_rules);
        ImageView image = (ImageView) findViewById(R.id.picture);
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) Converter.convertDpToPixel(180 * 2, this),
                (int) Converter.convertDpToPixel(140 * 2, this), true);
        image.setImageBitmap(bitmap);
    }

    public void backToMenu(View view) {

        finish();
    }
}
