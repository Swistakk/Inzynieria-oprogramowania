package com.mapriddle.mapriddle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;

import static utils.ToastPresenter.showToast;


public class CreatePrize extends Activity {
    private static int RESULT_LOAD_IMAGE = 1;
    private final Handler handler = new Handler();
    private ParseFile file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_prize);

        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        //((ScrollView) findViewById(R.id.scroll)).getLayoutParams().width = width;
        //((LinearLayout) findViewById(R.id.prize)).getLayoutParams().width = width;

        Button buttonLoadImage = (Button) findViewById(R.id.button_load_picture);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.image_view);
            Bitmap bmp = BitmapFactory.decodeFile(picturePath);
            imageView.setImageBitmap(bmp);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            Log.d("file", stream.toString());
            file = new ParseFile("image.jpeg", byteArray);

            ((Button) findViewById(R.id.prize_save)).setVisibility(View.INVISIBLE);
            ((Button) findViewById(R.id.button_load_picture)).setVisibility(View.INVISIBLE);

            file.saveInBackground(new SaveCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        //Log.e("zzz", "The image-object private final Handler handler = new Handler();id is: " + id);
                        handler.post(new Runnable() {
                            public void run() {
                                showToast("Obraz załadowany!", CreatePrize.this);
                                ((Button) findViewById(R.id.prize_save)).setVisibility(View.VISIBLE);
                                ((Button) findViewById(R.id.button_load_picture)).setVisibility(View.INVISIBLE);
                            }
                        });
                    } else {
                        Log.e("zzz", "Nie zapisano obiektu");
                        Log.e("zzz", e.toString());
                        handler.post(new Runnable() {
                            public void run() {
                                CreatePrize.this.finish();
                            }
                        });
                    }
                }
            });
            Log.d("zapisanie pliku", "udane");

        }


    }

    /**
     * Validates and saves the prize if possible
     * @param view view that invoked the method
     */
    public synchronized void savePrize(View view){

        CharSequence prizeName = ((EditText) findViewById(R.id.prize_title_edit)).getText();
        CharSequence prizeText = ((EditText) findViewById(R.id.prize_body_edit)).getText();

        if(prizeName.length() == 0 || prizeText.length() == 0 || file == null){
            showToast(R.string.question_save_toast_bad_data, getApplicationContext());
            return;
        }

        Context context = getApplicationContext();
        CharSequence text = getResources().getText(R.string.prize_saving);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        final ParseObject prize = new ParseObject("PRIZE");
        prize.put("prizeName", prizeName.toString());
        prize.put("prizeText", prizeText.toString());
        prize.put("prizeImage", file);
        prize.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    final String id = prize.getObjectId();
                    Log.e("zzz", "The prize-object private final Handler handler = new Handler();id is: " + id);
                    handler.post(new Runnable() {
                        public void run() {
                            showToast("Zrobione! Zanotuj kod nagrody: " + id, CreatePrize.this);
                            ((TextView) findViewById(R.id.prize_id)).setText("Zanotuj kod nagrody: " + id +
                                    ". Możesz go użyć tworząc scenariusz gry.");
                            ((Button) findViewById(R.id.prize_button)).setVisibility(View.VISIBLE);
                            ((LinearLayout) findViewById(R.id.prize)).setVisibility(View.INVISIBLE);
                        }
                    });
                } else {
                    Log.e("zzz", "Nie zapisano obiektu");
                    Log.e("zzz", e.toString());
                    handler.post(new Runnable() {
                        public void run() {
                            CreatePrize.this.finish();
                        }
                    });
                }
            }
        });
    }

    public void quit(View view) {
        finish();
        System.exit(0);
    }
}

