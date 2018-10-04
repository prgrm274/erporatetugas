//package com.com.com.task;
//
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class MainActivity extends AppCompatActivity {
//
//    FirebaseDatabase databaseFirebase;
//    DatabaseReference users;
//
//    EditText usernameEdit, passwordEdit;
//    Button button;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        databaseFirebase = FirebaseDatabase.getInstance();
//        users = databaseFirebase.getReference("Users");
//
//        usernameEdit = findViewById(R.id.username);
//        passwordEdit = findViewById(R.id.password);
//        button = findViewById(R.id.buttonSignup);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Users u = new Users(
//                        usernameEdit.getText().toString(),
//                        passwordEdit.getText().toString()
//                );
//
//                users.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.child(u.getUsername()).exists()) {
//                            Toast.makeText(MainActivity.this, "Username exists!", Toast.LENGTH_SHORT).show();
//                        } else {
//                            users.child(u.getUsername()).setValue(u);
//                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        Toast.makeText(MainActivity.this, "onCancelled", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//    }
//}
