package com.myapplicationdev.android.rpreportfault;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;
import com.myapplicationdev.android.rpreportfault.R;

public class SignIn extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private Button Login;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Email = (EditText) findViewById(R.id.etEmail);
        Password = (EditText) findViewById(R.id.etPassword);
        Login = (Button) findViewById(R.id.btnLogin);
        forgotPassword = (TextView)findViewById(R.id.tvForgotPassword);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            finish();
            startActivity(new Intent(SignIn.this, MainPages.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Email.length()== 0 || Password.length()==0){
                    Toast.makeText(SignIn.this, "Please enter your email and password above", Toast.LENGTH_SHORT).show();
                }
                else {
                    validate(Email.getText().toString(), Password.getText().toString());
                }
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this, ForgetPassword.class));
            }
        });

    }

    private void validate(String userEmail, String userPassword) {

        progressDialog.setMessage("Loading");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    startActivity(new Intent(SignIn.this, MainPages.class));
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(SignIn.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }



    public void Forgetpassword(View view) {
        Intent intent = new Intent(this, ForgetPassword.class);
        startActivity(intent);
    }

    public void backarrow1(View view) {
        Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
    }


}