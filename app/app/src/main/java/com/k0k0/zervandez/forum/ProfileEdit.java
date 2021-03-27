package com.k0k0.zervandez.forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class ProfileEdit extends AppCompatActivity {

    private EditText username, name, birthday, password, phoneNumber;
    private TextView email;
    private String userID;
    private String usernameStr, nameStr, birthdayStr, passStr, phoneStr;

    Button saveBtn;
    FirebaseUser user;
    DatabaseReference ref;
    DataSnapshot snapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        /*------------Profile information------------*/
        username = findViewById(R.id.edit_username);
        name = findViewById(R.id.edit_name);
        birthday = findViewById(R.id.edit_birthday);
        password = findViewById(R.id.edit_password);
        email = findViewById(R.id.edit_email);
        phoneNumber = findViewById(R.id.edit_phone);

        /*------------------Buttons------------------*/
        saveBtn = findViewById(R.id.save_info);

        user = FirebaseAuth.getInstance().getCurrentUser();

        userID = user.getUid();
        ref = FirebaseDatabase.getInstance().getReference("Users").child(userID);

        final String emailStr = user.getEmail();
        email.setText(emailStr);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameStr = username.getText().toString();
                nameStr = name.getText().toString();
                birthdayStr = birthday.getText().toString();
                phoneStr = phoneNumber.getText().toString();
                passStr = password.getText().toString();

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User thisUser = snapshot.getValue(User.class);
                        User currentUser = new User(thisUser);

                        if (!usernameStr.isEmpty()) currentUser.setUsername(usernameStr);
                        if (!nameStr.isEmpty()) currentUser.setName(nameStr);
                        if (!birthdayStr.isEmpty()) currentUser.setBirthday(birthdayStr);
                        if (!phoneStr.isEmpty()) currentUser.setPhoneNumber(phoneStr);
                        if(!passStr.isEmpty()) {
                            currentUser.setPassword(passStr);
                            user.updatePassword(passStr);
                        }

                        ref.setValue(currentUser);
                        startActivity(new Intent(ProfileEdit.this, Profile.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Failed to change the data from the user's profile", Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
    }
}