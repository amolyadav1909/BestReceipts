package com.bestreceipt.bestreceipt;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RegisterActivity extends Activity {
    String email,password,confirm_password;
    @BindView( R.id.editTextEmail) EditText editTextEmail;
    @BindView( R.id.editTextPassword) EditText editTextPassword;
    @BindView( R.id.editTextConfirmPassword) EditText editTextConfirmPassword;
    @BindView(R.id.buttonRegister) Button buttonRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    @OnClick(R.id.buttonRegister)
    public void register(View view) {
        email = editTextEmail.getText().toString();
        Log.e("Mytag", "email" + email);
        confirm_password = editTextConfirmPassword.getText().toString();
        password = editTextPassword.getText().toString();
        if(email.length()==0)
        {
            editTextEmail.setError("Please Enter Email Address");
        }
        else if(password.length()==0)
        {
            editTextPassword.setError("Please Enter Password");
        }
        else if(confirm_password.length()==0)
        {
            editTextConfirmPassword.setError("Please Enter Confirm Password");
        }
         else if(!password.equals(confirm_password))
          {
              editTextPassword.setError("Password and Confirm Password is not same");
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
              StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://dev.valueittechnology.com/bestreceipt/auth/register_app", new com.android.volley.Response.Listener<String>() {
                  @Override
                  public void onResponse(String response) {
                      Log.e("Mytag", "Response" + response);
                      if(response.contains("success"))
                      {
                          editTextPassword.setText("");
                          editTextEmail.setText("");
                          editTextPassword.setText("");
                          Toast.makeText(RegisterActivity.this, "Registeration Successful", Toast.LENGTH_SHORT).show();
                      }
                      else
                      {
                          Toast.makeText(RegisterActivity.this, "Email already exist", Toast.LENGTH_SHORT).show();
                      }
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
}
