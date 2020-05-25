package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GameLobbyActivity extends AppCompatActivity {

    ArrayList<String> playerNicks = new ArrayList<>();
    AsyncTask<?, ?, ?> runningTask;
    ListView listView;

    String gameCreatorArg;
    String name;
    String playerName;
    ArrayList arrayList = new ArrayList();
    String[] list;
    Button startGameButton;
    Button exitGameLobby;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_lobby);

        Bundle extras = getIntent().getExtras();
        gameCreatorArg = extras.getString("GameCreator");

        final ArrayList<String> tubeLines = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tubeLines);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        getPlayersData(tubeLines, adapter);



        exitGameLobby = findViewById(R.id.ExitGameLobby);
        exitGameLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMainMenu();
            }
        });



        startGameButton = findViewById(R.id.StartGameButton);
        if(gameCreatorArg.equals("1")){
            startGameButton.setVisibility(View.INVISIBLE);
        }
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGameLobby(tubeLines);
            }
        });
    }

    public ArrayList<String> getPlayersData(final ArrayList<String> tubeLines, final ArrayAdapter<String> adapter) {
        String url = "http://10.0.2.2:63439/api/GetPlayers";

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
                        adapter.notifyDataSetChanged();
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
        return playerNicks;
    }
    public void showGameLobby(ArrayList<String> tubeLines){
        Intent intent = new Intent(this, GameSessionActivity.class);
        intent.putStringArrayListExtra("arg", tubeLines);
        intent.putExtra("args", playerName);
        startActivity(intent);
    }
    private void showMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

