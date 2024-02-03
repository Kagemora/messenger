package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotPasswordActivity extends AppCompatActivity {
    private static String EXTRA_EMAIL = "email";
    private Button buttonResetPassword;
    private EditText editTextEmailRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initView();
        String email = getIntent().getStringExtra("email");
        editTextEmailRegistration.setText(email);
        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmailRegistration.getText().toString().trim();

                Intent intent = LoginActivity.getIntentLoginActivity(ForgotPasswordActivity.this);
                startActivity(intent);
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