package com.example.gymhelperapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

public class InputPictureAndExName extends AppCompatActivity {

    TextView textView;
    TextInputEditText textInputEditText;
    Button buttonSubmit;
    ImageView imageView;
    Uri uri = Uri.parse("D:\\OOP\\OOP_Project\\app\\src\\main\\res\\drawable\\missingphoto.png");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_picture_and_ex_name);


        textView = (TextView) findViewById(R.id.textInputExercise);
        textInputEditText = (TextInputEditText) findViewById(R.id.inputExerciseName);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        imageView = (ImageView) findViewById(R.id.imageExercise);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("imagine", "imaginea se apasa");
                ImagePicker.Companion.with(InputPictureAndExName.this)
                        //.crop()
                       // .maxResultSize(1080, 1080)
                        //.galleryOnly()
                        .start();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                try{
                    String exerciseName = textInputEditText.getText().toString();

                    if(!exerciseName.equals("")){

                        Intent intent = new Intent();

                        intent.putExtra("imageFromInput", uri.toString());
                        intent.putExtra("textFromInput", exerciseName);
                        Log.i("imagine", "URI-ul pozei bagate :: " + uri.toString() + "si textul :: " + exerciseName);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                    else
                        throw new EmptyTextException();
                }catch (EmptyTextException e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){

        super.onActivityResult(requestCode, resultCode, data);
        uri = data.getData();
        imageView.setImageURI(uri);
    }
}