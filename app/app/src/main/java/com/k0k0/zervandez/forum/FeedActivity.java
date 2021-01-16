package com.k0k0.zervandez.forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;       // apparently, we're old fashioned
import com.google.android.material.snackbar.Snackbar;                               // we wear it with pride, baby
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



class CustomListRowAdapter<String> extends BaseAdapter {

    Context context;
    List data;
    private static LayoutInflater inflater = null;

    public CustomListRowAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        if (vi == null) inflater.inflate(R.layout.custom_row, null);
        TextView postText = vi.findViewById(R.id.customRowTextView);
        postText.setText(data.toString());
        return vi;

    }
}


public class FeedActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;

    private ListView feedListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        auth = FirebaseAuth.getInstance();


        feedListView = findViewById(R.id.feelListView);
        FloatingActionButton fab = findViewById(R.id.fab);

        final ArrayList<String> list = new ArrayList<>();
        //final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.custom_row, list);
        final CustomListRowAdapter<String> customListRowAdapter = new CustomListRowAdapter<String>(this, list);


        feedListView.setAdapter(customListRowAdapter);

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

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Feed");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    list.add(dataSnapshot.getValue().toString());
                }
                customListRowAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    }

