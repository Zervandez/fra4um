package com.k0k0.zervandez.forum;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Allows the user to write a post and then post it on the system
 *
 * @author Ariel Halilaj
 * @since 17.12.2020
 */

public class PostActivity extends AppCompatActivity {
    private EditText title, text;
    private String postTitle, postText;
    private ImageButton button;
    DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        title = findViewById(R.id.post_title);
        text = findViewById((R.id.post_text));

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.dateView);
        textViewDate.setText(currentDate);

        button = findViewById(R.id.postButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postTitle = title.getText().toString();
                postText = text.getText().toString();
                Post newPost = new Post(postText);
                firebaseDatabase.child("posts").push().setValue(newPost);
                Intent intent = new Intent(PostActivity.this, FeedActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

}