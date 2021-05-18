package com.ktu.vavadoge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextClock;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;

public class Registration extends AppCompatActivity {

    String url_register = "http://134.209.250.135:8080/register";

    String name, username, email, password;
    EditText input_name, input_username, input_email, input_password;
    Button button;
    TextView message, sign_in;
    TextView name_er, username_er, password_er, email_er;
   // RadioGroup radioButton;
    RadioButton female, male, other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        input_name = (EditText) findViewById(R.id.editTextTextPersonName);
        input_username = (EditText) findViewById(R.id.editTextTextPersonName2);
        input_password = (EditText) findViewById(R.id.editTextTextPassword);
        input_email = (EditText) findViewById(R.id.editTextTextEmailAddress);

        button = (Button) findViewById(R.id.button_register);
        message = (TextView) findViewById(R.id.textView2);
        sign_in = (TextView) findViewById(R.id.sign_in);

        name_er = (TextView) findViewById(R.id.name_error);
        username_er = (TextView) findViewById(R.id.username_error);
        password_er = (TextView) findViewById(R.id.password_error);
        email_er = (TextView) findViewById(R.id.email_error);
        female = (RadioButton) findViewById(R.id.female);
        male = (RadioButton) findViewById(R.id.male);
        other = (RadioButton) findViewById(R.id.other);
      /*  radioButton.setOnClickListener(new View.OnClickListener() {
            //when user press registration button, the  registration  page is redirected to login page
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                boolean checked = ((RadioButton) view).isChecked();

                // Check which radio button was clicked
                switch(view.getId()) {
                    case R.id.female:
                        if (checked){
                            UserPicture.setPicture("female");
                            break;}
                    case R.id.male:
                        if (checked) {
                            UserPicture.setPicture("male");
                            // Ninjas rule
                            break;
                        }
                    case R.id.other:
                        if (checked) {
                            UserPicture.setPicture("other");
                            // Ninjas rule
                            break;
                        }
                }
            }
        });*/



        sign_in.setOnClickListener(new View.OnClickListener() {
            //when user press registration button, the  registration  page is redirected to login page
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = input_name.getText().toString();
                username = input_username.getText().toString();
                password = input_password.getText().toString();
                email = input_email.getText().toString();


                boolean kazkas =true;
                if(val_name(name) |  val_username(username) | val_password(password) | val_email(email))
                {
                    JSONObject test = new JSONObject();
                    try {
                        test.put("name", name);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
                    try {
                        test.put("email", email);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                    if (other.isChecked()) {
                        UserPicture.setPicture("other");
                        try {
                            test.put("picture_id", 3);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                      //  andyOrton.getText().toString();
                    } else if (male.isChecked()) {
                        UserPicture.setPicture("male");
                        try {
                            test.put("picture_id", 1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                      //  selectedSuperStar = sheamus.getText().toString();
                    } else if (female.isChecked()) {
                        UserPicture.setPicture("female");
                        try {
                            test.put("picture_id", 2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                       // selectedSuperStar = johnCena.getText().toString();
                    }
                   else {
                        UserPicture.setPicture("other");
                        //  andyOrton.getText().toString();
                    }


                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.POST, url_register, test, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    //message.setText("Response: " + response.toString());
                                    //Log.i("tag", "test");


                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                }
                            }, new Response.ErrorListener() {

                                //error message
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

                    // Access the RequestQueue through your singleton class.
                    RequestGate.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
                }


            }
        });


    }

    // validation of input fields
    private boolean val_email(String email) {
        int count = 0;
        int index = -1;
        String specialCharacters = "^(?=.*[!#$%^&*(),?:{}|<>]).{5,30}$";
        boolean doesHaveSpecialC = email.matches(specialCharacters); //email.matches(specialCharacters);

        for(int i=0; i<email.length(); i++)
        {
            if(email.charAt(i)=='@'){

                count++;
                index=i;
            }
        }
        int pr = index + 1;
        int pab = email.length() - (index+1);
        if (count==1 && doesHaveSpecialC==false && email.length() > 5 && pr>1 && pab>1 ) {
            email_er.setText("");
            return true;
        }
        else {
            if(email.isEmpty())
            {
                email_er.setText("email's field can not be empty'");
                return false;
            }
            email_er.setText("email is not valid");
            return false;
        }
       // return true;
    }

    private boolean val_password(String password) {
        int countNumbers=0;
        int countSpecialSymbol=0;
        String specialCharacters = "^(?=.*[0-9!@#$%^&*(),.?:{}|<>\\.]).{5,15}$";
        boolean doesHaveSpecialC = password.matches(specialCharacters);

        if(doesHaveSpecialC == true)
        {
            password_er.setText("");
            return true;
        }
        else {
            if(password.isEmpty())
            {
                password_er.setText("password's field can not be empty'");
                //password_er.setText(password);
                return false;
            }
            password_er.setText("password is not valid");
            //password_er.setText(password);
            return false;
        }
        //return true;
        //ilgesnis nei penki simboliai
        // turi tureti arba bent viena skaiciu arba bent viena special simboli

    }

    // Validates that the username is more than 4 characters long
    private boolean val_username(String username) {
        if(username.length() > 4)
        {
            username_er.setText("");
            return true;
        }
        else {
            if(username.isEmpty())
            {
                username_er.setText("username's field can not be empty'");
                return false;
            }
            username_er.setText("username is not valid");
            return false;
        }
       // return true;
    }

    private boolean val_name(String name) {
        int countNumbers=0;
        int countSpecialSymbol=0;
        String specialCharacters = "^(?=.*[0-9!@#$%^&*(),.?:{}|<>\\.]).{2,15}$";
        boolean doesHaveSpecialC = name.matches(specialCharacters);


        if(name.length()>1 && doesHaveSpecialC==false)
        {
            name_er.setText("");
            return true;
        }
        else {
            if(name.isEmpty())
            {
                name_er.setText("name's field can not be empty'");
                return false;
            }
            name_er.setText("name is not valid");
            return false;
        }
    }
}