package com.myapplicationdev.android.rpreportfault;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button onClickSignIn = (Button) findViewById(R.id.SignIn);
        Button onClickSignUp = (Button) findViewById(R.id.tvRegister);

        onClickSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, SignIn.class);
                startActivity(intent);
            }
        });


        onClickSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}


