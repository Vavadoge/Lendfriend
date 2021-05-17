package com.ktu.vavadoge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AddingFriend extends AppCompatActivity {
    Button buttonAddFriend;
    String url_add_friend = "http://134.209.250.135:8080/user/friend-request?self=true";
    TextView message;
    EditText input_otherUser;
    private static String otherName;
    FloatingActionButton fab;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfriend);
        input_otherUser = (EditText) findViewById(R.id.editTextAddFriend);
        message =(TextView)findViewById(R.id.textViewifFriendAdded);
        buttonAddFriend = (Button) findViewById(R.id.buttonAddFriendPage);
        fab = (FloatingActionButton) findViewById(R.id.home_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
              //  finish();
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //      .setAction("Action", null).show();
            }
        });

        buttonAddFriend.setOnClickListener(new View.OnClickListener() {

            //when user press log out button, the  profile page is redirected to login page
            @Override
            public void onClick(View v) {
                otherName = input_otherUser.getText().toString();
                // testing = (TextView) findViewById(R.id.textView11);

                JSONObject test = new JSONObject();
                try {
                    test.put("username", otherName);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, url_add_friend, test, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                message.setText("Friend added");

                            }
                        }, new Response.ErrorListener() {

                            //error message
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                //error.printStackTrace();
                                message.setText(error.networkResponse==null?"Friend added":"User can not be added");//setText("User can not be added");



                            }
                        });

                // Access the RequestQueue through your singleton class.
                RequestGate.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
            }
        });

    }

}
