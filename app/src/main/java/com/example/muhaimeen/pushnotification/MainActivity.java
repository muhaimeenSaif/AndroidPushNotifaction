package com.example.muhaimeen.pushnotification;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button signUp,login;
    AlertDialog.Builder builder;
    String url = "http://192.168.0.147/messaging/pushNotification/send_notification.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUp = (Button) findViewById(R.id.home_signUp);
        login = (Button) findViewById(R.id.home_login);
        checkNetworkConnection();
        //Log.d("Pushnotification","test");

    }

    public void onSignUp(View view) {
        Intent signUp = new Intent(MainActivity.this,sign_up.class);
        startActivity(signUp);
    }
    public void onLogin(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            Toast.makeText(MainActivity.this,"Sign up",Toast.LENGTH_SHORT).show();
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code  = jsonObject.getString("code");
                            String message = jsonObject.getString("message");
                            Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
                            builder.setTitle("Server Response........");
                            builder.setMessage(message);
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                            //displayAlert(code);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Error!!!",Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("token","eWaMXZhI7GI:APA91bFzfZsDamrnHon9zCwcysqOrNZwOoIBE85eIxte67f4jK1I9l9ther7vfznEik9Sj6F8ndjTIlO2nuU-stIJycdm8WKhL3FuyKlPB_sejWzuRDyVVHAlETsziQojF8Vbr31JwVl");

                return params;
            }
        };
        MySingleton.getInstance(MainActivity.this).addToRequestQue(stringRequest);
//        Intent login = new Intent(MainActivity.this,sign_in.class);
//        startActivity(login);
    }
    public boolean checkNetworkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()){
            Toast.makeText(MainActivity.this,"Connected",Toast.LENGTH_SHORT).show();
            return true;
        }
        else
            Toast.makeText(MainActivity.this,"Internet not Connected",Toast.LENGTH_SHORT).show();
            return false;
    }
}
