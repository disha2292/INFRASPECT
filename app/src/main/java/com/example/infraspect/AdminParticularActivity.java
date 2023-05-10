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

public class AdminParticularActivity extends AppCompatActivity {


    EditText editText;
    ProgressDialog progressDialog;
    private FirebaseDatabase database;
//    private FirebaseStorage firebaseStorage;

    private DatabaseReference databaseReference;
    Button button;
    ParticularProjectModel particularProject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_particular);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        database = FirebaseDatabase.getInstance();
//        firebaseStorage = FirebaseStorage.getInstance();
        databaseReference=database.getReference("Tasks");

        particularProject = new ParticularProjectModel();

        progressDialog = new ProgressDialog(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = editText.getText().toString();

                if(!task.isEmpty()){
                    progressDialog.setTitle("inserting Data...");
                    progressDialog.show();

                } else{
                    addDataToFirebase(task);
                }
            }
        });


    }

    private void addDataToFirebase(String task) {


        particularProject.setEdittext(task);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                databaseReference.setValue(project.getProjectName());
//                databaseReference.child(project.getProjectName()).setValue(project);

                FirebaseDatabase.getInstance().getReference("Tasks")

                        .child(particularProject.getEdittext())
                        .setValue(particularProject);

                Toast.makeText(AdminParticularActivity.this, "data added", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminParticularActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();

            }
        });
    }


}