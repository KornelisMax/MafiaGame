package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
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
import java.util.List;

public class CreateRoomActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    String name;

    Button enterData;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    List<String> playerNicks = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        textView = findViewById(R.id.textView2);
        editText = findViewById(R.id.editText);
        enterData = findViewById(R.id.button);
        enterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendAndRequestResponse();
                requestPlayerData();

            }
        });
    }
        private void postDataToServer(){
            mRequestQueue = Volley.newRequestQueue(this);
//            mStringRequest = new StringRequest(Request.Method.POST, url, new )
        }

        //
        private void sendAndRequestResponse() {
            //RequestQueue initialized
            String url = "http://10.0.2.2:63439/api/GetPlayers";

            mRequestQueue = Volley.newRequestQueue(this);

            //String Request initialized
            mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.i("test","Error :" + error.toString());
                }
            });

            mRequestQueue.add(mStringRequest);
        }
        //gets players nicks and then adds them to arraylist
    public void requestPlayerData() {
        String url = "http://10.0.2.2:63439/api/GetPlayers";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    if (response != null) {
                        for(int i = 0; i < response.length(); i++){
                            JSONObject obj = response.getJSONObject(i);
                            name = obj.getString("name");
                            if(!playerNicks.contains(name)) {
                                playerNicks.add(name);
                            }
                        }
                    }
                }catch(Exception e){
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



}

