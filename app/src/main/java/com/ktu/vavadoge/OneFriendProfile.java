package com.ktu.vavadoge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OneFriendProfile  extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_firends_profile);
            TextView txtProduct = (TextView) findViewById(R.id.text_friends_profile);

            Intent i = getIntent();
            // getting attached intent data
            String product = i.getStringExtra("friend");
            // displaying selected product name
            txtProduct.setText(product);
        }
    }