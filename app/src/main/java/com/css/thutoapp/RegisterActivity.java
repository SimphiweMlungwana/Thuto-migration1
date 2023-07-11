package com.css.thutoapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private EditText firstname, lastname, region, grade, email,passwrd;
    private Button signupbtn;
    private TextView logindirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        firstname = findViewById(R.id.editTextFirstName);
        lastname = findViewById(R.id.editTextLastName);
        grade = findViewById(R.id.grade);
        region = findViewById(R.id.Region);

        email = findViewById(R.id.TextEmail);
        passwrd = findViewById(R.id.TextPassword);

        logindirect = findViewById(R.id.guestlogin_txt);
        signupbtn = findViewById(R.id.buttonRegister);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email.getText().toString().trim();
                String pass = passwrd.getText().toString().trim();

                if(user.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Email empty", Toast.LENGTH_SHORT).show();
                } else if (pass.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Password empty", Toast.LENGTH_SHORT).show();
                }else {
                    auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                RegistrationDetails();
                                //toast

                            }else {
                                Toast.makeText(RegisterActivity.this, "Registration failed: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        logindirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,ActivityLogin.class));
            }
        });


    }

    private void RegistrationDetails() {
        String firstn = firstname.getText().toString().trim().toUpperCase();
        String lastn = lastname.getText().toString().trim().toUpperCase();
        String grde = grade.getText().toString().trim().toUpperCase();
        String rgn = region.getText().toString().trim().toUpperCase();

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("firstname", firstn);
        user.put("lastname", lastn);
        user.put("grade",grde);
        user.put("region",rgn);


// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RegisterActivity.this, "Registration successful ID: "+ documentReference.getId(), Toast.LENGTH_SHORT).show();
                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        startActivity(new Intent(RegisterActivity.this,ActivityLogin.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Registration failed: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        //Log.w(TAG, "Error adding document", e);
                    }
                });
    }
}