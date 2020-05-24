package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class VotingTimeoutActivity extends AppCompatActivity {
    String name;
    final ArrayList<String> tubeLines = new ArrayList<>();
    long startTime = 0;
    int clickedId;
    Handler timerHandler = new Handler();
    String playerName;
    TextView voteTimer;
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            voteTimer.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_timeout);
        getUpdatedPlayerList(tubeLines);
        Bundle extras = getIntent().getExtras();
        playerName = extras.getString("args"); //hardcoded
        playerName = "Tomas";
        voteTimer = findViewById(R.id.VoteTimer);
        MyCount counter = new MyCount(10000, 1000);
        counter.start();


    }
    public void getUpdatedPlayerList(final ArrayList<String> tubeLines) {
        String url = "http://10.0.2.2:63439/api/GetAlivePlayers";

        final RequestQueue queue = Volley.newRequestQueue(this);

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response != null) {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);
                            name = obj.getString("name");

                            if (name != null) {
                                tubeLines.add(name);
                            }
                        }
                        //adapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG", error.toString());
            }
        });

        queue.add(jsonArrayRequest);
    }

    public class MyCount extends CountDownTimer {

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            //playerName = playerNicks.get(clickedId);
            Intent intent = new Intent(VotingTimeoutActivity.this, GameSessionActivity.class);
            //Log.i("HelloListView", playerNicks.get(clickedId));
            intent.putExtra("args", playerName);
            //intent.putExtra("args", playerName); //current player
            intent.putStringArrayListExtra("arg", tubeLines);
            startActivity(intent);
            voteTimer.setText("done!");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            voteTimer.setText("Left: " + millisUntilFinished / 1000);
        }
    }
}
