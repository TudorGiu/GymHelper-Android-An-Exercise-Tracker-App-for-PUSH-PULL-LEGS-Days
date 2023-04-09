package com.example.gymhelperapp;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GymExercisesPushActivity extends AppCompatActivity {

    List<String> exerciseList = new ArrayList<>();
    List<String> exerciseImages = new ArrayList<>();

    ListView listView;

    private Button buttonAddExercise;
    private Button buttonRemoveLast;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            updateLists(result);
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_exercises_push);
        getSupportActionBar().setTitle("Push");


        loadData();

        listView = (ListView) findViewById(R.id.exercisesListView);



        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), exerciseList, exerciseImages);
        listView.setAdapter(customBaseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.i("LIST_CELL", "Item is clicked @ Position :: " + position);
            }
        });



        buttonAddExercise = (Button) findViewById(R.id.addExercise);
        buttonAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GymExercisesPushActivity.this, InputPictureAndExName.class);
                activityResultLauncher.launch(intent);
            }
        });

        buttonRemoveLast = (Button) findViewById(R.id.removeLastExercise);
        buttonRemoveLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(exerciseList.size() > 0 && exerciseImages.size() > 0){
                        exerciseList.remove(exerciseList.size() - 1);
                        exerciseImages.remove(exerciseImages.size() - 1);
                    }
                    else
                        throw new NoMoreElementsException();

                    CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), exerciseList, exerciseImages);
                    listView.setAdapter(customBaseAdapter);

                    saveData();
                }catch (NoMoreElementsException e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void updateLists(ActivityResult result){

        Intent intent = result.getData();


        String imageFromInput = intent.getStringExtra("imageFromInput");
        String textFromInput = intent.getStringExtra("textFromInput");

        Log.i("imagine", "Imaginea :: " +imageFromInput + " si textul :: " + textFromInput);
        if(imageFromInput != null){
            Uri imageFromInputURI = Uri.parse(imageFromInput);

            String stringFromUri = imageFromInput.toString();

            exerciseList.add(textFromInput);
            exerciseImages.add(stringFromUri);

            CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), exerciseList, exerciseImages);
            listView.setAdapter(customBaseAdapter);

            saveData();
        }
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json1 = gson.toJson(exerciseList);
        String json2 = gson.toJson(exerciseImages);
        editor.putString("exerciseListPush", json1);
        editor.putString("exerciseImagesPush", json2);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json1 = sharedPreferences.getString("exerciseListPush", null);
        String json2 = sharedPreferences.getString("exerciseImagesPush", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        exerciseList = gson.fromJson(json1, type);
        exerciseImages = gson.fromJson(json2, type);

        if(exerciseList == null){
            exerciseList = new ArrayList<>();
        }

        if(exerciseImages == null){
            exerciseImages = new ArrayList<>();
        }
    }

}
