package com.example.fitologyv112;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Calories extends AppCompatActivity {

    private ImageButton btnSettings;
    private Button btnHome;
    private Button btnCalories;
    private Button btnWeight;
    private Button btnProfile;

    ImageButton btnCapture;
    ImageButton btnCapture2;
    ImageButton btnCapture3;

    ImageView FoodPicture;
    ImageView FoodPicture2;
    ImageView FoodPicture3;


    //private static final int CAN_REQUEST = 1313;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);

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

        btnCapture = (ImageButton) findViewById(R.id.btnCapture);
        FoodPicture = (ImageView) findViewById(R.id.FoodPicture);
        btnCapture.setOnClickListener(new btnTakePhotoClicker());

        btnCapture2 = (ImageButton) findViewById(R.id.btnCapture2);
        FoodPicture2 = (ImageView) findViewById(R.id.foodPicture2);
        btnCapture2.setOnClickListener(new btnTakePhotoClicker2());

        btnCapture3 = (ImageButton) findViewById(R.id.btnCapture3);
        FoodPicture3 = (ImageView) findViewById(R.id.foodPicture3);
        btnCapture3.setOnClickListener(new btnTakePhotoClicker3());

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 ){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            FoodPicture.setImageBitmap(bitmap);
        }else if(requestCode == 2 ){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            FoodPicture2.setImageBitmap(bitmap);
        }else if(requestCode == 3){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            FoodPicture3.setImageBitmap(bitmap);
        }
    }


    class btnTakePhotoClicker implements Button.OnClickListener{

        @Override
        public void onClick(View view){
            Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,1);
        }
    }

    class btnTakePhotoClicker2 implements Button.OnClickListener{

        @Override
        public void onClick(View view){
            Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,2);
        }
    }

    class btnTakePhotoClicker3 implements Button.OnClickListener{

        @Override
        public void onClick(View view){
            Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,3);
        }
    }


}