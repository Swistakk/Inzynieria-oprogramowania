package com.example.krzysztof.magicsquare;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mapriddle.mapriddle.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import utils.Converter;

public class BestGamers extends Activity {

    Handler handler = new Handler();

    private class Result implements Comparator<Result> {

        private String name;
        private String result;

        Result(String name, String result) {
            this.name = name;
            this.result = result;
        }

        public String getName() {
            return name;
        }

        public String getResult() {
            return result;
        }


        @Override
        public int compare(Result lhs, Result rhs) {
            return lhs.result.compareTo(rhs.result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_gamers);
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("SQUARESRESULT");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> ResultList, ParseException e) {
                if (e == null) {
                    final List<Result> results = new LinkedList<Result>();
                    for (ParseObject o : ResultList) {
                        results.add(new Result((String) o.get("GamerName"), (String) o.get("GamerResult")));
                    }
                    Collections.sort(results, new Result("", ""));
                    int i = 1;
                    setContentView(R.layout.activity_best_gamers);
                    RelativeLayout layout = (RelativeLayout) findViewById(R.id.scores_layout);
                    TextView textView;
                    for (Result res : results) {
                        Log.e(res.getName(), res.getResult());
                        textView = new TextView(getApplicationContext());
                        layout.addView(textView);
                        textView.setTextSize(20);
                        textView.setTextColor(Color.parseColor("#000000"));
                        textView.setText(i + ". " +
                                res.getName() + "    " + res.getResult());
                        textView.setX(Converter.convertDpToPixel(30, getApplicationContext()));
                        textView.setY(Converter.convertDpToPixel(i * 40 + 30, getApplicationContext()));
                        i++;
                        if (i > 10) {
                            break;
                        }
                    }
                } else {
                    Log.e("Result list", e.toString());
                }
            }
        });
    }
}
