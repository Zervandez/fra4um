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

public class LoginActivity extends AppCompatActivity {

    private EditText emailET, passwordET;
    private Button loginBtn;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = findViewById(R.id.login_emailEditText);
        passwordET = findViewById(R.id.login_editTextPassword);
        loginBtn = findViewById(R.id.login_loginBtn);

        firebaseAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailStr = emailET.getText().toString().trim();
                String passStr = passwordET.getText().toString();
                loginUser(emailStr, passStr);
            }
        });

    }

    private void loginUser(String email, String pass) {
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "LOGGED IN", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, FeedActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "LOGIN FAILED", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}