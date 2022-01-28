package com.example.projekt_sm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.projekt_sm.helpers.ImageHelperActivity;
import com.example.projekt_sm.image.FlowerClassificationActivity;
import com.example.projekt_sm.image.ImageClassificationActivity;
import com.example.projekt_sm.image.ObjectDetectionActivity;
import com.google.mlkit.vision.objects.ObjectDetection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onGotoImageActivity(View view){
        Intent intent = new Intent(this, ImageClassificationActivity.class);
        startActivity(intent);
    }

    public void onGotoFlowerClassification(View view){
        Intent intent = new Intent(this, FlowerClassificationActivity.class);
        startActivity(intent);
    }
    public void onGotoObjectDetection(View view){
        Intent intent = new Intent(this, ObjectDetectionActivity.class);
        startActivity(intent);
    }
}