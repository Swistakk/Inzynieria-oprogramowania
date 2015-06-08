package com.scenarios;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mapriddle.mapriddle.R;
import com.mapriddle.mapriddle.RiddleMapPlayActivity;
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

    public RiddlePos(String name, String identifier){
        objectID = identifier;
        this.name = name;
    }

    /**
     * Starts playing this riddle
     * @param activity current activity
     */
    public void play(Activity activity){
        Intent i = new Intent();
        i.putExtra("task", objectID);
        i.putExtra("name", name);
        activity.setResult(Activity.RESULT_OK, i);
        activity.finish();
    }

    @Override
    public String toString(){
        return name;
    }
}

/**
 * Presents a list of available riddles
 */
public class TaskListActivity extends Activity {
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
                    riddlePosList.add(new RiddlePos("Nurikabe", "NU"));
                    riddlePosList.add(new RiddlePos("Nonogramy", "NO"));
                    riddlePosList.add(new RiddlePos("Magiczne Kwadraty", "MK"));
                    riddlePosList.add(new RiddlePos("Filomino", "FI"));
                    for (ParseObject o : riddleList)
                        riddlePosList.add(new RiddlePos(o));
                    handler.post(new Runnable() {
                        private RiddlePos[] riddles = riddlePosList.toArray(new RiddlePos[riddlePosList.size()]);

                        @Override
                        public void run() {
                            ArrayAdapter<RiddlePos> adapter = new ArrayAdapter<RiddlePos>(
                                    TaskListActivity.this, R.layout.riddle_list_item, riddles);
                            ListView listView = (ListView) findViewById(R.id.riddle_listview);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView parent, View v, int position, long id) {
                                    riddles[position].play(TaskListActivity.this);
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
