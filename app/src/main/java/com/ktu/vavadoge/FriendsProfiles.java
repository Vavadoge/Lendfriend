package com.ktu.vavadoge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class FriendsProfiles extends AppCompatActivity {

   // Button button, buttonDebt;
    RecyclerView listView,listViewNotFriends;
    String url_add_friend = "http://134.209.250.135:8080/user/friend-request?self=true";
    String url_check_friends = "http://134.209.250.135:8080/user/friend";
    String url_check_friend_requests = "http://134.209.250.135:8080/user/friend-request";


    ArrayList<String> arrayListFriends = new ArrayList<>();
    List<String> listFriends = new LinkedList<>();
    ArrayList<String> arrayListNotFriendsSent = new ArrayList<>();
    List<String> listNotFriendsSent = new LinkedList<>();
    ArrayList<String> arrayListNotFriendsReceived = new ArrayList<>();
    List<String> listNotFriendsReceived = new LinkedList<>();
    //ArrayList<UserFriend> allFriends = new ArrayList<>();
    ArrayList<UserFriend> friends1 = new ArrayList<>();
    ArrayList<UserFriend> friends2 = new ArrayList<>();
    ArrayList<UserFriend> friends = new ArrayList<>();
    ArrayList<String> eilute = new ArrayList<>();
    ArrayList<UserFriend> finalList = new ArrayList<>();
    ArrayList<ArrayList<UserFriend>> draugai = new ArrayList<>();
    //UserFriend[] friendList1;
    RequestQueue queue;
    TextView textInvite,textView_invite_action;//quote,
    FloatingActionButton fab;
    EditText input_otherUser;


    String otherName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
      //  button = (Button) findViewById(R.id.buttonFriend);
        //button = (Button) findViewById(R.id.friendsList);
        listView = (RecyclerView) findViewById(R.id.listView);
        queue = Volley.newRequestQueue(this);

//        listViewNotFriends =(RecyclerView) findViewById(R.id.appendingFriends);
       // input_otherUser = (EditText) findViewById(R.id.friendsName);
     //   buttonDebt = (Button) findViewById(R.id.buttonDebt);
    //    quote =  (TextView) findViewById(R.id.textview_friends_quote);
        textInvite =  (TextView) findViewById(R.id.textView_friends_invite);
        textView_invite_action =  (TextView) findViewById(R.id.textView_friends_invite_action);
        textView_invite_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AddingFriend.class);
                startActivity(intent);

                finish();
            }
        });
        fab = (FloatingActionButton) findViewById(R.id.home_friends_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queue.start();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //      .setAction("Action", null).show();
            }
        });


     /*

*/

      /*  buttonDebt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Debts.class);
                startActivity(intent);
                Intent intent1 = new Intent(FriendsProfiles.this, Profile.class);
                startActivity(intent1);

            }
        });*/
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
                    //quote.setVisibility(View.GONE);
                    textInvite.setVisibility(View.GONE);
                    textView_invite_action.setVisibility(View.GONE);

                    // set up the RecyclerView
                    UserFriend [] friendList1 = finalList.toArray(new UserFriend[finalList.size()]);
                    RecyclerView recyclerView = findViewById(R.id.listView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    FriendsAdapter arrayAdapter = new FriendsAdapter(getApplicationContext(), friendList1);
                    recyclerView.setAdapter(arrayAdapter);
                    //cancelAllQueuedRequests();
                    //FriendsAdapter(getApplicationContext(), R.layout.activity_viewlist, R.id.tekstukas, friendList1);


                    // ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.activity_viewlist, R.id.tekstukas, friendList1);
                    // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_friends, R.id.textView, arrayList);
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
                    //quote.setVisibility(View.GONE);
                    textInvite.setVisibility(View.GONE);
                    textView_invite_action.setVisibility(View.GONE);

                    // set up the RecyclerView
                    UserFriend[] friendList1 = finalList.toArray(new UserFriend[finalList.size()]);
                    RecyclerView recyclerView = findViewById(R.id.listView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    FriendsAdapter arrayAdapter = new FriendsAdapter(getApplicationContext(), friendList1);
                    recyclerView.setAdapter(arrayAdapter);
                }
//
//                try {
//                    if(error.networkResponse != null)
//                    {
//                        if(error.networkResponse.data != null)
//                        {
//                            message.setText("Error: " + new JSONObject(new String(error.networkResponse.data)));
//                        }
//                        else
//                        {
//                            message.setText(String.valueOf(error.networkResponse.statusCode));
//                        }
//                    }
//                    else
//                    {
//                        message.setText("null");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
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
                    //quote.setVisibility(View.GONE);
                    textInvite.setVisibility(View.GONE);
                    textView_invite_action.setVisibility(View.GONE);

                    // set up the RecyclerView
                    UserFriend [] friendList1 = finalList.toArray(new UserFriend[finalList.size()]);
                    RecyclerView recyclerView = findViewById(R.id.listView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    FriendsAdapter arrayAdapter = new FriendsAdapter(getApplicationContext(), friendList1);
                    recyclerView.setAdapter(arrayAdapter);
                    //cancelAllQueuedRequests();
                    //FriendsAdapter(getApplicationContext(), R.layout.activity_viewlist, R.id.tekstukas, friendList1);


                    // ArrayAdapter arrayAdapter = new arrayAdapter(getApplicationContext(), R.layout.activity_viewlist, R.id.tekstukas, friendList1);
                    // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_friends, R.id.textView, arrayList);
                }
                /*if(friends1.size()>0)
                {

                    //quote.setVisibility(View.GONE);
                    textInvite.setVisibility(View.GONE);
                    textView_invite_action.setVisibility(View.GONE);

                    // set up the RecyclerView

                    UserFriend [] friendList1 = friends1.toArray(new UserFriend[friends1.size()]);
                    RecyclerView recyclerView = findViewById(R.id.listView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    FriendsAdapter arrayAdapter = new FriendsAdapter(getApplicationContext(), friendList1);
                    recyclerView.setAdapter(arrayAdapter);
                   // cancelAllQueuedRequests();
                    //FriendsAdapter(getApplicationContext(), R.layout.activity_viewlist, R.id.tekstukas, friendList1);


                    // ArrayAdapter  = new ArrayAdapter(getApplicationContext(), R.layout.activity_viewlist, R.id.tekstukas, friendList1);
                    // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_friends, R.id.textView, arrayList);
                }*/

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
                    //quote.setVisibility(View.GONE);
                    textInvite.setVisibility(View.GONE);
                    textView_invite_action.setVisibility(View.GONE);

                    // set up the RecyclerView
                    UserFriend[] friendList1 = finalList.toArray(new UserFriend[finalList.size()]);
                    RecyclerView recyclerView = findViewById(R.id.listView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    FriendsAdapter arrayAdapter = new FriendsAdapter(getApplicationContext(), friendList1);
                    recyclerView.setAdapter(arrayAdapter);
                }
//
//                try {
//                    if(error.networkResponse != null)
//                    {
//                        if(error.networkResponse.data != null)
//                        {
//                            message.setText("Error: " + new JSONObject(new String(error.networkResponse.data)));
//                        }
//                        else
//                        {
//                            message.setText(String.valueOf(error.networkResponse.statusCode));
//                        }
//                    }
//                    else
//                    {
//                        message.setText("null");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
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
                    //quote.setVisibility(View.GONE);
                    textInvite.setVisibility(View.GONE);
                    textView_invite_action.setVisibility(View.GONE);

                    // set up the RecyclerView
                    UserFriend [] friendList1 = finalList.toArray(new UserFriend[finalList.size()]);
                    RecyclerView recyclerView = findViewById(R.id.listView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    FriendsAdapter arrayAdapter = new FriendsAdapter(getApplicationContext(), friendList1);
                    recyclerView.setAdapter(arrayAdapter);
                    //cancelAllQueuedRequests();
                    //FriendsAdapter(getApplicationContext(), R.layout.activity_viewlist, R.id.tekstukas, friendList1);


                   // ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.activity_viewlist, R.id.tekstukas, friendList1);
                    // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_friends, R.id.textView, arrayList);
                }

               /* if(listFriends.size()>0)
                {
                    String countryList[] = new String[listFriends.size()];
                  listFriends.toArray(countryList);


                 ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.activity_viewlist, R.id.tekstukas, countryList);
                // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_friends, R.id.textView, arrayList);
                listView.setAdapter(arrayAdapter);
                }
                if(listNotFriendsReceived.size()>0)
                {
                    String listA[] = new String[listNotFriendsReceived.size()];
                    listNotFriendsReceived.toArray(listA);


                    ArrayAdapter arrayAdapter1 = new ArrayAdapter(getApplicationContext(), R.layout.activity_viewlist_notfriends, R.id.notFriendsView, listA);
                    // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_friends, R.id.textView, arrayList);
                    listViewNotFriends.setAdapter(arrayAdapter1);
                }*/
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
                        //quote.setVisibility(View.GONE);
                        textInvite.setVisibility(View.GONE);
                        textView_invite_action.setVisibility(View.GONE);

                        // set up the RecyclerView
                        UserFriend[] friendList1 = finalList.toArray(new UserFriend[finalList.size()]);
                        RecyclerView recyclerView = findViewById(R.id.listView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        FriendsAdapter arrayAdapter = new FriendsAdapter(getApplicationContext(), friendList1);
                        recyclerView.setAdapter(arrayAdapter);
                    }
                    // set up the RecyclerView
                  //  UserFriend [] friendList1 = finalList.toArray(new UserFriend[finalList.size()]);
                   // RecyclerView recyclerView = findViewById(R.id.listView);
                  //  recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                   // FriendsAdapter arrayAdapter = new FriendsAdapter(getApplicationContext(), friendList1);
                   // recyclerView.setAdapter(arrayAdapter);

                    //FriendsAdapter(getApplicationContext(), R.layout.activity_viewlist, R.id.tekstukas, friendList1);


                    // ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.activity_viewlist, R.id.tekstukas, friendList1);
                    // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_friends, R.id.textView, arrayList);

                //int i = 0;
//
//                try {
//                    if(error.networkResponse != null)
//                    {
//                        if(error.networkResponse.data != null)
//                        {
//                            message.setText("Error: " + new JSONObject(new String(error.networkResponse.data)));
//                        }
//                        else
//                        {
//                            message.setText(String.valueOf(error.networkResponse.statusCode));
//                        }
//                    }
//                    else
//                    {
//                        message.setText("null");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }

        });

       // System.out.println("SVARBUUUUUUUU DYDIS  " + kiekis)
        queue.add(jsn2);
        queue.add(jsn1);
        queue.add(jsn);
        //cancelAllQueuedRequests();
       // RequestGate.getInstance(getApplicationContext()).addToRequestQueue(jsn2);
        //RequestGate.getInstance(getApplicationContext()).addToRequestQueue(jsn1);
        //RequestGate.getInstance(getApplicationContext()).addToRequestQueue(jsn);
     //  System.out.println("SVARBUUUUUUUU DYDIS  " + kiekis);


        /*if(friends.size()>0)
        {
            //quote.setVisibility(View.GONE);
            textInvite.setVisibility(View.GONE);
            textView_invite_action.setVisibility(View.GONE);

            // set up the RecyclerView

            UserFriend [] friendList1 = friends.toArray(new UserFriend[friends.size()]);
            RecyclerView recyclerView = findViewById(R.id.listView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            FriendsAdapter arrayAdapter = new FriendsAdapter(getApplicationContext(), friendList1);
            recyclerView.setAdapter(arrayAdapter);

            //FriendsAdapter(getApplicationContext(), R.layout.activity_viewlist, R.id.tekstukas, friendList1);


            // ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.activity_viewlist, R.id.tekstukas, friendList1);
            // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_friends, R.id.textView, arrayList);
        }*/

        // String list[] = (String[]) arrayList.toArray();
        // for(int i=0; i<arrayList.)
     //   int index = list.size();

        //   ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.activity_viewlist, R.id.tekstukas, list);
        // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_friends, R.id.textView, arrayList);

        //assign adapter to listview
        //    listView.setAdapter(arrayAdapter);

    }
    public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

        private UserFriend[] localDataSet;
        private LayoutInflater mInflater;
        //private ClickListener  listener;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView textView;
            private final Button addBtn;
            private final Button deleteBtn;
            private final TextView textFriend;
            private final TextView textRequest;
            public RelativeLayout relativeLayout;


            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                textView = (TextView) view.findViewById(R.id.FriendText);
                addBtn = (Button)view.findViewById(R.id.add_btn);
                deleteBtn = (Button)view.findViewById(R.id.delete_btn);
                textFriend = (TextView)view.findViewById(R.id.textboxAlreadyFriend);
                textRequest = (TextView)view.findViewById(R.id.textboxRequestWasSent);
                relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);

            }

            public TextView getTextView() {
                return textView;
            }
            public Button getAddBtn() {
                return addBtn;
            }
            public Button getDeleteBtn() {
                return deleteBtn;
            }
            public TextView getTextFriend() {
                return textFriend;
            }
            public TextView getTextRequest() {
                return textRequest;
            }
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data to populate views to be used
         * by RecyclerView.
         */
        //public CustomAdapter(UserFriend[] dataSet, ClickListener listener)
        public FriendsAdapter(Context context, UserFriend[] dataSet) {
            localDataSet = dataSet;
            this.mInflater = LayoutInflater.from(context);
            // this.listener = listener;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View listItem= layoutInflater.inflate(R.layout.activity_viewlist_friend, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(listItem);
            return viewHolder;
        }
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

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
                viewHolder.getTextFriend().setVisibility(View.VISIBLE);
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
//                                    viewHolder.textFriend.setText("Added friend");
//                                    viewHolder.textFriend.setVisibility(View.VISIBLE);
//                                    viewHolder.notify();
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

 /*
        button.setOnClickListener(new View.OnClickListener() {

            //when user press log out button, the  profile page is redirected to login page
            @Override
            public void onClick(View v) {
                otherName = input_otherUser.getText().toString();
                // testing = (TextView) findViewById(R.id.textView11);

                JSONObject test = new JSONObject();
                try {
                    test.put("username", otherName);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, url_add_friend, test, new Response.Listener<JSONObject>()  {

                            @Override
                            public void onResponse(JSONObject response) {
                                //testing.setText("krabas");
                                //   message.setText("Response: " + response);
                                //     Log.i("tag", "test");
                                //  kazkas = testing.getText().toString();


                                //     Intent intent = new Intent(getApplicationContext(), Login.class);
                                //startActivity(intent);
                            }
                        }, new Response.ErrorListener() {

                            //error message
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                //error.printStackTrace();
                                // testing.setText("AAAAAAA");
                                try {
                                    // testing.setText(error.networkResponse==null?"taip":"ne");
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
                                        //  message.setText("null");
                                        JsonArrayRequest jsn = new JsonArrayRequest(Request.Method.GET, url_add_friend, null, new Response.Listener<JSONArray>() {
                                            //assigns json object values to string and then to appropriate text box
                                            @Override
                                            public void onResponse(JSONArray response) {
                                                //message.setText("Response2: " + response.toString());
                                                //   testing.setText("bet kas");
                                                String xx = "";
                                                // try {
                                                xx = response.toString(); //("username");
                                                // ArrayList<String> arrayList = new ArrayList<>();

                                                for(int i = 0; i < response.length(); i++){
                                                    JSONObject jresponse = null;
                                                    try {
                                                        jresponse = response.getJSONObject(i);
                                                        String nickname = jresponse.getString("second_user");
                                                        // arrayList.add(nickname);
                                                        Log.d("second_user", nickname);
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }

                                                }


                                                // testing.setText(xx);

                                            }
                                        }, new Response.ErrorListener() {
                                            //error message
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                //    testing.setText("kazkas");
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
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                // Access the RequestQueue through your singleton class.
                RequestGate.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
            }
        });
*/

 