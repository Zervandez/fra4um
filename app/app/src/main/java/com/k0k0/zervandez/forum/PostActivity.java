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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Allows the user to write a post and then post it on the system
 *
 * @author Ariel Halilaj
 * @since 17.12.2020
 */

public class PostActivity extends AppCompatActivity {

    private EditText postEtx;
    private Button postBtn;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        firebaseDatabase = FirebaseDatabase.getInstance();

        postEtx = findViewById(R.id.postTextMultiLine);
        postBtn = findViewById(R.id.postBtn);

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt = postEtx.getText().toString();
                if (TextUtils.isEmpty(postEtx.toString())){
                    Toast.makeText(getApplicationContext(), "EMPTY NO GOOD", Toast.LENGTH_LONG).show();
                } else {
                    firebaseDatabase.getReference().child("Feed").push().setValue(txt);
                    startActivity(new Intent(PostActivity.this, FeedActivity.class));
                    finish();
                }
            }
        });
    }
}