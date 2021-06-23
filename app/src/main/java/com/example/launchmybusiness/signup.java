package com.example.launchmybusiness;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
    Button btncon;
    EditText username;
    EditText password;
    String user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btncon=findViewById(R.id.button3);

        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        //nlasa9ha to go to home page
        btncon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createcompte(v);
            }
        });
    }

    //Créer un compte
    public void createcompte(View view) {
        user=username.getText().toString();
        pass=password.getText().toString();

        if(user.isEmpty()){
            Toast.makeText(signup.this,"Entrer un @ email",Toast.LENGTH_LONG).show();
            return;

        }
        if(pass.isEmpty()){
            Toast.makeText(signup.this,"Entrer un password",Toast.LENGTH_LONG).show();
            return;
        }
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signup.this,"Compte créer avec succés, "+"Login pour continuer",Toast.LENGTH_SHORT).show();

                }else
                    Toast.makeText(signup.this,"Echec:"+ task.getException().getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

    public void homepage(){
        Intent intent = new Intent(this, homepage.class);
        startActivity(intent);
    }


}