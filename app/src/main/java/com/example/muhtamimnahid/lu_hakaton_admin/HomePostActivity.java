package com.example.muhtamimnahid.lu_hakaton_admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePostActivity extends AppCompatActivity {

    @BindView(R.id.productlist)
    ListView productlist;
    private DatabaseReference mDatabase;
    ArrayList<Posts> arrayOfproducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_post);
        ButterKnife.bind(this);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Posts");

        // My top posts by number of stars
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayOfproducts = new ArrayList<Posts>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Posts product = postSnapshot.getValue(Posts.class);
                    arrayOfproducts.add(product);
                }
                // Create the adapter to convert the array to views
                IdeaAdapter adapter = new IdeaAdapter(HomePostActivity.this, arrayOfproducts);
                // Attach the adapter to a ListView
                productlist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

}
