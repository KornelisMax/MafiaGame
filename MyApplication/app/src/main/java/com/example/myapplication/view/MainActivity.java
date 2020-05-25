package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    private Button instructionButton;
    private Button createGameButton;
    private Button joinRoomButton;
    private Button exitGameButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
        instructionButton = findViewById(R.id.instructionButton);
        instructionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInstructions();
            }
        });

        createGameButton = findViewById(R.id.createGameButton);
        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGameCreationMenu();
            }
        });

        joinRoomButton = findViewById(R.id.joinGameButton);
        joinRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJoinToRoomMenu();

            }
        });

        exitGameButton = findViewById(R.id.LeaveGame);
        exitGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitGame();
            }
        });

    }

    private void exitGame() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    private void showGameCreationMenu() {
        Intent intent = new Intent(this, CreateRoomActivity.class);
        startActivity(intent);
    }
    private void showJoinToRoomMenu() {
        Intent intent = new Intent(this, JoinRoomActivity.class);
        startActivity(intent);
    }

    public void showInstructions(){
        Intent intent = new Intent(this, InstructionActivity.class);
        startActivity(intent);
    }

}
