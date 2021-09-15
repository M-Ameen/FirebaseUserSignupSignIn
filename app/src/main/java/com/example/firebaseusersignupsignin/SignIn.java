package com.example.firebaseusersignupsignin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    TextInputLayout t1,t2;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        t1=findViewById(R.id.email_login);
        t2=findViewById(R.id.pwd_login);
        progressBar=findViewById(R.id.progressBar_login);



    }

    public void signinbuttonclicked(View view) {
        progressBar.setVisibility(View.VISIBLE);
        String email=t1.getEditText().getText().toString();
        String password=t2.getEditText().getText().toString();

        mAuth=FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressBar.setVisibility(View.INVISIBLE);
                            t1.getEditText().setText("");
                            t2.getEditText().setText("");
                            Toast.makeText(SignIn.this, "SignIn Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(SignIn.this,Dashboard.class);
                            intent.putExtra("email",mAuth.getCurrentUser().getEmail());
                            intent.putExtra("uid",mAuth.getCurrentUser().getUid());
                            startActivity(intent);

                        }
                        else {
                            progressBar.setVisibility(View.INVISIBLE);
                            t1.getEditText().setText("");
                            t2.getEditText().setText("");
                            Toast.makeText(SignIn.this, "Invalid email/password", Toast.LENGTH_SHORT).show();
                        }

                    }
                });



    }
}