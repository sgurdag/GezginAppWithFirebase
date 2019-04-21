package com.sagu1.notebookwithfirebase.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sagu1.notebookwithfirebase.R;

public class LoginActivity extends AppCompatActivity {

    EditText usernameET;
    EditText passwordET;

    private FirebaseAuth mAuth;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);

        ((Button)findViewById(R.id.registerBTN)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
           ((Button)findViewById(R.id.loginBTN)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isValid()){

                    login(usernameET.getText().toString(),passwordET.getText().toString());
                }

            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            proceedToMainActivity();
        }
    }

    private void login(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                proceedToMainActivity();
            }
        });

    }

    private void proceedToMainActivity() {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }

    private boolean isValid() {

        if (usernameET.getText().toString().length() == 0 ){
            Toast.makeText(this, "Kullanıcı Adı Giriniz", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (passwordET.getText().toString().length() == 0 ){
            Toast.makeText(this, "Şifre Giriniz", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
