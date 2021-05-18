package com.ktu.vavadoge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OneFriendProfile  extends AppCompatActivity {
    String url_debts = "http://134.209.250.135:8080/user/debt";
    String url_check_debt_requests = "http://134.209.250.135:8080/user/debt-request";


    String url_add_friend = "http://134.209.250.135:8080/user/friend-request?self=true";
    String url_check_friends = "http://134.209.250.135:8080/user/friend";
    String url_check_friend_requests = "http://134.209.250.135:8080/user/friend-request";



    ArrayList<UserFriend> friends1 = new ArrayList<>();
    ArrayList<UserFriend> friends2 = new ArrayList<>();
    ArrayList<UserFriend> friends = new ArrayList<>();
    ArrayList<String> eilute = new ArrayList<>();
    ArrayList<UserFriend> finalList = new ArrayList<>();
    ArrayList<ArrayList<UserFriend>> draugai = new ArrayList<>();

    //UserFriend[] friendList1;
    RequestQueue queue;

    RecyclerView listView;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_firends_profile);
            TextView txtProduct = (TextView) findViewById(R.id.text_friends_profile);

            //paraso varda draugo
            Intent i = getIntent();
            // getting attached intent data
            String product = i.getStringExtra("friend");
            // displaying selected product name
            txtProduct.setText(product);
            //pabaiga


            listView = (RecyclerView) findViewById(R.id.listViewOneFriendProfile);
            queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsn2 = new JsonArrayRequest(Request.Method.GET, url_check_friend_requests, null, new Response.Listener<JSONArray>() {
            //assigns json object values to string and then to appropriate text box
            @Override
            public void onResponse(JSONArray response) {

                String xx = "";
                xx = response.toString(); //("username");
                // String countryList[];
                for (int i = 0; i < response.length(); i++) {
                    JSONObject jresponse = null;
                    try {
                        jresponse = response.getJSONObject(i);

                        String nickname = jresponse.getString("friend");
                        String first = jresponse.getString("owner");
                        String status = jresponse.getString("accepted");
                        friends.add(new UserFriend(first, status, "received"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                eilute.add("received");
                draugai.add(friends);
                System.out.println("-------received-----------" + friends.size());
                if(eilute.size()==3)
                {
                    //String[] text = eilute.toArray(new String[3]);
                    finalList.addAll(friends);
                    finalList.addAll(friends1);
                    finalList.addAll(friends2);

                    // set up the RecyclerView
                    UserFriend [] friendList1 = finalList.toArray(new UserFriend[finalList.size()]);
                    RecyclerView recyclerView = findViewById(R.id.listView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    OneFriendProfile.DebtsAdapter arrayAdapter = new OneFriendProfile.DebtsAdapter(getApplicationContext(), friendList1);
                    recyclerView.setAdapter(arrayAdapter);
                }
            }
        }, new Response.ErrorListener() {
            //error message
            @Override
            public void onErrorResponse(VolleyError error) {
                eilute.add("received");
                System.out.println("-----------------------Vo cia----------- received");
                if(eilute.size()==3) {
                    //String[] text = eilute.toArray(new String[3]);
                    finalList.addAll(friends);
                    finalList.addAll(friends1);
                    finalList.addAll(friends2);

                    // set up the RecyclerView
                    UserFriend[] friendList1 = finalList.toArray(new UserFriend[finalList.size()]);
                    RecyclerView recyclerView = findViewById(R.id.listView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    OneFriendProfile.DebtsAdapter arrayAdapter = new OneFriendProfile.DebtsAdapter(getApplicationContext(), friendList1);
                    recyclerView.setAdapter(arrayAdapter);
                }
            }

        });
        JsonArrayRequest jsn1 = new JsonArrayRequest(Request.Method.GET, url_check_friends, null, new Response.Listener<JSONArray>() {
            //assigns json object values to string and then to appropriate text box
            @Override
            public void onResponse(JSONArray response) {

                String xx = "";
                xx = response.toString(); //("username");
                // String countryList[];
                for (int i = 0; i < response.length(); i++) {
                    JSONObject jresponse = null;
                    try {
                        jresponse = response.getJSONObject(i);

                        String nickname = jresponse.getString("friend");
                        String first = jresponse.getString("owner");
                        String status = jresponse.getString("accepted");
                        friends1.add(new UserFriend(nickname, status, "friend"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                eilute.add("friend");
                draugai.add(friends1);
                System.out.println("-------friend-----------" + friends1.size());
                if(eilute.size()==3)
                {
                    //String[] text = eilute.toArray(new String[3]);
                    finalList.addAll(friends);
                    finalList.addAll(friends1);
                    finalList.addAll(friends2);

                    // set up the RecyclerView
                    UserFriend [] friendList1 = finalList.toArray(new UserFriend[finalList.size()]);
                    RecyclerView recyclerView = findViewById(R.id.listView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    OneFriendProfile.DebtsAdapter arrayAdapter = new OneFriendProfile.DebtsAdapter(getApplicationContext(), friendList1);
                    recyclerView.setAdapter(arrayAdapter);
                }

            }
        }, new Response.ErrorListener() {
            //error message
            @Override
            public void onErrorResponse(VolleyError error) {
                eilute.add("friend");
                System.out.println("-----------------------Vo cia----------- friend");
                if(eilute.size()==3) {
                    //String[] text = eilute.toArray(new String[3]);
                    finalList.addAll(friends);
                    finalList.addAll(friends1);
                    finalList.addAll(friends2);

                    // set up the RecyclerView
                    UserFriend[] friendList1 = finalList.toArray(new UserFriend[finalList.size()]);
                    RecyclerView recyclerView = findViewById(R.id.listView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    OneFriendProfile.DebtsAdapter arrayAdapter = new OneFriendProfile.DebtsAdapter(getApplicationContext(), friendList1);
                    recyclerView.setAdapter(arrayAdapter);
                }

            }

        });
        //  message.setText("null");
        JsonArrayRequest jsn = new JsonArrayRequest(Request.Method.GET, url_add_friend, null, new Response.Listener<JSONArray>() {
            //assigns json object values to string and then to appropriate text box
            @Override
            public void onResponse(JSONArray response) {

                String xx = "";
                xx = response.toString(); //("username");
                // String countryList[];
                for(int i = 0; i < response.length(); i++){
                    JSONObject jresponse = null;
                    try {
                        jresponse = response.getJSONObject(i);

                        String nickname = jresponse.getString("friend");
                        String first = jresponse.getString("owner");
                        String status = jresponse.getString("accepted");


                        String username = UserProfile.getName();

                        friends2.add(new UserFriend(nickname, status,"sent"));

                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                eilute.add("sent");
                draugai.add(friends2);
                System.out.println("-------sent-----------" + friends2.size());

                if(eilute.size()==3)
                {
                    //String[] text = eilute.toArray(new String[3]);
                    finalList.addAll(friends);
                    finalList.addAll(friends1);
                    finalList.addAll(friends2);

                    // set up the RecyclerView
                    UserFriend [] friendList1 = finalList.toArray(new UserFriend[finalList.size()]);
                    RecyclerView recyclerView = findViewById(R.id.listView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    OneFriendProfile.DebtsAdapter arrayAdapter = new OneFriendProfile.DebtsAdapter(getApplicationContext(), friendList1);
                    recyclerView.setAdapter(arrayAdapter);
                }
            }

        }, new Response.ErrorListener() {
            //error message
            @Override
            public void onErrorResponse(VolleyError error) {
                // if(friends.size()>0)
                // {
                eilute.add("accepted");
                System.out.println("-----------------------Vo cia");
                if(eilute.size()==3) {
                    //String[] text = eilute.toArray(new String[3]);
                    finalList.addAll(friends);
                    finalList.addAll(friends1);
                    finalList.addAll(friends2);

                    // set up the RecyclerView
                    UserFriend[] friendList1 = finalList.toArray(new UserFriend[finalList.size()]);
                    RecyclerView recyclerView = findViewById(R.id.listView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    OneFriendProfile.DebtsAdapter arrayAdapter = new OneFriendProfile.DebtsAdapter(getApplicationContext(), friendList1);
                    recyclerView.setAdapter(arrayAdapter);
                }
            }
        });

        queue.add(jsn2);
        queue.add(jsn1);
        queue.add(jsn);

    }
    public class DebtsAdapter extends RecyclerView.Adapter<OneFriendProfile.DebtsAdapter.ViewHolder> {

        private UserFriend[] localDataSet;      //CIA NE USERFRIEND
        private LayoutInflater mInflater;
        //private ClickListener  listener;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView textView;
            private final TextView textInDebt;
            private final TextView textRequest;
            private final Button addBtn;
            private final Button deleteBtn;
            private final Button finishBtn;

            public RelativeLayout relativeLayout;


            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View
                textView = (TextView) view.findViewById(R.id.DebtInfo);
                addBtn = (Button)view.findViewById(R.id.add_btn_debt);
                deleteBtn = (Button)view.findViewById(R.id.delete_btn_debt);
                finishBtn = (Button)view.findViewById(R.id.finish_btn_debt);
                textInDebt = (TextView)view.findViewById(R.id.textboxYouAreInDebt);
                textRequest = (TextView)view.findViewById(R.id.textboxRequestWasSent_debt);
                relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);

            }

            public TextView getTextView() {
                return textView;
            }
            public Button getAddBtn() {
                return addBtn;
            }
            public Button getFinishBtntn() {
                return finishBtn;
            }
            public Button getDeleteBtn() {
                return deleteBtn;
            }
            public TextView getTextRequest() {
                return textRequest;
            }
            public TextView getTexInDebt() {
                return textInDebt;
            }
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data to populate views to be used
         * by RecyclerView.
         */
        //public CustomAdapter(UserFriend[] dataSet, ClickListener listener)
        public DebtsAdapter(Context context, UserFriend[] dataSet) {
            localDataSet = dataSet;
            this.mInflater = LayoutInflater.from(context);
            // this.listener = listener;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public OneFriendProfile.DebtsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View listItem= layoutInflater.inflate(R.layout.activity_viewlist_friend_debt, viewGroup, false);
            OneFriendProfile.DebtsAdapter.ViewHolder viewHolder = new OneFriendProfile.DebtsAdapter.ViewHolder(listItem);
            return viewHolder;
        }
        @Override
        public void onBindViewHolder(OneFriendProfile.DebtsAdapter.ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            System.out.println("+" + getItemCount());
            if(localDataSet[position].getType().equals("received"))
            {
                viewHolder.getAddBtn().setVisibility(View.VISIBLE);
                viewHolder.getDeleteBtn().setVisibility(View.VISIBLE);
                viewHolder.getTextView().setText((CharSequence) localDataSet[position].getUsername());
            }
            if(localDataSet[position].getType().equals("sent"))
            {
                viewHolder.getTextRequest().setVisibility(View.VISIBLE);
                viewHolder.getTextView().setText((CharSequence) localDataSet[position].getUsername());
            }
            if(localDataSet[position].getType().equals("friend"))
            {
                viewHolder.getTextView().setText((CharSequence) localDataSet[position].getUsername());
            }
            viewHolder.getAddBtn().setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int row = viewHolder.getAdapterPosition();
                    String username = localDataSet[row].getUsername();
                    String url_patch = "http://134.209.250.135:8080/user/friend-request/";
                    url_patch += username;
                    StringRequest stringRequest = new StringRequest
                            (Request.Method.PATCH, url_patch, new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {
                                    //Kazka gal isvest i ekrana kad pridejo

                                    viewHolder.addBtn.setVisibility(View.GONE);
                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Kazka pasakyt kad nejo prideti

                                    // int a = 5;
                                }
                            });

                    // Access the RequestQueue through your singleton class.
                    RequestGate.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);


                }
            });

           /* viewHolder.getDeleteBtn().setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int row = viewHolder.getAdapterPosition();
                    String username = localDataSet[row].getUsername();
                    String url_patch = url_check_friends+"/";
                    url_patch += username;
                    StringRequest stringRequest = new StringRequest
                            (Request.Method.DELETE, url_patch, new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {
                                    //Kazka gal isvest i ekrana kad pridejo

                                    viewHolder.deleteBtn.setVisibility(View.GONE);
                                    viewHolder.addBtn.setVisibility(View.GONE);


//                                    viewHolder.textFriend.setText("Added friend");
//                                    viewHolder.textFriend.setVisibility(View.VISIBLE);
//                                    viewHolder.notify();
                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Kazka pasakyt kad nejo prideti

                                    int a = 5;
                                }
                            });

                    // Access the RequestQueue through your singleton class.
                    RequestGate.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);


                }
            });*/
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            int a = localDataSet.length;
            return a;
        }
    }
}