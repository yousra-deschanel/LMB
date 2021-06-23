package com.example.launchmybusiness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {
    Button login;
    Button creat;
    TextView forgotPass;
    EditText username;
    EditText password;
    String user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.button);
        creat = findViewById(R.id.button2);
        forgotPass = findViewById(R.id.forgot);

        username=(EditText)findViewById(R.id.usernamelog);
        password=(EditText)findViewById(R.id.passwordlog);

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpassword(v);
            }
        });

        //nlasa9ha to go to home page
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login(v);
            }
        });

        //nlasa9ha to go to sign up
        creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //displayguides();
                signup();
            }
        });

    }

    public void login(View view) {
        user=username.getText().toString();
        pass=password.getText().toString();
        if(user.isEmpty()){
            Toast.makeText(MainActivity.this,"Entrer une @ email",Toast.LENGTH_SHORT).show();
            return;

        }
        if(pass.isEmpty()){
            Toast.makeText(MainActivity.this,"Entrer un password",Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseAuth auth= FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(), homepage.class));
                    finish();

                }else
                    Toast.makeText(MainActivity.this,"Echec:"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

            }

        });
    }

    //rénitialiser le mot de passe
    public void resetpassword(View view) {
        user=username.getText().toString();
        if(user.isEmpty()){
            Toast.makeText(MainActivity.this,"Entrer une @ email",Toast.LENGTH_SHORT).show();
            return;

        }
        FirebaseAuth auth= FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Reset email envoyé avec succés, verifier votre email.",Toast.LENGTH_SHORT).show();

                }else
                    Toast.makeText(MainActivity.this,"Echec:"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();


            }
        });
    }


    /*
    public void displayguides() {
        Intent intent = new Intent(this, userlist.class);
        startActivity(intent);
    }

     */


    //go to sign up
    public void signup() {
        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }
}

