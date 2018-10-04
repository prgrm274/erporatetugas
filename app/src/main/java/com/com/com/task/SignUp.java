package com.com.com.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private Button buttonSignUp, toButtonSignIn;
    private EditText nameEditText, usernameEditText, passwordEditText;

    FirebaseAuth firebaseAuth;
    String name, username, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSignUp = findViewById(R.id.buttonSignUp);
        toButtonSignIn = findViewById(R.id.toButtonSignIn);
        nameEditText = findViewById(R.id.name);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEditText.getText().toString();
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();

                if (valid()) {
                    firebaseAuth.createUserWithEmailAndPassword(username, password)
                            .addOnCompleteListener(
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(SignUp.this, "Sign Up sukses", Toast.LENGTH_SHORT).show();
                                                finish();
                                                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(SignUp.this, "Sign In error", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                            );
                }
            }
        });

        toButtonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
            }
        });

    }

    private Boolean valid() {
        Boolean result = false;

        if (name.equals("") | username.equals("") || password.equals("")) {
            Toast.makeText(this, "must be filled the fields", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }
}
