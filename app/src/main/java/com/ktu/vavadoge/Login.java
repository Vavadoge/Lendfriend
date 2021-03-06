package com.ktu.vavadoge;

import androidx.appcompat.app.AppCompatActivity;

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

    String url = "http://134.209.250.135:8080/auth";
    String url_test = "http://134.209.250.135:8080/user";

    String username, password;
    EditText input_username, input_password;
    TextView message;
    Button button;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button) findViewById(R.id.button_login);
        input_username = (EditText) findViewById(R.id.editTextTextPersonName);
        input_password = (EditText) findViewById(R.id.editTextTextPassword);
        message = (TextView) findViewById(R.id.textView4);
        logout = (Button) findViewById(R.id.button_logout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = input_username.getText().toString();
                password = input_password.getText().toString();

                JSONObject test = new JSONObject();
                try {
                    test.put("username", username);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    test.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, url, test, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                message.setText("Response: " + response.toString());
                                Log.i("tag", "test");


                                JsonObjectRequest jsn = new JsonObjectRequest(Request.Method.GET, url_test, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        message.setText("Response2: " + response.toString());
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
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest jsonObjectRequest = new StringRequest
                        (Request.Method.DELETE, url_test, new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                message.setText("Response: " + response);
                                Log.i("tag", "test");

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