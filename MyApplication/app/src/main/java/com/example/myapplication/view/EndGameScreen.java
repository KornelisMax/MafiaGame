package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

public class EndGameScreen extends AppCompatActivity {
    int statusFlag;
    String statusFlagS;
    TextView whoWon;
    String winner;
    String name;
    String isAlive;
    String role;
    ListView listView;
    Button exitToMainMenuButton;
    final ArrayList<String> tubeLines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game_screen);
        Bundle extras = getIntent().getExtras();
        statusFlagS = extras.getString("endgame");
        whoWon = findViewById(R.id.WhoWon);
        exitToMainMenuButton = findViewById(R.id.ExitToMainMenu);
        exitToMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMainMenu();
            }
        });
        statusFlag = Integer.parseInt(statusFlagS);
        if(statusFlag == 1){
            Log.i("winner 1", " " + statusFlag);
            winner = "Mafia";
        }
        if(statusFlag == 2){
            Log.i("winner 2", " " + statusFlag);
            winner = "Civilian";
        }
        listView = findViewById(R.id.ListView);
        whoWon.setText("Winners: " + winner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tubeLines);
        listView.setAdapter(adapter);
        getUpdatedPlayerList(adapter);
    }

    public void getUpdatedPlayerList(final ArrayAdapter<String> adapter) {
        String url = "http://10.0.2.2:63439/api/GetPlayersEndGame";

        final RequestQueue queue = Volley.newRequestQueue(this);

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response != null) {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);
                            name = obj.getString("name");
                            isAlive = obj.getString("isAlive");
                            role = obj.getString("role");
                            if (name != null) {
                                if (role.equals(winner)) {
                                    Log.i("role equals winner", role + " " + winner);
                                    tubeLines.add("Winner: " +name + ", " +"is he/she alive: " + isAlive+ ",  he/she was: " + role);
                                }
                                else {
                                    Log.i("role equals looser", role + " " + winner);
                                    tubeLines.add("Looser: " +name + ", " +"is he/she alive: " + isAlive+ ",  he/she was: " + role);
                                }
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
    }
    private void showMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
