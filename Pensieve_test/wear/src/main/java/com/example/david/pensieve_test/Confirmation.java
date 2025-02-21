package com.example.david.pensieve_test;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.CircledImageView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by david on 4/19/16.
 */
public class Confirmation extends Activity{
    private final String TAG = "@>@>@>";

    private String todoTask = "";
    private Boolean set = Boolean.FALSE;
    private String taskId = "";
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.confirmation);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Log.d("CONFIRMATION", "On confirmation notification screen");

        if (extras != null) {
            todoTask = extras.getString("/task_item");
            String[] lst = todoTask.split("@@@");

            TextView task_item = (TextView) findViewById(R.id.task_item);
            task_item.setText(lst[0]);
            taskId = lst[3];
            Log.d("CONFIRMATION", "TaskId = " + taskId);
            Log.d("CONFIRMATION", "Explicit taskId = " + extras.getString("taskId"));
            set = Boolean.TRUE;
        }

        CircledImageView parag = (CircledImageView) findViewById(R.id.reminder);
        parag.setOnTouchListener(new OnSwipeTouchListener(getBaseContext()) {
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                //Toast.makeText(Confirmation.this, "right", Toast.LENGTH_SHORT).show();

                if (set) {
                    Intent intent = new Intent(Confirmation.this, cantRemember.class);

                    if (todoTask != "") {
                        intent.putExtra("/dont_remember", todoTask);
                        intent.putExtra("taskId", taskId);
                        Log.d(TAG, "dont remember todotask " + todoTask);
                    } else {
                        intent.putExtra("/dont_remember", taskId);
                        intent.putExtra("taskId", taskId);
                        Log.d(TAG, "dont remember todotask nothing");
                    }
                    startActivity(intent);
                }
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                //Toast.makeText(Confirmation.this, "left", Toast.LENGTH_SHORT).show();

                if (set) {
                    Intent intent = new Intent(Confirmation.this, cantRemember.class);
                    if (todoTask != "") {
                        intent.putExtra("/dont_remember", todoTask);
                        intent.putExtra("taskId", taskId);
                        Log.d(TAG, "dont remember todotask " + todoTask);
                    } else {
                        intent.putExtra("/dont_remember", taskId);
                        intent.putExtra("taskId", taskId);
                        Log.d(TAG, "dont remember todotask nothing");
                    }
                    startActivity(intent);
                }
            }
        });

    }


}
