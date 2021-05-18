package com.ktu.vavadoge;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Debts extends AppCompatActivity {
    TextView textView15;
    ImageView image;
    Button button;
    EditText input_type, input_username;
    String information, username;
    String url_register_debt = "http://134.209.250.135:8080/user/debt-request";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt);
        String type =DebtType.getType();
        textView15 = (TextView)findViewById(R.id.textView15);
        image = (ImageView) findViewById(R.id.imageView2);
        button = (Button) findViewById(R.id.buttonToRequestDebt);
        input_username = (EditText) findViewById(R.id.editTextTextDebtUsername);
        input_type = (EditText) findViewById(R.id.editTextTextDebtInformation);

        //textView15.setText(DebtType.getType());
        switch (type) {
            case "car":
                image.setBackgroundResource(R.drawable.ic_baseline_directions_car);
                input_type.setHint("Write trip's location");
                break;
            case "food":
                image.setBackgroundResource(R.drawable.ic_baseline_fastfood);
                input_type.setHint("Write food and place");
                break;
            case "coffee":
                image.setBackgroundResource(R.drawable.ic_baseline_emoji_tea);
                input_type.setHint("Write place");
                break;
            case "money":
                image.setBackgroundResource(R.drawable.ic_baseline_euro_symbol);
                input_type.setHint("Write amount");
                break;
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                information = input_type.getText().toString();
                username = input_username.getText().toString();


                JSONObject test = new JSONObject();
                try {
                    test.put("indebtor", username);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    test.put("type", type);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    test.put("information", information);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.POST, url_register_debt, test, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    textView15.setText("Debt has been sent");

                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                            }, new Response.ErrorListener() {

                                //error message

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    textView15.setText("Debt can not been sent");
                                }

                            });

                    // Access the RequestQueue through your singleton class.
                    RequestGate.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
                }



        });



    }

}
