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

public class SignIn extends AppCompatActivity {

    private EditText usernameIn, passwordIn;
    String username, password;
    private Button signIn, toSignUp;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_signin);

        usernameIn = findViewById(R.id.usernameIn);
        passwordIn = findViewById(R.id.passwordIn);
        signIn = findViewById(R.id.buttonSignIn);
        toSignUp = findViewById(R.id.toButtonSignUp);

        firebaseAuth = FirebaseAuth.getInstance();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameIn.getText().toString();
                password = passwordIn.getText().toString();
                if (valid()) {
                    firebaseAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        finish();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(SignIn.this, "Sign in sukses", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(SignIn.this, "username or password is incorrect", Toast.LENGTH_SHORT).show();
                                        usernameIn.setText("");
                                        passwordIn.setText("");
                                    }
                                }
                            });
                }
            }
        });

        toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });

    }

    private Boolean valid() {
        Boolean result = false;

        if (username.equals("") | password.equals("")) {
            Toast.makeText(this, "username and password must be filled", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }
}
