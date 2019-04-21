package com.sagu1.notebookwithfirebase.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sagu1.notebookwithfirebase.R;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameET;
    EditText passwordET;
    EditText passwordAgainET;
    private FirebaseAuth mAuth;
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();


        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        passwordAgainET = findViewById(R.id.passwordAgainET);

        ((Button) findViewById(R.id.registerBTN)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()){

                    register(usernameET.getText().toString(),passwordET.getText().toString());
                }
            }
        });

    }

    private void register(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Toast.makeText(RegisterActivity.this, "Giriş Başarılı",
                                    Toast.LENGTH_SHORT).show();

                            RegisterActivity.this.finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }

    private boolean isValid() {

        if (usernameET.getText().toString().length() == 0 ){
            Toast.makeText(this, "Kullanıcı Adı Giriniz", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (passwordET.getText().toString().length() == 0 ){
            Toast.makeText(this, "Şifre Giriniz", Toast.LENGTH_SHORT).show();
            return false;
        } else if (passwordAgainET.getText().toString().length() == 0 ){
            Toast.makeText(this, "Şifreyi Tekrar Giriniz", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!passwordAgainET.getText().toString().equals(passwordET.getText().toString())){
            Toast.makeText(this, "Şifreler Uyuşmuyor", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
