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
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String url_logout = "http://134.209.250.135:8080/user/logout";
    Button buttonFriends,buttonDebts, buttonProfile, buttonAddFriend, logout;
    FloatingActionButton fab;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        buttonFriends = (Button) findViewById(R.id.button_main_friends);
        buttonDebts = (Button) findViewById(R.id.button_main_debts);
        buttonProfile = (Button) findViewById(R.id.button_main_myprofile);
        buttonAddFriend = (Button) findViewById(R.id.button_main_add_friend);
        fab = (FloatingActionButton) findViewById(R.id.add_fab);
        logout = (Button) findViewById(R.id.button_main_logout);


        buttonFriends.setOnClickListener(new View.OnClickListener() {
            //when user press registration button, the  registration  page is redirected to login page
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FriendsProfiles.class);
                startActivity(intent);
            }
        });
        buttonDebts.setOnClickListener(new View.OnClickListener() {
            //when user press registration button, the  registration  page is redirected to login page
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DebtsShow.class);
                startActivity(intent);
            }
        });
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            //when user press registration button, the  registration  page is redirected to login page
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
            }
        });
            fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectDebtType.class);
                startActivity(intent);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //      .setAction("Action", null).show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {

            //when user press log out button, the  profile page is redirected to login page
            @Override
            public void onClick(View v) {
                StringRequest jsonObjectRequest = new StringRequest
                        (Request.Method.DELETE, url_logout, new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        }, new Response.ErrorListener() {

                            //error message
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

                // Access the RequestQueue through your singleton class.
                RequestGate.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
            }


        });

        buttonAddFriend.setOnClickListener(new View.OnClickListener() {
            //when user press registration button, the  registration  page is redirected to login page
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddingFriend.class);
                startActivity(intent);
            }
        });
    }



}
