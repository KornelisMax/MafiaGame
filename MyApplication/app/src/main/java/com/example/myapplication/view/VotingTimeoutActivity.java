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
import com.android.volley.toolbox.StringRequest;
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
    boolean isDay;
    int gameStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_timeout);
        getUpdatedPlayerList(tubeLines);
        Bundle extras = getIntent().getExtras();
        playerName = extras.getString("args"); //hardcoded
        playerName = "Tomas";
        isDay = extras.getBoolean("isDay");
        gameStatus = getGameStatus();
        voteTimer = findViewById(R.id.VoteTimer);

        MyCount counter = new MyCount(5000, 300);
        counter.start();


    }
    public int getGameStatus() {
        String url = String.format("http://10.0.2.2:63439/api/GetGameStatus?votingPlayer=%1$s", playerName);

        final RequestQueue queue = Volley.newRequestQueue(this);

        final StringRequest StringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        gameStatus = Integer.parseInt(response);
                        Log.i("Game Status response", ""+gameStatus);
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

        queue.add(StringRequest);
        return gameStatus;
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
            Intent intent;
            if(gameStatus == 2){
                intent = new Intent(VotingTimeoutActivity.this, EndGameScreen.class);
                intent.putExtra("endgame", "2");
                startActivity(intent);
            }
            if(gameStatus == 1){
                intent = new Intent(VotingTimeoutActivity.this, EndGameScreen.class);
                intent.putExtra("endgame", "1");
                startActivity(intent);
            }
            if(gameStatus != 1 && gameStatus != 2) {
                if (isDay == false) {
                    intent = new Intent(VotingTimeoutActivity.this, GameSessionActivity.class);
                    addExtra(intent);
                }
                if (isDay == true) {
                    intent = new Intent(VotingTimeoutActivity.this, GameSessionNight.class);
                    addExtra(intent);
                }
                //Log.i("HelloListView", playerNicks.get(clickedId));
            }
        }
        public void addExtra(Intent intent){
            intent.putExtra("args", playerName);
            //intent.putExtra("args", playerName); //current player
            intent.putStringArrayListExtra("arg", tubeLines);
            startActivity(intent);
            voteTimer.setText("done!");
        }
        @Override
        public void onTick(long millisUntilFinished) {
            //voteTimer.setText("Left: " + millisUntilFinished / 1000);
        }
    }
}
