package com.k0k0.zervandez.forum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText emailET, passwordET;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = findViewById(R.id.login_emailEditText);
        passwordET = findViewById(R.id.login_editTextPassword);
        loginBtn = findViewById(R.id.login_loginBtn);
        
    }
}