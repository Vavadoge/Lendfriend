package com.ktu.vavadoge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    Button logout, button, buttonFriends;
    String url_logout = "http://134.209.250.135:8080/user/logout";
    String url_take_data = "http://134.209.250.135:8080/user/self";
    String url_add_friend = "http://134.209.250.135:8080/user/friend";

    TextView message;
    TextView name;
    TextView email;
    TextView test;
    TextView time;

    TextView testing;

    EditText input_otherUser;

    String otherName;
    public ArrayList<String> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout = (Button) findViewById(R.id.logout_button);
        button = (Button) findViewById(R.id.open_bottom_sheet_button);
        buttonFriends = (Button) findViewById(R.id.friendsList);
        message = (TextView) findViewById(R.id.textView6);
        name = (TextView)findViewById(R.id.profile_name_text);
        email = (TextView)findViewById(R.id.profile_email_text);
        test = (TextView) findViewById(R.id.username_field);
        time = (TextView) findViewById(R.id.time_field);
        testing = (TextView) findViewById(R.id.textView11);


        input_otherUser = (EditText) findViewById(R.id.editTextTextPersonName3);




       /* Button OpenBottomSheet = findViewById(R.id.open_bottom_sheet_button);

        OpenBottomSheet.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                       // otherName = input_otherUser.getText().toString();
                        BottomSheetDialog bottomSheet = new BottomSheetDialog();
                        bottomSheet.show(getSupportFragmentManager(),
                                "ModalBottomSheet");
                    }
                });

*/
        JsonObjectRequest jsn = new JsonObjectRequest(Request.Method.GET, url_take_data, null, new Response.Listener<JSONObject>() {
            //assigns json object values to string and then to appropriate text box
            @Override
            public void onResponse(JSONObject response) {
                //message.setText("Response2: " + response.toString());

                String usernames = null;
                try {
                    usernames = response.getString("username");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String names = null;
                try {
                    names = response.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String emails= null;
                try {
                    emails = response.getString("email");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String time_value = null;
                try {
                    time_value = response.getString("created_at");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                name.setText(names);
                email.setText(emails);
                test.setText(usernames);
                time.setText(time_value);

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

        logout.setOnClickListener(new View.OnClickListener() {

            //when user press log out button, the  profile page is redirected to login page
            @Override
            public void onClick(View v) {
                StringRequest jsonObjectRequest = new StringRequest
                        (Request.Method.DELETE, url_logout, new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                message.setText("Response: " + response);
                                Log.i("tag", "test");

                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            }
                        }, new Response.ErrorListener() {

                            //error message
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                //error.printStackTrace();

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

                // Access the RequestQueue through your singleton class.
                RequestGate.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
            }
        });
        buttonFriends.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FriendsProfiles.class);
                startActivity(intent);
            }
        });
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
                                testing.setText("krabas");
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
                                testing.setText("AAAAAAA");
                                try {
                                    testing.setText(error.networkResponse==null?"taip":"ne");
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
                                                testing.setText("bet kas");
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


                                                testing.setText(xx);

                                            }
                                        }, new Response.ErrorListener() {
                                            //error message
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                testing.setText("kazkas");
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

    }
}