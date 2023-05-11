package com.example.infraspect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button adminButton;
    Button contractorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adminButton = (Button) findViewById(R.id.btnAdmin);


        //if click on Admin
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //method call
            public void onClick(View view) {
                openAdminAuth();
            }
        });
    }

    //method
    public void openAdminAuth(){
        Intent intent = new Intent(this,AdminAuth.class);
        startActivity(intent);


        contractorButton = (Button) findViewById(R.id.btnContractor);

        //if click on contractor
        contractorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //method call
            public void onClick(View view) {
                openContractorAuth();
            }
        });
    }
    //method
    public void openContractorAuth(){
        Intent intent = new Intent(this,ContractorAuth.class);
        startActivity(intent);
    }


}








