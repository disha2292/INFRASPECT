package com.example.infraspect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ContractorAuth extends AppCompatActivity {


    Button sendotp;
    Button signin;
    EditText phoneNo;
    EditText writeOtp;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    String codeSent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_auth);



        sendotp = findViewById(R.id.btnotp);
        signin = findViewById(R.id.btnsignin);
        phoneNo = findViewById(R.id.writenumber);
        writeOtp = findViewById(R.id.writeotp);

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String adminPhoneNo = phoneNo.getText().toString();
                PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber((adminPhoneNo))
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(ContractorAuth.this)
                        .setCallbacks(mcallbacks)
                        .build();

                PhoneAuthProvider.verifyPhoneNumber(options);

            }
        });

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

                            Intent intent = new Intent(ContractorAuth.this,ContractorScreen.class);
                            startActivity(intent);
                            finish();

                        }
                        else{

                            Toast.makeText(ContractorAuth.this, "Wrong Code", Toast.LENGTH_SHORT).show();

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






