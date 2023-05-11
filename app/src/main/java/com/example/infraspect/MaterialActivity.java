package com.example.infraspect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MaterialActivity extends AppCompatActivity {

    EditText data;
    ProgressDialog progressDialog;
    Button dataAdd;
    EditText pname;
    private FirebaseDatabase database;
    MaterialModel materialDetails;
//    private FirebaseStorage firebaseStorage;

    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        data= findViewById(R.id.datadetails);
        dataAdd=findViewById(R.id.adddata);
        progressDialog = new ProgressDialog(this);
        pname= findViewById(R.id.projectnameformaterial);


        database = FirebaseDatabase.getInstance();
//        firebaseStorage = FirebaseStorage.getInstance();
        databaseReference=database.getReference("Material");

        materialDetails = new MaterialModel();

        dataAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataaaa = data.getText().toString();
                String pnameformaterial = pname.getText().toString();


                if(!dataaaa.isEmpty()&&pnameformaterial.isEmpty()){
                    progressDialog.setTitle("inserting Data...");
                    progressDialog.show();

                } else{
                    addDataToFirebase(dataaaa,pnameformaterial);
                }
            }
        });

    }

    private void addDataToFirebase(String dataaaa,String pnameformaterial) {

        materialDetails.setdata(dataaaa);
        materialDetails.setdata(pnameformaterial);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                databaseReference.setValue(project.getProjectName());
//                databaseReference.child(project.getProjectName()).setValue(project);

                FirebaseDatabase.getInstance().getReference("Materials")

                        .child(materialDetails.getPnameformaterial())
                        .setValue(materialDetails);

                Toast.makeText(MaterialActivity.this, "data added", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MaterialActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();

            }
        });
    }
}