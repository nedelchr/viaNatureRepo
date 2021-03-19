package com.example.vianature;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AddDestination extends AppCompatActivity implements  View.OnClickListener {


    private TextView registerDestination;
    private EditText editTextTitle, editTextDescription;
    private ImageView imageView;
    private String editImageUrl;
    private StorageReference storageReference;
    private static final int PICK_IMAGE_REQUEST = 1;

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
        imageView = findViewById(R.id.image);
        studentDbRef = FirebaseDatabase.getInstance().getReference().child("Destination");
        storageReference = FirebaseStorage.getInstance().getReference("imageFolder");
        spinnerRegion = (Spinner) findViewById(R.id.spinner_region);
        spinnerType = (Spinner) findViewById(R.id.spinner_type);
        spinnerHardness = (Spinner) findViewById(R.id.spinner_hardness);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {

            Toast.makeText(AddDestination.this, "Choose Image Completed!", Toast.LENGTH_SHORT).show();
            Uri mImageUri = data.getData();
//
            Picasso.get().load(mImageUri).into(imageView);

            final StorageReference Imagename = storageReference.child("image" + mImageUri.getLastPathSegment());

            Imagename.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            editImageUrl = String.valueOf(uri);

                        }
                    });
                }
            });
        }
    }



    private void registerDestination() {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String region = spinnerRegion.getSelectedItem().toString();
        String type = spinnerType.getSelectedItem().toString();
        String hardness = spinnerHardness.getSelectedItem().toString();
        String getImageUrl = editImageUrl;
        Destination destination = new Destination(title, description, region, type, hardness, "Someone", getImageUrl);

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