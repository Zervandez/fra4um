package com.k0k0.zervandez.forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;       // apparently, we're old fashioned
import com.google.android.material.snackbar.Snackbar;                               // we wear it with pride, baby
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class FeedActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private ListView feedListView;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        auth = FirebaseAuth.getInstance();


        feedListView = findViewById(R.id.feelListView);
        FloatingActionButton fab = findViewById(R.id.fab);

        Query query = FirebaseDatabase.getInstance().getReference().child("Feed");

        FirebaseListOptions<Post> options = new FirebaseListOptions.Builder<Post>()
                .setLayout(R.layout.custom_row)
                .setLifecycleOwner(FeedActivity.this)
                .setQuery(query, Post.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView txtPost = v.findViewById(R.id.customRowTextView);


                Post post = (Post) model;
                txtPost.setText(post.getPostText().toString());
            }
        };

        feedListView.setAdapter(adapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Speak Socrates!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(FeedActivity.this, PostActivity.class));
            }
        });

        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                auth.signOut();
                Toast.makeText(getApplicationContext(), "LOGGED OUT", Toast.LENGTH_LONG).show();
                startActivity(new Intent(FeedActivity.this, StartActivity.class));
                finish();
                return false;
            }

        });

        /*
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Feed");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    list.add(dataSnapshot.getValue().toString());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}



