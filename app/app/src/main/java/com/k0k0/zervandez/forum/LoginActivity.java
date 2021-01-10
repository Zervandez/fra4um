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

/**
 * Allows the user to log in to the system using their email address
 *
 * @author Ariel Halilaj
 * @since 14.12.2020
 */

public class LoginActivity extends AppCompatActivity {

    private EditText emailET, passwordET;
    private Button loginBtn;

    private FirebaseAuth firebaseAuth;

    /**
     * Interact with the Login-interface.
     * <p> The Main components of the Login-interface are:
     * <ul>
     *  <li> A block for the user's email address
     *  <li> A block for the password
     *  <li> A button to finish logging in the system
     * </ul>
     * @param savedInstanceState Most up-to-date data of the LoginActivity
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = findViewById(R.id.login_emailEditText);
        passwordET = findViewById(R.id.login_editTextPassword);
        loginBtn = findViewById(R.id.login_button);

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

    /**
     * Perform the authentication.
     * <p>
     *     If the user has provided correct information, he/she will be directed to the system's main-interface where he/she can read and post stuffs
     * <p>
     *     If the user has provided false information, the login process will be failed and he/she will stay in the Login-Interface until he/she
     *     provides correct information
     *
     * @param email The email address of the user
     * @param pass  A Password
     */

    private void loginUser(String email, String pass) {
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "WELCOME TO FRA4RUM", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, PostActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "PLEASE TRY AGAIN!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}