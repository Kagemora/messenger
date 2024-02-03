package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextHowOld;
    private EditText editTextLastName;
    private EditText editTextPasswordRegistration;
    private EditText editTextEmailRegistration;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initView();
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getTrimmedText(editTextEmailRegistration);
                String password = getTrimmedText(editTextPasswordRegistration);
                String name = getTrimmedText(editTextName);
                String lastName = getTrimmedText(editTextLastName);
                String year = getTrimmedText(editTextHowOld);

                Intent intent = LoginActivity.getIntentLoginActivity(RegistrationActivity.this);
                startActivity(intent);
            }
        });
    }
    public static Intent getIntentRegistrationActivity(Context context){
        return new Intent(context,RegistrationActivity.class);
    }
    private void initView(){
        editTextName = findViewById(R.id.editTextName);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        editTextHowOld = findViewById(R.id.editTextHowOld);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextPasswordRegistration = findViewById(R.id.editTextPasswordRegistration);
        editTextEmailRegistration = findViewById(R.id.editTextEmailRegistration);
    }
    private String getTrimmedText(EditText editText){
       return editText.getText().toString().trim();
    }
}