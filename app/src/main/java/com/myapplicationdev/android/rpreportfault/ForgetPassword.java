package com.myapplicationdev.android.rpreportfault;

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
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {

    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        passwordEmail = (EditText)findViewById(R.id.etPasswordEmail);
        resetPassword = (Button)findViewById(R.id.btnPasswordReset);
        firebaseAuth = FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = passwordEmail.getText().toString().trim();

                if(useremail.equals("")) {
                    Toast.makeText(ForgetPassword.this, "Please enter your registered email ID", Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgetPassword.this, "Email sent! Please check your email. ", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ForgetPassword.this, SignIn.class));
                            }else{
                                Toast.makeText(ForgetPassword.this, "Email address not registered! ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }


    public void continuetologin(View view) {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }

    public void backarrow7(View view) {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }
}