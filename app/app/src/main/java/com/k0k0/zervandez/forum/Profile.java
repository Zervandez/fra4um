package com.k0k0.zervandez.forum;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    private TextView username, name, birthday, password, email, phoneNumber;
    private String usernameStr, nameStr, birthdayStr, phoneStr, emailStr;

    Button editBtn;
    ImageButton viewPostBtn;
    FirebaseUser user;
    DatabaseReference ref;
    DataSnapshot snapshot;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*------------Profile information------------*/
        username = findViewById(R.id.profile_username);
        name = findViewById(R.id.profile_name);
        birthday = findViewById(R.id.profile_birthday);
        password = findViewById(R.id.profile_password);
        email = findViewById(R.id.profile_email);
        phoneNumber = findViewById(R.id.profile_phone);

        /*------------------Buttons------------------*/
        editBtn = findViewById(R.id.editBtn);
        viewPostBtn = findViewById(R.id.profile_post);

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
        menu.findItem(R.id.nav_profile).setVisible(false);

        /*------------------Navigation View Item Selected----------*/
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_feed:
                        startActivity(new Intent(Profile.this, FeedActivity.class));
                        break;
                    case R.id.nav_post:
                        startActivity(new Intent(Profile.this, PostActivity.class));
                        break;
                    case R.id.nav_logout:
                        startActivity(new Intent(Profile.this, StartActivity.class));
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        /*------------------Profile configuration------------------*/
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        ref = FirebaseDatabase.getInstance().getReference("Users").child(userID);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User currentUser = snapshot.getValue(User.class);
                if(currentUser != null) {
                    username.setText(currentUser.getUsername());
                    name.setText(currentUser.getName());
                    birthday.setText(currentUser.getBirthday());
                    phoneNumber.setText(currentUser.getPhoneNumber());
                    email.setText(currentUser.getEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed to invoke the data from the user's profile", Toast.LENGTH_LONG).show();
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, ProfileEdit.class));
            }
        });


    }

}