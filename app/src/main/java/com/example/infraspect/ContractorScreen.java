package com.example.infraspect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class ContractorScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_screen);


        Button viewProject;
        Button addPhoto;


        viewProject = findViewById(R.id.viewprojectsc);


        viewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewProject();
            }
        });
    }
    public void openViewProject(){
        Intent intent = new Intent(this, ContractorViewProject.class);
        startActivity(intent);

        View addPhoto = findViewById(R.id.addphoto);
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddPhoto();
            }
        });
    }

    public void openAddPhoto(){
        Intent intent = new Intent(this, ContractorAddPhotos.class);
        startActivity(intent);









    }
}