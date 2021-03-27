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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Allows the user to create an account via their email address.
 *
 * @author Ariel Halilaj
 * @since  10.12.2020
 *
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText emailET, passET;
    private Button registerBtn, quitBtn;

    private FirebaseAuth firebaseAuth;     //  we declare an instance of Firebase Auth
    private FirebaseUser newUser;
    private DatabaseReference ref;
    private String emailStr, passStr;

    /**
     * Interact with the Register-interface.
     * <p> The Main components of the Register-interface are:
     * <ul>
     *  <li> A block for the user's email address
     *  <li> A block for the password
     *  <li> A button to finish the registration
     * </ul>
     * @param savedInstanceState Most up-to-date data of the RegisterActivity
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailET = findViewById(R.id.reg_emailEditText);
        passET = findViewById(R.id.reg_editTextPassword);
        registerBtn = findViewById(R.id.register_button);
        quitBtn = findViewById(R.id.quit_button);

        firebaseAuth = FirebaseAuth.getInstance();      // we initialise the instance

        registerBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Get the required information from the user (email, password).
             * <p> If the users haven't filled the email block, they will get a notification
             * <p> If the user haven't filled the password block, they will get a notification
             * <p> If the two required information have been given, they will be passed to the registerUser(email, pass)-Method to perform the authentication
             * @param view the register view
             */
            @Override
            public void onClick(View view) {
                emailStr = emailET.getText().toString().trim();
                passStr = passET.getText().toString();
                if ( TextUtils.isEmpty(emailStr) && TextUtils.isEmpty(passStr) ) {
                    Toast.makeText(getApplicationContext(), "EMAIL AND PASSWORD ARE EMPTY", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(emailStr)) {
                    Toast.makeText(getApplicationContext(), "PLEASE ENTER YOUR EMAIL", Toast.LENGTH_LONG).show();
                } else if ( TextUtils.isEmpty(passStr) ) {
                    Toast.makeText(getApplicationContext(), "PLEASE ENTER YOUR PASSWORD", Toast.LENGTH_LONG).show();
                } else {
                    registerUser(emailStr, passStr);
                }
            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, StartActivity.class));
            }
        });
    }

    /**
     * Perform the registration process.
     * <p>
     *     If the user has provided correct information, the registration process will be successful and he/she will be directed to the Login-interface
     *     so that he/she can login to the system
     * <p>
     *     If the user has provided false information, the registration process will be failed and he/she will stay in the Register-Interface until he/she
     *     provides correct information
     *
     * @param email The email address of the user
     * @param pass  A Password
     */

    private void registerUser(String email, String pass) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "SUCCESSFULLY CREATE AN ACCOUNT", Toast.LENGTH_LONG).show();

                    /*---------------Put a new user to the Users in the Database----------------*/
                    newUser = FirebaseAuth.getInstance().getCurrentUser();
                    String userID = newUser.getUid();
                    String userEmail = newUser.getEmail();
                    User user = new User(userID, userEmail,passStr);
                    ref = FirebaseDatabase.getInstance().getReference("Users");
                    ref.child(userID).setValue(user);
                    /*--------------------------------------------------------------------------*/
                    startActivity(new Intent(RegisterActivity.this, StartActivity.class));
                    finish();       // we type finish so the user can't come back by hitting 'back'
                }
                else Toast.makeText(getApplicationContext(), "PLEASE TRY AGAIN!", Toast.LENGTH_LONG).show();
            }
        });
    }
}