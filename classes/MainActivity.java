package com.example.gymhelperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonOpenActivityPush;
    private Button buttonOpenActivityPull;
    private Button buttonOpenActivityLegs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.title_color)));

        buttonOpenActivityPush = (Button) findViewById(R.id.pushButton);
        buttonOpenActivityPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGymExercisesPushActivity();
            }
        });

        buttonOpenActivityPull = (Button) findViewById(R.id.pullButton);
        buttonOpenActivityPull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGymExercisesPullActivity();
            }
        });

        buttonOpenActivityLegs = (Button) findViewById(R.id.legsButton);
        buttonOpenActivityLegs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGymExercisesLegsActivity();
            }
        });


    }

    public void openGymExercisesPushActivity(){
        Intent intent = new Intent(this, GymExercisesPushActivity.class);
        startActivity(intent);
    }
    public void openGymExercisesPullActivity(){
        Intent intent = new Intent(this, GymExercisesPullActivity.class);
        startActivity(intent);
    }
    public void openGymExercisesLegsActivity(){
        Intent intent = new Intent(this, GymExercisesLegsActivity.class);
        startActivity(intent);
    }
}