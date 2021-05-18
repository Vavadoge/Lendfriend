package com.ktu.vavadoge;

import androidx.appcompat.app.AppCompatActivity;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt);
        String type =DebtType.getType();
        textView15 = (TextView)findViewById(R.id.textView15);
        image = (ImageView) findViewById(R.id.imageView2);
        textView15.setText(DebtType.getType());
        switch (type) {
            case "car":
                image.setBackgroundResource(R.drawable.ic_baseline_directions_car);
                break;
            case "food":
                image.setBackgroundResource(R.drawable.ic_baseline_fastfood);
                break;
            case "coffee":
                image.setBackgroundResource(R.drawable.ic_baseline_emoji_tea);
                break;
            case "money":
                image.setBackgroundResource(R.drawable.ic_baseline_euro_symbol);
                break;

        }



    }

}
