package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.myapplication.view.GameLobbyActivity;

public class CreatingRoomTimeout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_room_timeout);
        MyCount counter = new MyCount(10000, 300);
        counter.start();
    }
    public void showGameLobby(){
        Intent intent = new Intent(this, GameLobbyActivity.class);
        startActivity(intent);
    }

    public class MyCount extends CountDownTimer {

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            showGameLobby();
            //Log.i("HelloListView", playerNicks.get(clickedId));
        }
    }

}
