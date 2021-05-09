package com.ktu.vavadoge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class FriendsProfiles extends AppCompatActivity {

    Button button, buttonDebt;
    ListView listView,listViewNotFriends;
    String url_add_friend = "http://134.209.250.135:8080/user/friend";

    TextView message;
    ArrayList<String> arrayListFriends = new ArrayList<>();
    List<String> listFriends = new LinkedList<>();
    ArrayList<String> arrayListNotFriendsSent = new ArrayList<>();
    List<String> listNotFriendsSent = new LinkedList<>();
    ArrayList<String> arrayListNotFriendsReceived = new ArrayList<>();
    List<String> listNotFriendsReceived = new LinkedList<>();

    EditText input_otherUser;

    String otherName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        button = (Button) findViewById(R.id.buttonFriend);
        //button = (Button) findViewById(R.id.friendsList);
        listView= (ListView)findViewById(R.id.listView);
        listViewNotFriends =(ListView)findViewById(R.id.appendingFriends);
        input_otherUser = (EditText) findViewById(R.id.friendsName);
             buttonDebt = (Button) findViewById(R.id.buttonDebt);


        buttonDebt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Debts.class);
                startActivity(intent);
                Intent intent1 = new Intent(FriendsProfiles.this, Profile.class);
                startActivity(intent1);

            }
        });
        //  message.setText("null");
        JsonArrayRequest jsn = new JsonArrayRequest(Request.Method.GET, url_add_friend, null, new Response.Listener<JSONArray>() {
            //assigns json object values to string and then to appropriate text box
            @Override
            public void onResponse(JSONArray response) {

                String xx = "";
                xx = response.toString(); //("username");
               // String countryList[];
                for(int i = 0; i < response.length(); i++){
                    JSONObject jresponse = null;
                    try {
                            jresponse = response.getJSONObject(i);

                            String nickname = jresponse.getString("second_user");
                            String first = jresponse.getString("first_user");
                            String  status = jresponse.getString("status");

                            String username = UserProfile.getName();
                            if ( status=="false" )
                            {
                                if (first.equals(username))
                                {
                                    arrayListNotFriendsSent.add(nickname);
                                    listNotFriendsSent.add(nickname);
                                }
                                else
                                {
                                    arrayListNotFriendsReceived.add(first);
                                    listNotFriendsReceived.add(first);
                                }
                            }
                            else
                            {
                                arrayListFriends.add(nickname);
                                listFriends.add(nickname);
                            }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
              //  String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
                if(listFriends.size()>0)
                {
                    String countryList[] = new String[listFriends.size()];
                  listFriends.toArray(countryList);


                 ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.activity_viewlist, R.id.tekstukas, countryList);
                // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_friends, R.id.textView, arrayList);
                listView.setAdapter(arrayAdapter);
                }
                if(listNotFriendsReceived.size()>0)
                {
                    String listA[] = new String[listNotFriendsReceived.size()];
                    listNotFriendsReceived.toArray(listA);


                    ArrayAdapter arrayAdapter1 = new ArrayAdapter(getApplicationContext(), R.layout.activity_viewlist_notfriends, R.id.notFriendsView, listA);
                    // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_friends, R.id.textView, arrayList);
                    listViewNotFriends.setAdapter(arrayAdapter1);
                }
            }

        }, new Response.ErrorListener() {
            //error message
            @Override
            public void onErrorResponse(VolleyError error) {

                try {
                    if(error.networkResponse != null)
                    {
                        if(error.networkResponse.data != null)
                        {
                            message.setText("Error: " + new JSONObject(new String(error.networkResponse.data)));
                        }
                        else
                        {
                            message.setText(String.valueOf(error.networkResponse.statusCode));
                        }
                    }
                    else
                    {
                        message.setText("null");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
        RequestGate.getInstance(getApplicationContext()).addToRequestQueue(jsn);


        // String list[] = (String[]) arrayList.toArray();
        // for(int i=0; i<arrayList.)
     //   int index = list.size();

        //   ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.activity_viewlist, R.id.tekstukas, list);
        // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_friends, R.id.textView, arrayList);

        //assign adapter to listview
        //    listView.setAdapter(arrayAdapter);







    }
}

 /*
        button.setOnClickListener(new View.OnClickListener() {

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
                        (Request.Method.POST, url_add_friend, test, new Response.Listener<JSONObject>()  {

                            @Override
                            public void onResponse(JSONObject response) {
                                //testing.setText("krabas");
                                //   message.setText("Response: " + response);
                                //     Log.i("tag", "test");
                                //  kazkas = testing.getText().toString();


                                //     Intent intent = new Intent(getApplicationContext(), Login.class);
                                //startActivity(intent);
                            }
                        }, new Response.ErrorListener() {

                            //error message
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                //error.printStackTrace();
                                // testing.setText("AAAAAAA");
                                try {
                                    // testing.setText(error.networkResponse==null?"taip":"ne");
                                    if(error.networkResponse != null)
                                    {
                                        if(error.networkResponse.data != null)
                                        {
                                            message.setText("Error: " + new JSONObject(new String(error.networkResponse.data)));
                                        }
                                        else
                                        {
                                            message.setText(String.valueOf(error.networkResponse.statusCode));

                                        }
                                    }
                                    else
                                    {
                                        //  message.setText("null");
                                        JsonArrayRequest jsn = new JsonArrayRequest(Request.Method.GET, url_add_friend, null, new Response.Listener<JSONArray>() {
                                            //assigns json object values to string and then to appropriate text box
                                            @Override
                                            public void onResponse(JSONArray response) {
                                                //message.setText("Response2: " + response.toString());
                                                //   testing.setText("bet kas");
                                                String xx = "";
                                                // try {
                                                xx = response.toString(); //("username");
                                                // ArrayList<String> arrayList = new ArrayList<>();

                                                for(int i = 0; i < response.length(); i++){
                                                    JSONObject jresponse = null;
                                                    try {
                                                        jresponse = response.getJSONObject(i);
                                                        String nickname = jresponse.getString("second_user");
                                                        // arrayList.add(nickname);
                                                        Log.d("second_user", nickname);
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }

                                                }


                                                // testing.setText(xx);

                                            }
                                        }, new Response.ErrorListener() {
                                            //error message
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                //    testing.setText("kazkas");
                                                try {
                                                    if(error.networkResponse != null)
                                                    {
                                                        if(error.networkResponse.data != null)
                                                        {
                                                            message.setText("Error: " + new JSONObject(new String(error.networkResponse.data)));
                                                        }
                                                        else
                                                        {
                                                            message.setText(String.valueOf(error.networkResponse.statusCode));
                                                        }
                                                    }
                                                    else
                                                    {
                                                        message.setText("null");
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                        });
                                        RequestGate.getInstance(getApplicationContext()).addToRequestQueue(jsn);

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                // Access the RequestQueue through your singleton class.
                RequestGate.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
            }
        });
*/

 