package com.mapriddle.mapriddle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by adam on 25.04.15.
 */

/**
 * Position in riddle list
 */
class RiddlePos{
    private String objectID;
    private String name;

    /**
     * Constructor
     * @param o ParseObject representing the riddle
     */
    public RiddlePos(ParseObject o){
        objectID = o.getObjectId();
        name = o.getString("riddleName");
    }

    /**
     * Starts playing this riddle
     * @param context current context
     */
    public void play(Context context){
        Intent i = new Intent(context, RiddleMapPlayActivity.class);
        i.putExtra("RIDDLE_CODE", objectID);
        context.startActivity(i);
    }

    @Override
    public String toString(){
        return name;
    }
}

/**
 * Presents a list of available riddles
 */
public class RiddleMapListActivity extends Activity {
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddle_map_list);
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("RIDDLE");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> riddleList, ParseException e) {
                if (e == null) {
                    final List<RiddlePos> riddlePosList = new LinkedList<RiddlePos>();
                    for (ParseObject o : riddleList)
                        riddlePosList.add(new RiddlePos(o));
                    handler.post(new Runnable() {
                        private RiddlePos[] riddles = riddlePosList.toArray(new RiddlePos[riddlePosList.size()]);

                        @Override
                        public void run() {
                            ArrayAdapter<RiddlePos> adapter = new ArrayAdapter<RiddlePos>(
                                    RiddleMapListActivity.this, R.layout.riddle_list_item, riddles);
                            ListView listView = (ListView) findViewById(R.id.riddle_listview);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView parent, View v, int position, long id) {
                                    riddles[position].play(RiddleMapListActivity.this);
                                }
                            });
                        }
                    });
                } else {
                    Log.e("Riddle list", e.toString());
                }
            }
        });
    }

}
