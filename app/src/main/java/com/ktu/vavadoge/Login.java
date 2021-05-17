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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {

    // Database url
    String url_login = "http://134.209.250.135:8080/login";

    // Declaring the needed variables
    String username, password;
    EditText input_username, input_password;
    TextView message, sign_up;
    Button button;
    TextView username_er, password_er;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button) findViewById(R.id.button_login);
        input_username = (EditText) findViewById(R.id.editTextTextPersonName);
        input_password = (EditText) findViewById(R.id.editTextTextPassword);
        message = (TextView) findViewById(R.id.textView4);
        sign_up = (TextView) findViewById(R.id.sign_up);
        password_er = (TextView) findViewById(R.id.textView7);
        username_er = (TextView) findViewById(R.id.textView6);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
            }
        });




        // This is what happens after...
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = input_username.getText().toString();
                password = input_password.getText().toString();

                JSONObject test = new JSONObject();
                try {
                    test.put("username", username);
                    //username_er.setText("");
                } catch (JSONException e) {
                    e.printStackTrace();
                 //   username_er.setText("invalid username");
                }
                try {
                    test.put("password", password);
                //    password_er.setText("");
                } catch (JSONException e) {
                  //  username_er.setText("invalid password");
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, url_login, test, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                                                                       //message.setText("Response: " + response.toString());

                                // If the log in is successful, the user is taken to their profile
                               // Intent intent = new Intent(getApplicationContext(), Profile.class);
                              //  Intent intent = new Intent(getApplicationContext(), Profile.class);
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                //error.printStackTrace();
                                password_er.setText("Invalid username or password");

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