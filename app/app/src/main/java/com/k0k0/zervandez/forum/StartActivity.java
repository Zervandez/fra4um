package com.k0k0.zervandez.forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Allows the user to login to the system or create a new account if they don't have one.
 * @author Thien Huong Le
 * @since 10.01.2021
 */

public class StartActivity extends AppCompatActivity {

    private Button loginBtn, registerBtn;
    private EditText emailET, passwordET;

    private FirebaseAuth firebaseAuth;

    /**
     * Interact with the Main-interface where the users can either log in to the system or create a new account.
     * <p> The Main components of the Main-interface are:
     * <ul>
     *  <li> A box for the user's email address
     *  <li> A box for the password
     *  <li> A button to finish logging in the system
     *  <li> A Sign-up button to create a new account
     * </ul>
     * @param savedInstanceState Most up-to-date data of the StartActivity
     */

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
            /**
             * Verify the information given by the user (email, password) after they click on the Login-button.
             * <p> If the users haven't filled the required information, they will get a notification
             * <p> If the users haven't filled the email block, they will get a notification
             * <p> If the user haven't filled the password block, they will get a notification
             * <p> If the two required information have been given, they will be passed to the loginUser(email, pass)-Method to perform the authentication
             * @param view the start view
             */
            @Override
            public void onClick(View view) {
                String emailStr = emailET.getText().toString().trim();
                String passStr = passwordET.getText().toString();

                if ( TextUtils.isEmpty(emailStr) && TextUtils.isEmpty(passStr) ) {
                    Toast.makeText(getApplicationContext(), "EMAIL AND PASSWORD ARE EMPTY", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(emailStr)) {
                    Toast.makeText(getApplicationContext(), "PLEASE ENTER YOUR EMAIL", Toast.LENGTH_LONG).show();
                } else if ( TextUtils.isEmpty(passStr) ) {
                    Toast.makeText(getApplicationContext(), "PLEASE ENTER YOUR PASSWORD", Toast.LENGTH_LONG).show();
                } else {
                    loginUser(emailStr, passStr);
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Direct the user to the RegisterView after they click on the sign-up button
             * @param view the start view
             */
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, RegisterActivity.class));
            }
        });

    }

    /**
     * Perform the authentication.
     * <p>
     *     If the users have provided correct information, they will be directed to the system's main-interface where they can read and post stuffs
     * <p>
     *     If the users have provided false information, the login process will be failed and they will stay in the Main-Interface until they
     *     provide correct information
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
                    startActivity(new Intent(StartActivity.this, PostActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "PLEASE TRY AGAIN!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
