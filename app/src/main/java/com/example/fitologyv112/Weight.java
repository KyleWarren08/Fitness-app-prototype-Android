package com.example.fitologyv112;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Weight extends AppCompatActivity {

    private ImageButton btnSettings;
    private Button btnHome;
    private Button btnCalories;
    private Button btnWeight;
    private Button btnProfile;

    ListView WeightTracker;
    ArrayList<String> list;

    Button btnAdd;
    EditText txtEditAdd;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

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

        WeightTracker = (ListView) findViewById(R.id.WeightTracker);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        txtEditAdd = (EditText) findViewById(R.id.txtEditAdd);

        list = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, list);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String WeightEntry = txtEditAdd.getText().toString();

                list.add(WeightEntry);
                WeightTracker.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
            }
        });




    }

    public void openSettings() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void openHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openCalories() {
        Intent intent = new Intent(this, Calories.class);
        startActivity(intent);
    }

    public void openWeight() {
        Intent intent = new Intent(this, Weight.class);
        startActivity(intent);
    }

    public void openProfile() {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

}