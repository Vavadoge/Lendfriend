package com.ktu.vavadoge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class DebtsShow extends AppCompatActivity {

    // Button button, buttonDebt;
    ListView listView;
    String url_add_friend = "http://134.209.250.135:8080/user/friend-request?self=true";
    String url_check_friends = "http://134.209.250.135:8080/user/friend";
    String url_check_friend_requests = "http://134.209.250.135:8080/user/friend-request";


    ArrayList<String> friends = new ArrayList<>();
    TextView textInvite, textView_invite_action;//quote,
    FloatingActionButton fab, fab1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_debts);
        listView = (ListView) findViewById(R.id.listViewDebts);

        fab = (FloatingActionButton) findViewById(R.id.home_debts_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        fab1 = (FloatingActionButton) findViewById(R.id.add_debts_fab);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectDebtType.class);
                startActivity(intent);
            }
        });


        JsonArrayRequest jsn1 = new JsonArrayRequest(Request.Method.GET, url_check_friends, null, new Response.Listener<JSONArray>() {
            //assigns json object values to string and then to appropriate text box
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    JSONObject jresponse = null;
                    try {
                        jresponse = response.getJSONObject(i);

                        String nickname = jresponse.getString("friend");
                        String first = jresponse.getString("owner");
                        String status = jresponse.getString("accepted");
                        friends.add(nickname);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (friends.size() > 0) {


                    String[] friendList1 = new String[friends.size()];
                    friends.toArray(friendList1);
                    ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_viewlist_request, R.id.textboxViewDebtsFriend, friendList1);

                    listView.setAdapter(arrayAdapter);
                    listView.setOnItemClickListener(listClick);
                }
            }
        }, new Response.ErrorListener() {
            //error message
            @Override
            public void onErrorResponse(VolleyError error) {
            }

        });

        RequestGate.getInstance(getApplicationContext()).addToRequestQueue(jsn1);

    }

    // listening to single list item on click
    private AdapterView.OnItemClickListener listClick=
            new AdapterView.OnItemClickListener(){
        public void onItemClick(AdapterView parent, View view,
        int position, long id) {

            // selected item
         //   String friend = ((TextView) view).getText().toString();
            String friend = (String)listView.getItemAtPosition(position);
            // Launching new Activity on selecting single List Item
            Intent i = new Intent(getApplicationContext(), OneFriendProfile.class);
            // sending data to new activity
            i.putExtra("friend", friend);
            startActivity(i);

        }
    };

}



