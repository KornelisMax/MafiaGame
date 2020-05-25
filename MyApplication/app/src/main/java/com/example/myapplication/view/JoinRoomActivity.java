package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

public class JoinRoomActivity extends AppCompatActivity {

    Button joinGame;
    Button backToMainMenu;
    EditText nameInput;
    EditText roomIdInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_room);

        nameInput = findViewById(R.id.PlayerNameInput);
        roomIdInput = findViewById(R.id.RoomIdInput);

        joinGame = findViewById(R.id.JoinGame);
        backToMainMenu = findViewById(R.id.ReturnToMenu);

        joinGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(roomIdInput.getText().toString().equals("5589")){
                    joinGameLobby();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong room Id.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void joinGameLobby(){
        Intent intent = new Intent(JoinRoomActivity.this, GameLobbyActivity.class);
        intent.putExtra("GameCreator", "1");
        startActivity(intent);
    }
}
