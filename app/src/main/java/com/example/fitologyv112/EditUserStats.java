package com.example.fitologyv112;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchUIUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class EditUserStats extends AppCompatActivity {

    public static final String TAG = "TAG";
    private ImageButton btnSettings;
    private Button btnHome;
    private Button btnCalories;
    private Button btnWeight;
    private Button btnProfile;

    private Spinner spinnerGender;
    private Spinner spinnerUniteWeight;
    private Spinner spinnerUniteHeight;

    ImageButton btnSave;
    EditText mWeight, mHeight, mAge;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_stats);

        btnSettings = (ImageButton) findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });

        btnHome = (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });

        btnCalories = (Button) findViewById(R.id.btnCalories);
        btnCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalories();
            }
        });

        btnWeight = (Button) findViewById(R.id.btnWeight);
        btnWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeight();
            }
        });

        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });

        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(EditUserStats.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Genders));
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);

        spinnerUniteWeight = (Spinner) findViewById(R.id.spinnerUniteWeight);
        ArrayAdapter<String> weightAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.WeightUnite));
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUniteWeight.setAdapter(weightAdapter);

        spinnerUniteHeight = (Spinner) findViewById(R.id.spinnerUniteHeight);
        ArrayAdapter<String> heightAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.HeightUnite));
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUniteHeight.setAdapter(heightAdapter);

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