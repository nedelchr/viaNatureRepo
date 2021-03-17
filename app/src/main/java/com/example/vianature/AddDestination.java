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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AddDestination extends AppCompatActivity implements  View.OnClickListener {


    private TextView registerDestination;
    private EditText editTextTitle, editTextDescription;

   // private final Firestore db;

    DatabaseReference studentDbRef;

    private String regionGlobal, typeGlobal, hardnessGlobal;

    Spinner  spinnerRegion;
    Spinner spinnerType;
    Spinner spinnerHardness;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_destination);

        registerDestination = (Button) findViewById(R.id.registerDestination);
        registerDestination.setOnClickListener(this);
        editTextTitle = (EditText) findViewById(R.id.destTitle);
        editTextDescription = (EditText) findViewById(R.id.destDescription);

        studentDbRef = FirebaseDatabase.getInstance().getReference().child("Destination");

        spinnerRegion = (Spinner) findViewById(R.id.spinner_region);
        spinnerType = (Spinner) findViewById(R.id.spinner_type);
        spinnerHardness = (Spinner) findViewById(R.id.spinner_hardness);

        /*
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

         */
    }

/*
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String textRegion = parent.getItemAtPosition(position).toString();
        String textType = parent.getItemAtPosition(position).toString();
        String textHardness = parent.getItemAtPosition(position).toString();


        typeGlobal = textType;
        regionGlobal = textRegion;
        hardnessGlobal = textHardness;

        Toast.makeText(parent.getContext(), textRegion, Toast.LENGTH_SHORT).show();
        Toast.makeText(parent.getContext(), textType, Toast.LENGTH_SHORT).show();
        Toast.makeText(parent.getContext(), textHardness, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
 */






    private void registerDestination() {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String region = spinnerRegion.getSelectedItem().toString();
        String type = spinnerType.getSelectedItem().toString();
        String hardness = spinnerHardness.getSelectedItem().toString();
        Destination destination = new Destination(title, description, region, type, hardness, "Someone");

        studentDbRef.push().setValue(destination);
       Toast.makeText(AddDestination.this, "Data inserted", Toast.LENGTH_SHORT).show();

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerDestination:
                registerDestination();
                break;
        }
    }
}