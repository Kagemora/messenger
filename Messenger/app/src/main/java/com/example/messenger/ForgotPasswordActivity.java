package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {
    private static String EXTRA_EMAIL = "email";
    private Button buttonResetPassword;
    private EditText editTextEmailRegistration;
    private ForgotPasswordViewModel forgotPasswordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initView();
        forgotPasswordViewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);
        observeForgot();
        String email = getIntent().getStringExtra("email");
        editTextEmailRegistration.setText(email);
        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmailRegistration.getText().toString().trim();
                forgotPasswordViewModel.resetPassword(email);
            }
        });

    }
    private void observeForgot(){
        forgotPasswordViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                if(error!=null) {
                    Toast.makeText(ForgotPasswordActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
        forgotPasswordViewModel.getUser().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Toast.makeText(ForgotPasswordActivity.this, R.string.reset_link_sent,Toast.LENGTH_SHORT).show();
                    Intent intent = LoginActivity.getIntentLoginActivity(ForgotPasswordActivity.this);
                    startActivity(intent);
                }
            }
        });
    }
    public static Intent getIntentForgotPasswordActivity(Context context, String email){
        Intent intent = new Intent(context,ForgotPasswordActivity.class);
        intent.putExtra(EXTRA_EMAIL,email);
        return intent;
    }
    private void initView(){
        buttonResetPassword = findViewById(R.id.buttonResetPassword);
        editTextEmailRegistration = findViewById(R.id.editTextEmailRegistration);
    }
}