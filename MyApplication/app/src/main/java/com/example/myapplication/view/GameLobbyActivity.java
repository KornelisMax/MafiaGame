package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    List<String> passedArg;
    String name;
    ArrayList arrayList = new ArrayList();
    String[] list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_lobby);
        listView = findViewById(R.id.listView);
        final ArrayList<String> tubeLines = new ArrayList<>();

        //playerNicks = getIntent().getExtras().getStringArrayList("arg");
        arrayList.add(0,"test");
        arrayList.add(1,"test2");
        //getPlayersData();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tubeLines);
        listView.setAdapter(adapter);
        getPlayersData(tubeLines, adapter);

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

    }

