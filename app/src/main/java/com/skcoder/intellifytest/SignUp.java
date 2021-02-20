package com.skcoder.intellifytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    EditText email,password,password2;
    Button sigupBtn;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.setTitle("SignUp");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.signup_progressbar);
        email = (EditText) findViewById(R.id.signup_Email);
        password = (EditText) findViewById(R.id.signup_Password);
        password2 = (EditText) findViewById(R.id.signup2_Password);
        sigupBtn = findViewById(R.id.SignupBtn);

        if (mAuth.getCurrentUser() != null){
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            finish();
        }

        sigupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString().trim();
                String Pass = password.getText().toString().trim();
                String Pass2 = password2.getText().toString().trim();

                if (TextUtils.isEmpty(Email))
                {
                    email.setError("Email is required.");
                    email.requestFocus();
                }else if (TextUtils.isEmpty(Pass)){
                    password.setError("Password is required.");
                    password.requestFocus();
                }else if (TextUtils.isEmpty(Pass2)){
                    password2.setError("Password is required.");
                    password2.requestFocus();
                }else if (Pass.length()!=Pass2.length()){
                    Toast.makeText(SignUp.this, "Password not match!!", Toast.LENGTH_SHORT).show();
                }else{
                    progressBar.setVisibility(View.VISIBLE);

                    mAuth.createUserWithEmailAndPassword(Email,Pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(getApplicationContext(), Home.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(SignUp.this, "User created.", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(SignUp.this, "Error :"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                }
            }
        });


    }
}