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
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameSessionNight extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    TextView timerTextView;
    long startTime = 0;
    int clickedId;
    ArrayList<String> playerNicks;
    String playerName;
    boolean isDay;

    Handler timerHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_session_night);
        listView = findViewById(R.id.PlayerList);
        timerTextView = findViewById(R.id.TimerTextView);
        MyCount counter = new MyCount(10000, 1000);
        counter.start();


        Bundle extras = getIntent().getExtras();
        playerNicks = extras.getStringArrayList("arg");
        playerName = extras.getString("args");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playerNicks);
        listView.setAdapter(adapter);
        //listView.setOnItemClickListener(this);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("HelloListView", playerName + "You clicked Item: " + id + " at position:" + position);
        clickedId = (int)id;
    }

    public class MyCount extends CountDownTimer {

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            //playerName = playerNicks.get(clickedId);
            HttpPOSTRequestWithParameters();
            isDay = false;
            Intent intent = new Intent(GameSessionNight.this, VotingTimeoutActivity.class);
            Log.i("HelloListView", playerNicks.get(clickedId));
            intent.putExtra("arg", playerNicks.get(clickedId));
            intent.putExtra("isDay", isDay);
            intent.putExtra("args", playerName); //current player
            intent.putStringArrayListExtra("arg1", playerNicks);
            startActivity(intent);
            timerTextView.setText("done!");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            timerTextView.setText("Left: " + millisUntilFinished / 1000);
        }
    }


    public void HttpPOSTRequestWithParameters() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:63439/api/Vote";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR", "error => " + error.toString());
                    }
                }
        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                //..add other headers
                return params;
            }


            // this is the relevant method
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("votedPlayer", playerNicks.get(clickedId));
                params.put("votingPlayer", "Tomas"); //hardcoded
                Log.i("HelloListView", playerNicks.get(clickedId));
                // volley will escape this for you
                //playerName = playerNameInput.getText().toString();
                //params.put("randomFieldFilledWithAwkwardCharacters", "{{%stuffToBe Escaped/");


                return params;
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError response) {
                try {

                    String json = new String(response.networkResponse.data, HttpHeaderParser.parseCharset(response.networkResponse.headers));
                    Log.e("tag", "reponse error = " + json);
                } catch (Exception e) {
                }
                return super.parseNetworkError(response);
            }


        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
        //showGameLobby();
    }


}
