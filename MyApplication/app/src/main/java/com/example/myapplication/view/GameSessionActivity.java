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

import com.example.myapplication.R;

import java.util.ArrayList;

public class GameSessionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    TextView timerTextView;
    long startTime = 0;
    int clickedId;
    ArrayList<String> playerNicks;
    String playerName;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerTextView.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_session);
        listView = findViewById(R.id.PlayerList);
        timerTextView = findViewById(R.id.TimerTextView);
        MyCount counter = new MyCount(10000, 1000, this);
        counter.start();


        Bundle extras = getIntent().getExtras();
        playerNicks = extras.getStringArrayList("arg");
        playerName = extras.getString("args");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playerNicks);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        clickedId = (int)id;
    }

    public class MyCount extends CountDownTimer {

        public MyCount(long millisInFuture, long countDownInterval, GameSessionActivity gameSessionActivity) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {

            Intent intent = new Intent(GameSessionActivity.this, GameLobbyActivity.class);
            Log.i("HelloListView", playerNicks.get(clickedId));
            intent.putExtra("arg", playerNicks.get(clickedId));
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

}
