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
import android.widget.EditText;
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

public class sign_up extends AppCompatActivity {

    String signup_url = "http://192.168.0.147/messaging/pushNotification/userSignUp.php";
    String token="";
    EditText firstName,lastName,emailAddress;
    Button submit;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firstName = (EditText)findViewById(R.id.sign_up_firsName);
        lastName = (EditText) findViewById(R.id.sign_up_lastName);
        emailAddress = (EditText) findViewById(R.id.sign_up_email);
        submit = (Button) findViewById(R.id.sign_up_submit_btn);

        //Toast.makeText(sign_up.this,"Sign up",Toast.LENGTH_SHORT).show();
        //-----------------Getting Instance Id of User --------------------//
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Sign_up", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        token = task.getResult().getToken();

                        // Log and toast
                        //  String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("User Token", token);

                        //Toast.makeText(sign_up.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void onSubmit(View view){
        builder = new AlertDialog.Builder(sign_up.this);
        final String firstNameStr = firstName.getText().toString();
        final String lastNameStr = lastName.getText().toString();
        final String emailAddressStr = emailAddress.getText().toString();




        StringRequest stringRequest = new StringRequest(Request.Method.POST, signup_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//                        Toast.makeText(sign_up.this,"Sign up",Toast.LENGTH_SHORT).show();
                        try {
                            //Toast.makeText(sign_up.this,"Sign up",Toast.LENGTH_SHORT).show();
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code  = jsonObject.getString("code");
                            String message = jsonObject.getString("message");
                            Toast.makeText(sign_up.this,message,Toast.LENGTH_SHORT).show();
                            builder.setTitle("Server Response........");
                            builder.setMessage(message);
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    firstName.setText("");
                                    lastName.setText("");
                                    emailAddress.setText("");
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
                Toast.makeText(sign_up.this,"Error!!!",Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("firstName",firstNameStr);
                params.put("lastName",lastNameStr);
                params.put("email",emailAddressStr);
                params.put("token",token);

                return params;
            }
        };
        MySingleton.getInstance(sign_up.this).addToRequestQue(stringRequest);

    }
    public boolean checkNetworkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }
}
