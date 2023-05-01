package com.example.infraspect;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;

public class AdminAddproject extends AppCompatActivity {

    EditText projectName;
    EditText address;
    EditText startDate;
    EditText endDate;

    Button submitproject;

    ProjectModel project;

    ProgressDialog progressDialog;
    private FirebaseDatabase database;
//    private FirebaseStorage firebaseStorage;

    private DatabaseReference databaseReference;
//    DatabaseReference ref = database.getReference("server/saving-data/fireblog");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_project);

        database = FirebaseDatabase.getInstance();
//        firebaseStorage = FirebaseStorage.getInstance();
        databaseReference=database.getReference("projectModel");


        project = new ProjectModel();

        projectName = findViewById(R.id.projectname);
        address = findViewById(R.id.address);
        startDate = findViewById(R.id.startdate);
        endDate = findViewById(R.id.endDate);
        submitproject = findViewById(R.id.submitproject);






        progressDialog = new ProgressDialog(this);

        submitproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//         long projectModel= System.currentTimeMillis();
//
//         String identifier = "ProjectModel"+projectModel;

                String pn = projectName.getText().toString();
                String add = address.getText().toString();
                String date = startDate.getText().toString();
                String edate = endDate.getText().toString();

                if(!pn.isEmpty()&&add.isEmpty()&&date.isEmpty()&&edate.isEmpty()){
                    progressDialog.setTitle("inserting Data...");
                    progressDialog.show();

                } else{
                    addDataToFirebase(pn,add,date,edate);
                }

//                final StorageReference reference = firebaseStorage.getReference().child("project")
//                        .child(System.currentTimeMillis()+"");
            }
        });
    }

    private void addDataToFirebase(String pn, String add, String date, String edate) {

        project.setProjectName(pn);
        project.setAddress(add);
        project.setEndDate(edate);
        project.setStartDate(date);

//        databaseReference.addListenerForSingleValueEvent(new SingleValueEvent() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                databaseReference.push().setValue(project);
//                Toast.makeText(AdminAddproject.this, "data added", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//                Toast.makeText(AdminAddproject.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
//
//            }
//        });


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                databaseReference.setValue(project.getProjectName());
//                databaseReference.child(project.getProjectName()).setValue(project);

                FirebaseDatabase.getInstance().getReference("projectModel")

                        .child(project.getProjectName())
                        .setValue(project);

                Toast.makeText(AdminAddproject.this, "data added", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminAddproject.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();

            }
        });

//        databaseReference.push().setValue(project);


//        DataSnapshot dataSnapshot = null;
//        String proname = dataSnapshot.child(pn).child("projectName").getValue(String.class);
//        String addpro = dataSnapshot.child(add).child("phoneNo").getValue(String.class);
//        String stada = dataSnapshot.child(date).child("startingDate").getValue(String.class);
//        String endda = dataSnapshot.child(date).child("endingDate").getValue(String.class);



    }
}