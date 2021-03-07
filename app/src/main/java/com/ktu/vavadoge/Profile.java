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
    TextView username;
    TextView name;
    TextView email;
    // Papildyt
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout = (Button) findViewById(R.id.logout_button);
        message = (TextView) findViewById(R.id.textView6);
        username = (TextView)findViewById(R.id.profile_username_text);
        name = (TextView)findViewById(R.id.profile_name_text);
        email = (TextView)findViewById(R.id.profile_email_text);
        // papildyt

        JsonObjectRequest jsn = new JsonObjectRequest(Request.Method.GET, url_take_data, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                message.setText("Response2: " + response.toString());

                // Pasiimti iš json response'o informaciją, ir ją išspausdinti gražiai į profilį
                // priskirt kintamuosius
                // pvz:
                //

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
                //JSONObject json = new JSONObject(yourdata);
                //String statistics = json.getString("statistics");
                //String ageJohn = name1.getString("Age");
                //
                //priskirti texview jau tą informaciją
                username.setText(usernames);
                name.setText(names);
                email.setText(emails);
                //
                //pvz: message.setText("KAZKOKS TEKSTAS");


            }
        }, new Response.ErrorListener() {
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