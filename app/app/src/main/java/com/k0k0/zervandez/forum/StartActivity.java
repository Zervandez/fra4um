package com.k0k0.zervandez.forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {

    private Button loginBtn, registerBtn;
    private EditText emailET, passwordET;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        registerBtn = findViewById(R.id.start_registerBtn);
        loginBtn = findViewById(R.id.start_login_button);
        emailET = findViewById(R.id.start_login_emailEditText);
        passwordET = findViewById(R.id.start_login_editTextPassword);

        firebaseAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailStr = emailET.getText().toString().trim();
                String passStr = passwordET.getText().toString();
                loginUser(emailStr, passStr);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, RegisterActivity.class));
            }
        });

    }


    private void loginUser(String email, String pass) {
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "WELCOME TO FRA4RUM", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(StartActivity.this, PostActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "PLEASE TRY AGAIN!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
