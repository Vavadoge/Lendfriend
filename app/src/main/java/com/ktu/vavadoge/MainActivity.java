package com.ktu.vavadoge;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    String url_logout = "http://134.209.250.135:8080/user/logout";
    Button buttonFriends,buttonDebts, buttonProfile, buttonAddFriend, logout;
    FloatingActionButton fab;
    ListView listView;
    String url_debts_confirmed_from_us = "http://134.209.250.135:8080/user/debt?status=accepted";
    ArrayList<String> norifications = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        buttonFriends = (Button) findViewById(R.id.button_main_friends);
        buttonDebts = (Button) findViewById(R.id.button_main_debts);
        buttonProfile = (Button) findViewById(R.id.button_main_myprofile);
        buttonAddFriend = (Button) findViewById(R.id.button_main_add_friend);
        fab = (FloatingActionButton) findViewById(R.id.add_fab);
        logout = (Button) findViewById(R.id.button_main_logout);
        listView = (ListView)findViewById(R.id.listViewNotifications);


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

        JsonArrayRequest jsn1 = new JsonArrayRequest(Request.Method.GET, url_debts_confirmed_from_us, null, new Response.Listener<JSONArray>() {
            //assigns json object values to string and then to appropriate text box
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    JSONObject jresponse = null;
                    try {
                        jresponse = response.getJSONObject(i);
                        int id = jresponse.getInt("id");
                        String user = jresponse.getString("debtor");
                        String time_value = jresponse.getString("created_at");
                        time_value = time_value.substring(0,10);
                        String information = jresponse.getString("debt");
                        String[] str = information.split(":");
                        information = str[2];
                        str = information.split("\"");
                        information = str[1];
                        long days=0;
                        //SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
                        //Date firstDate = sdf.parse("06/24/2017");
                        //Date secondDate = sdf.parse("06/30/2017");
                        //long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
                        //long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                        //assertEquals(6, diff);
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                       // String inputString1 = "23 01 1997";
                        //String inputString2 = "27 04 1997";

                        LocalDate date1 = LocalDate.now();
                        LocalDate date2 = LocalDate.parse(time_value, dtf);

                        System.out.println("-----------------" + date1 + "-------------" + date2);
                        //days = Duration.between(date1, date2).toDays();
                        //long first = date1.getTime();
                        //days = date1-date2;
                        days = ChronoUnit.DAYS.between(date2, date1);
                        System.out.println ("Days: " + days);


                        String text = "You haven't returned the debt " + information + " to " + user + " for " +  days + " days";
                        System.out.println(text);
                        if (days >= 0)
                            norifications.add(text);
                        //String nickname = jresponse.getString("friend");
                        //String first = jresponse.getString("owner");
                        //String status = jresponse.getString("accepted");
                        //friends.add(nickname);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if (norifications.size() > 0) {


                    String[] friendList1 = new String[norifications.size()];
                    norifications.toArray(friendList1);
                    ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_viewlist_notification, R.id.textboxViewDebtsFriend, friendList1);

                    listView.setAdapter(arrayAdapter);
                    //listView.setOnItemClickListener(listClick);
                }
                else{
                    norifications.add("You have no notifications");
                    String[] friendList1 = new String[norifications.size()];
                    norifications.toArray(friendList1);
                    ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_viewlist_notification, R.id.textboxViewDebtsFriend, friendList1);

                    listView.setAdapter(arrayAdapter);
                }
            }
        }, new Response.ErrorListener() {
            //error message
            @Override
            public void onErrorResponse(VolleyError error) {
                if (norifications.size() == 0)
                {
                    norifications.add("You have no notifications");
                    String[] friendList1 = new String[norifications.size()];
                    norifications.toArray(friendList1);
                    ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_viewlist_notification, R.id.textboxViewDebtsFriend, friendList1);

                    listView.setAdapter(arrayAdapter);
                }
            }

        });
        RequestGate.getInstance(getApplicationContext()).addToRequestQueue(jsn1);
    }

}
