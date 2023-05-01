package com.example.infraspect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminScreen extends AppCompatActivity {


    Button viewProject;
    Button addProject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);


        viewProject = findViewById(R.id.viewprojecta);

        viewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewProject();
            }
        });
    }
    public void openViewProject(){
        Intent intent = new Intent(this,AdminViewProject.class);
        startActivity(intent);



        addProject = findViewById(R.id.viewprojectsc);

        addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddProject();
            }
        });
    }
    public void openAddProject(){
        Intent intent = new Intent(this,AdminAddproject.class);
        startActivity(intent);



    }
}