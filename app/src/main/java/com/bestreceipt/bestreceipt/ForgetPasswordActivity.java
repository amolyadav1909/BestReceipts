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

/**
 * Created by pc4 on 08-02-2017.
 */

public class ForgetPasswordActivity extends Activity {
    String email;
    @BindView(R.id.buttonResetPassword) Button buttonResetPassword;
    @BindView( R.id.editTextEmail) EditText editTextEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    @OnClick(R.id.buttonResetPassword)
    public void forget_password(View view) {
        email = editTextEmail.getText().toString();
        if (email.length() == 0) {
            editTextEmail.setError("Please Enter Email Address");
        } else if (!isValidEmail(email)) {
            editTextEmail.setError("Email Address is not valid");
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://dev.valueittechnology.com/bestreceipt/auth/forgotten_password_app", new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("Mytag", "Response" + response);
                    if (response.contains("success")) {
                        editTextEmail.setText("");
                        Toast.makeText(ForgetPasswordActivity.this, "Instructions for resetting your password have just been emailed to you.", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(ForgetPasswordActivity.this, "Email does not exist into our record.Please Register", Toast.LENGTH_SHORT).show();
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
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
}
