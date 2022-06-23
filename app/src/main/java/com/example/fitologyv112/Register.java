package com.example.fitologyv112;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mFullname,mEmail,mPassword;
    EditText mWeight, mHeight, mAge;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;

    Spinner spinnerGender;
    Spinner spinnerUniteWeight;
    Spinner spinnerUniteHeight;

    EditText mCalorieIntake, mInputWeight2;
    Spinner spinnerUniteWeight2;
    Spinner spinnerGainOrLose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullname = findViewById(R.id.FullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        mRegisterBtn = findViewById(R.id.registerButton);
        mLoginBtn = findViewById(R.id.loginText);

        mWeight = findViewById(R.id.inputWeight);
        spinnerUniteWeight = findViewById(R.id.spinnerUniteWeight);
        mHeight = findViewById(R.id.inputHeight);
        spinnerUniteHeight = findViewById(R.id.spinnerUniteHeight);
        mAge = findViewById(R.id.inputAge);
        spinnerGender = findViewById(R.id.spinnerGender);

        mCalorieIntake = findViewById(R.id.inputDailyCalorie);
        mInputWeight2 = findViewById(R.id.inputWeight2);
        spinnerUniteWeight2 = findViewById(R.id.spinnerUniteWeight2);
        spinnerGainOrLose = findViewById(R.id.spinnerGainOrLose);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Genders));
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);

        spinnerUniteWeight = (Spinner) findViewById(R.id.spinnerUniteWeight);
        ArrayAdapter<String> weightAdapter = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.WeightUnite));
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUniteWeight.setAdapter(weightAdapter);

        spinnerUniteHeight = (Spinner) findViewById(R.id.spinnerUniteHeight);
        ArrayAdapter<String> heightAdapter = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.HeightUnite));
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUniteHeight.setAdapter(heightAdapter);

        spinnerUniteWeight2 = (Spinner)findViewById(R.id.spinnerUniteWeight2);
        ArrayAdapter<String> weightAdapter2 = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.WeightUnite));
        weightAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUniteWeight2.setAdapter(weightAdapter2);

        spinnerGainOrLose = (Spinner)findViewById(R.id.spinnerGainOrLose);
        ArrayAdapter<String> gainOrLoseAdapter = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.LoseOrGain));
        gainOrLoseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGainOrLose.setAdapter(gainOrLoseAdapter);


        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String fullname = mFullname.getText().toString();
                final String Weight = mWeight.getText().toString().trim();
                final String uniteWeight = spinnerUniteWeight.getSelectedItem().toString().trim();
                final String Height = mHeight.getText().toString().trim();
                final String uniteHeight = spinnerUniteHeight.getSelectedItem().toString().trim();
                final String Age = mAge.getText().toString().trim();
                final String Gender = spinnerGender.getSelectedItem().toString().trim();
                final String DailyCalorie = mCalorieIntake.getText().toString().trim();
                final String WeightGoal = mInputWeight2.getText().toString().trim();
                final String uniteWeight2 = spinnerUniteWeight2.getSelectedItem().toString().trim();
                final String gainOrLose = spinnerGainOrLose.getSelectedItem().toString().trim();


                if (TextUtils.isEmpty(fullname)){
                    mFullname.setError("Fullname is required");
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required.");
                    return;
                }
                if (password.length() < 6){
                    mPassword.setError("Password Must be >= 6 Characters");
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);

                 //register the user in the firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this,"User Created", Toast.LENGTH_SHORT).show();
                            //Storing the Username in firestore
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName", fullname);
                            user.put("email", email);
                            user.put("Weight", Weight);
                            user.put("uniteWeight", uniteWeight);
                            user.put("Height", Height);
                            user.put("uniteHeight", uniteHeight);
                            user.put("Age", Age);
                            user.put("Gender", Gender);
                            user.put("DailyCalorieIntake", DailyCalorie);
                            user.put("WeightGoal", WeightGoal);
                            user.put("uniteWeight2", uniteWeight2);
                            user.put("gainOrLose", gainOrLose);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user profile is created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent (getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Register.this,"Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
       });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
}