package com.example.infraspect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.material,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.item1){
            Intent intent = new Intent(AdminScreen.this,MaterialActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}