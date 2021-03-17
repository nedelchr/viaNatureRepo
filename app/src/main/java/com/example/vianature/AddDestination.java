package com.example.vianature;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AddDestination extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener  {


    private TextView registerDestination;
    private EditText editTextTitle, editTextDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_destination);

        registerDestination = (Button) findViewById(R.id.registerUser);
        registerDestination.setOnClickListener(this);
        editTextTitle = (EditText) findViewById(R.id.destTitle);
        editTextDescription = (EditText) findViewById(R.id.destDescription);

        Spinner spinnerRegion = (Spinner) findViewById(R.id.spinner_region);
        Spinner spinnerType = (Spinner) findViewById(R.id.spinner_type);
        Spinner spinnerHardness = (Spinner) findViewById(R.id.spinner_hardness);


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.region, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.hardness, android.R.layout.simple_spinner_item);


        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerRegion.setAdapter(adapter1);
        spinnerType.setAdapter(adapter2);
        spinnerHardness.setAdapter(adapter3);

        spinnerRegion.setOnItemSelectedListener(this);
        spinnerType.setOnItemSelectedListener(this);
        spinnerHardness.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerUser:
                registerDestination();
                break;
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String textRegion = parent.getItemAtPosition(position).toString();
        String textType = parent.getItemAtPosition(position).toString();
        String textHardness = parent.getItemAtPosition(position).toString();


        Toast.makeText(parent.getContext(), textRegion, Toast.LENGTH_SHORT).show();
        Toast.makeText(parent.getContext(), textType, Toast.LENGTH_SHORT).show();
        Toast.makeText(parent.getContext(), textHardness, Toast.LENGTH_SHORT).show();
    }


    private void registerDestination() {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();

        if (title.isEmpty()) {
            editTextTitle.setError("Title is required!");
            editTextTitle.requestFocus();
        }




    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}