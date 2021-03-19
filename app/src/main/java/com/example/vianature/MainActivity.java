package com.example.vianature;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.style.IconMarginSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity  {

    public FirebaseAuth firebaseAuth;
    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";
    public ImageView pressed;
    public RelativeLayout searchButton;
    private RecyclerView recyclerView;
    private ResultAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(MainActivity.this);
        mAuth = FirebaseAuth.getInstance();
        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ResultActivity.class));
            }
        });
        pressed = findViewById(R.id.addDest);
        pressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddDestination.class));
            }
        });
        //pressed.setOnClickListener(this);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);



        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,  LinearLayoutManager.VERTICAL, false));

        FirebaseRecyclerOptions<Destination> options =
                new FirebaseRecyclerOptions.Builder<Destination>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Destination"), Destination.class)
                        .build();
        adapter = new ResultAdapter(options, this);
        recyclerView.setAdapter(adapter);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(firebaseAuth.getInstance().getCurrentUser() == null)
                { switch (item.getItemId()) {
                    case R.id.addDest:
                        startActivity(new Intent(getApplicationContext(), AddDestination.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.favoutire:
                        Log.i("TAG", "not logged in");
                        startActivity(new Intent(getApplicationContext(), LoginUser.class));
                        overridePendingTransition(0, 0);
                        Toast.makeText(MainActivity.this, "Please log in first to be able to use Favourites", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.user:
                        Log.i("TAG", "not logged in");
                        startActivity(new Intent(getApplicationContext(), LoginUser.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                    return false;
                }else {
                    switch (item.getItemId()) {
                        case R.id.addDest:
                            startActivity(new Intent(getApplicationContext(), AddDestination.class));
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.home:
                            return true;
                        case R.id.favoutire:
                            Log.i("TAG","logged in");
                            startActivity(new Intent(getApplicationContext(), Favourite.class));
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.user:
                            Log.i("TAG","logged in");
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            overridePendingTransition(0,0);
                            return true;
                    }
                    return false;
                }
            }
        });


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