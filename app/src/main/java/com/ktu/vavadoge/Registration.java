package com.ktu.vavadoge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

                //if all validation is correct then puts all the input values to json object

           /*     if(val_name(name)==false)
                {
                    name_er.setText("name is not valid");
                }
                email = input_email.getText().toString();
                if(val_name(name))
                {
                    name_er.setText("");
                }
                if(val_email(email)==false)
                {
                    email_er.setText("email is not valid");
                }
                if(val_email(email))
                {
                    email_er.setText("");
                }
                if(val_password(password)==false)
                {
                    password_er.setText("password is not valid");
                }
                if(val_password(password))
                {
                    password_er.setText("");
                }
                if(val_username(username)==false)
                {
                    username_er.setText("username is not valid");
                }
                if(val_username(username))
                {
                    username_er.setText("");
                }

            */
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
        int count =0;
        String specialCharacters = "[!#$%^&*(),?:{}|<>]";
        boolean doesHaveSpecialC = Pattern.matches(specialCharacters,email); //email.matches(specialCharacters);

        for(int i=0; i<email.length(); i++)
        {
            if(i=='@' ){
                if(Character.isLetter(email.charAt(i-1)) && Character.isLetter(email.charAt(i+1))) {
                    count++;
                }
            }
        }
        if (count==1 && doesHaveSpecialC==false) {
            email_er.setText("");
            return true;
        }
        else {
            if(email.isEmpty())
            {
                email_er.setText("password's field can not be empty'");
                return false;
            }
            email_er.setText("password is not valid");
            return false;
        }
       // return true;
    }

    private boolean val_password(String password) {
        int countNumbers=0;
        int countSpecialSymbol=0;
        String specialCharacters = "[0-9!@#$%^&*(),?:{}|<>]";

        boolean doesHaveSpecialC =  Pattern.matches(specialCharacters,password);//password.matches(specialCharacters);

        if(password.length()>5 && doesHaveSpecialC)//(countNumbers>0 || countSpecialSymbol >0))
        {
            password_er.setText("");
            return true;
        }
        else {
            if(password.isEmpty())
            {
                password_er.setText("password's field can not be empty'");
                return false;
            }
            password_er.setText("password is not valid");
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
        String specialCharacters = "[0-9!@#$%^&*(),?:{}|<>]";
        boolean doesHaveSpecialC = name.matches(specialCharacters);

        /*for(int i=0; i<name.length(); i++) {
            if (Character.isDigit(name.charAt(i))) {
                countNumbers++;
            }
            if (name.charAt(i) == '.' || name.charAt(i) == '!' || name.charAt(i) == '?') {
                countSpecialSymbol++;
            }
        }*/

        if(name.length()>2 && doesHaveSpecialC==false)
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
      //  return true;
    }
}