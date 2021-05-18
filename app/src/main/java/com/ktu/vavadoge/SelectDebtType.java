package com.ktu.vavadoge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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


public class SelectDebtType extends AppCompatActivity{
    ImageButton car, food, coffee, money;
    String type;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_type_of_debt);
        car = (ImageButton) findViewById(R.id.searchImageButtonMasina);
        food = (ImageButton) findViewById(R.id.searchImageButtonMaistas);
        coffee = (ImageButton) findViewById(R.id.searchImageButtonKava);
        money= (ImageButton) findViewById(R.id.searchImageButtonEuras);

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Debts.class);
                startActivity(intent);
                type="car";
                DebtType.setType(type);
                finish();
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Debts.class);
                startActivity(intent);
                type="food";
                DebtType.setType(type);
                finish();
            }
        });
        coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Debts.class);
                startActivity(intent);
                type="coffee";
                DebtType.setType(type);
                finish();
            }
        });
        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Debts.class);
                startActivity(intent);
                type="money";
                DebtType.setType(type);
                finish();
            }
        });
    }

}
