package com.example.fitologyv112;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class Profile extends AppCompatActivity {

    private ImageButton btnSettings;
    private Button btnHome;
    private Button btnCalories;
    private Button btnWeight;
    private Button btnProfile;
    private ImageButton btnStatsEdit;

    TextView outUsername, outWeight, uniteWeight, outHeight, uniteHeight, outAge, outGender;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnSettings = (ImageButton) findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSettings();
            }
        });

        btnHome = (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openHome();
            }
        });

        btnCalories = (Button) findViewById(R.id.btnCalories);
        btnCalories.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCalories();
            }
        });

        btnWeight = (Button) findViewById(R.id.btnWeight);
        btnWeight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openWeight();
            }
        });

        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openProfile();
            }
        });

        btnStatsEdit = (ImageButton) findViewById(R.id.btnStatsEdit);
        btnStatsEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatsEdit();
            }
        });


// getting the information from the firestore.

        outUsername = findViewById(R.id.outUsername);
        outWeight = findViewById(R.id.outWeight);
        uniteWeight = findViewById(R.id.uniteWeight);
        outHeight = findViewById(R.id.outHeight);
        uniteHeight = findViewById(R.id.uniteHeight);
        outAge = findViewById(R.id.outAge);
        outGender = findViewById(R.id.outGender);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                outUsername.setText(documentSnapshot.getString("fName"));
                outWeight.setText(documentSnapshot.getString("Weight"));
                uniteWeight.setText(documentSnapshot.getString("uniteWeight"));
                outHeight.setText(documentSnapshot.getString("Height"));
                uniteHeight.setText(documentSnapshot.getString("uniteHeight"));
                outAge.setText(documentSnapshot.getString("Age"));
                outGender.setText(documentSnapshot.getString("Gender"));
            }
        });
    }


    public void openSettings(){
        Intent intent = new Intent (this, Settings.class);
        startActivity(intent);
    }

    public void openHome(){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

    public void openCalories(){
        Intent intent = new Intent (this, Calories.class);
        startActivity(intent);
    }

    public void openWeight(){
        Intent intent = new Intent (this, Weight.class);
        startActivity(intent);
    }

    public void openProfile(){
        Intent intent = new Intent (this, Profile.class);
        startActivity(intent);
    }

    public void openStatsEdit(){
        Intent intent = new Intent (this, EditUserStats.class);
        startActivity(intent);
    }

}