package com.ktu.vavadoge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity {

    Button logout;
    String url_logout = "http://134.209.250.135:8080/user/logout";
    String url_take_data = "http://134.209.250.135:8080/user/self";
    TextView message;
    TextView name;
    TextView email;
    TextView test;
    TextView time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout = (Button) findViewById(R.id.logout_button);
        message = (TextView) findViewById(R.id.textView6);
        name = (TextView)findViewById(R.id.profile_name_text);
        email = (TextView)findViewById(R.id.profile_email_text);
        test = (TextView) findViewById(R.id.username_field);
        time = (TextView) findViewById(R.id.time_field);


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
    }
}