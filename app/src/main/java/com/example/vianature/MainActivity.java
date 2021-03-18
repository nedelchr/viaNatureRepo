package com.example.vianature;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity  {

    public FirebaseAuth firebaseAuth;
    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";
    public TextView pressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        pressed = (TextView) findViewById(R.id.addDest);
        pressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddDestination.class));
            }
        });
        //pressed.setOnClickListener(this);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

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
                            //startActivity(new Intent(getApplicationContext(), LoginUser.class));
                            startActivity(new Intent(getApplicationContext(), ResultActivity.class));

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
                               // startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                                startActivity(new Intent(getApplicationContext(), ResultActivity.class));
                                overridePendingTransition(0,0);
                                return true;
                        }
                        return false;
                    }
            }
        });


    }

    /*
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String username = currentUser.getDisplayName();
        if(currentUser != null){
            currentUser.reload();
            Log.i("TAG", "Start check currentUser != null--------------------------------------------------------------");
            Log.i("TAG", username);
            Log.i("TAG", "Finish check currentUser != null--------------------------------------------------------------");
        } else
        {
            Log.i("TAG", "not user found");
            Log.i("TAG", username);
        }
    }

     */
}