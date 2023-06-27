package com.example.infraspect;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImagesActivity extends AppCompatActivity {
    //    private RecyclerView mRecyclerView;
    RecyclerView recyclerView;
//    private ImageAdapter mAdapter;

    ImageAdapter adapter;
    private StorageReference mStorageRef;
    private ImageView imageviewupload;
    private ProgressBar mProgressCircle;
    private Uri mImageUri;
    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        recyclerView = findViewById(R.id.recycler_view);
        FirebaseDatabase firebaseDatabase
                = FirebaseDatabase.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle = findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();
        imageviewupload = findViewById(R.id.imageviewupload);

        adapter = new ImageAdapter(ImagesActivity.this, mUploads);

//     adapter = new MyAdapter(this,list);
        recyclerView.setAdapter(adapter);

       mDatabaseRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {

               // getting a DataSnapshot for the
               // location at the specified relative
               // path and getting in the link variable
               for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                   Map<String, Object> map = (Map<String, Object>) childSnapshot.getValue();
                   String link = String.valueOf(map.get("link"));
//               Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
//               Log.d(TAG, "Value is: " + map);

               Picasso.get().load(link).into(imageviewupload);
           }

               // loading that data into rImage
               // variable which is ImageView



       }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {
               Log.w(TAG, "Failed to read value.", error.toException());
           }

           });





    }
}