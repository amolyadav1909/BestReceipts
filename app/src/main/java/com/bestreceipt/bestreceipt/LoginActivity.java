package com.bestreceipt.bestreceipt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by pc4 on 06-02-2017.
 */

public class LoginActivity extends Activity {
    String email,password;
    @BindView(R.id.buttonRegister) Button buttonRegister;
    @BindView(R.id.buttonLogin) Button buttonLogin;
    @BindView( R.id.editTextPassword)
    EditText editTextPassword;
    @BindView( R.id.editTextEmail) EditText editTextEmail;
@BindView(R.id.textViewForgetPassword) TextView textViewForgetPassword;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        if(sharedPreferences.getString("email",null)!=null)
        {
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
     @OnClick(R.id.buttonRegister)
        public void submit(View view) {
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.buttonLogin)
    public void login(View view) {
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();
        Log.e("Mytag", "email" + email);
        if(email.length()==0)
        {
            editTextEmail.setError("Please Enter Email Address");
        }
        else if(password.length()==0)
        {
            editTextPassword.setError("Please Enter Password");
        }
        else if(password.length()<4)
        {
            editTextPassword.setError("Password should not be less than 4 Character");
        }
        else if(!isValidEmail(email))
        {
            editTextEmail.setError("Email Address is not valid");
        }
        else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://dev.valueittechnology.com/bestreceipt/auth/login_app", new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("Mytag", "Response" + response);
                    if(response.contains("success"))
                    {
                        editor.putString("email",email);
                        editor.commit();
                        editTextPassword.setText("");
                        editTextEmail.setText("");
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                        Toast.makeText(LoginActivity.this, "No Email Record Found,Please Register or Click on Forget password", Toast.LENGTH_SHORT).show();
                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Mytag","Error"+error);
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

    }
    @OnClick(R.id.textViewForgetPassword)
    public void forget_password(View view) {
       Intent intent = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
        startActivity(intent);
    }
}
