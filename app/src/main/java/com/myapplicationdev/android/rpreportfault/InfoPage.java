package com.myapplicationdev.android.rpreportfault;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;


public class InfoPage extends AppCompatActivity {

    private EditText etDesc, etLoc, etEmail;
    private Button btnSubmit;


    DatabaseReference databaseReference;
    UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);

        databaseReference = FirebaseDatabase.getInstance().getReference("Authorised User");

        etDesc = (EditText) findViewById(R.id.editTextDescription);
        etLoc = (EditText) findViewById(R.id.editTextLocation);
        etEmail = (EditText) findViewById(R.id.editTextEmail);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                confirmDialog();
            }
        });

    }

    public void addUserInput(){
        String userDesc = etDesc.getText().toString();
        String userLoc = etLoc.getText().toString();
        String userEmail = etEmail.getText().toString();

        if(!TextUtils.isEmpty(userDesc) && !TextUtils.isEmpty(userLoc) && !TextUtils.isEmpty(userEmail)){

            String id = databaseReference.push().getKey();

            UserInfo userInfo = new UserInfo(id,userDesc,userLoc,userEmail);

            databaseReference.child(id).setValue(userInfo);
            etDesc.setText("");
            etLoc.setText("");
            etEmail.setText("");

            Intent intent = new Intent(InfoPage.this, MainPages.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            Toast.makeText(this, "Report successfully submitted", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(InfoPage.this,"Please enter the required information",Toast.LENGTH_SHORT).show();
        }
    }
    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation Upon Submission");
        builder.setMessage("We believe you have provided all the information base on your experience and at the best of your knowledge. Are you sure you would like to submit it?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Firebase submit and finalise data
                addUserInput();

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Submission Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

    /*
    public void dialogEvent(View view){

        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Some codes here
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(InfoPage.this);
                alertDlg.setTitle("Confirmation Upon Submission");
                alertDlg.setMessage("We believe you have provided all the information base on your experience and at the best of your knowledge. Are you sure you would like to submit it?");
                alertDlg.setCancelable(false);
                alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Report successfully submitted", Toast.LENGTH_SHORT).show();
                        // Firebase submit and finalise data
                    }
                });

                alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Submission Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDlg.show();
            }
        });

    }
    */
}
