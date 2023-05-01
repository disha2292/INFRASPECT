package com.example.infraspect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class AdminAuth extends AppCompatActivity {


    Button sendotp;
    Button signin;
    EditText phoneNo;
    EditText writeOtp;


    //get insstance of a class
    //.getInstance() function actually gets a string of url as an input parameter.
    FirebaseAuth auth = FirebaseAuth.getInstance();
    String codeSent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_auth);


        sendotp = findViewById(R.id.abtnotp);
        signin = findViewById(R.id.abtnsignin);
        phoneNo = findViewById(R.id.awritenumber);
        writeOtp = findViewById(R.id.awriteotp);


        //proceeds to send OTP to entered number when click on send otp

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String adminPhoneNo = phoneNo.getText().toString();
                PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber((adminPhoneNo))
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(AdminAuth.this)
                        .setCallbacks(mcallbacks)
                        .build();

                PhoneAuthProvider.verifyPhoneNumber(options);

            }
        });


        //let user sign in with phone number
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signWithPhoneCode();

            }
        });

    }

    public void signWithPhoneCode(){
        String enterUserCode = writeOtp.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent,enterUserCode);
        signInWithPhoneAuthCredential(credential);
    }

    public void signInWithPhoneAuthCredential(PhoneAuthCredential credential){
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Intent intent = new Intent(AdminAuth.this,AdminScreen.class);
                            startActivity(intent);
                            finish();

                        }
                        else{

                            Toast.makeText(AdminAuth.this, "Wrong Code", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {

                }

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);

                    codeSent=s;
                }
            };


}