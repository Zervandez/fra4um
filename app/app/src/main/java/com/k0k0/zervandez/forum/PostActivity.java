package com.k0k0.zervandez.forum;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.Menu;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


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

        /*------------------Hooks------------------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        /*------------------Toolbar------------------*/
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /*------------------Hide the profile item------------------*/
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_post).setVisible(false);

        /*------------------Navigation View Item Selected----------*/
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_feed:
                        startActivity(new Intent(PostActivity.this, FeedActivity.class));
                        break;
                    case R.id.nav_profile:
                        startActivity(new Intent(PostActivity.this, Profile.class));
                        break;
                    case R.id.nav_logout:
                        startActivity(new Intent(PostActivity.this, StartActivity.class));
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

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