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

public class MainActivity extends AppCompatActivity {

private ImageButton btnSettings;
private Button btnHome;
private Button btnCalories;
private Button btnWeight;
private Button btnProfile;

TextView outDailyCalorieIntake, outWeight2, outUniteWeight2, outLoseOrGain;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        outDailyCalorieIntake = findViewById(R.id.outDailyCalorieIntake);
        outWeight2 = findViewById(R.id.outWeight2);
        outUniteWeight2 = findViewById(R.id.outUniteWeight2);
        outLoseOrGain = findViewById(R.id.outLoseOrGain);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                outDailyCalorieIntake.setText(documentSnapshot.getString("DailyCalorieIntake"));
                outWeight2.setText(documentSnapshot.getString("WeightGoal"));
                outUniteWeight2.setText(documentSnapshot.getString("uniteWeight2"));
                outLoseOrGain.setText(documentSnapshot.getString("gainOrLose"));
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

}