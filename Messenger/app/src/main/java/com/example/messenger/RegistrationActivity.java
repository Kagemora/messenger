package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextHowOld;
    private EditText editTextLastName;
    private EditText editTextPasswordRegistration;
    private EditText editTextEmailRegistration;
    private Button buttonSignUp;
    private RegistrationViewModel registrationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initView();
        registrationViewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        observeViewModel();
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getTrimmedText(editTextEmailRegistration);
                String password = getTrimmedText(editTextPasswordRegistration);
                String name = getTrimmedText(editTextName);
                String lastName = getTrimmedText(editTextLastName);
                int ageText = Integer.parseInt(getTrimmedText(editTextHowOld));

                registrationViewModel.signUp(email,password,name,lastName,ageText);
            }
        });
    }
    public static Intent getIntentRegistrationActivity(Context context){
        return new Intent(context,RegistrationActivity.class);
    }
    private void observeViewModel(){
        registrationViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                if(errorMessage != null){
                    Toast.makeText(RegistrationActivity.this,errorMessage,Toast.LENGTH_SHORT).show();
                }
            }
        });
        registrationViewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser !=null){
                    Intent intent = UsersActivity.getIntentUsersActivity(RegistrationActivity.this,firebaseUser.getUid());
                    startActivity(intent);
                    finish();
                }
            }
        });
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