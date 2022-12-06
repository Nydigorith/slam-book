package com.example.slambook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText etxtUsername, etxtPassword;
    Button btnLogin;
    TextView btnRegister;
    Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
    }

    private void initialize() {
        etxtUsername = findViewById(R.id.etxtUsername);
        etxtPassword = findViewById(R.id.etxtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password =etxtPassword.getText().toString();
                String username =etxtUsername.getText().toString();
                if (username.equals("") || password.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(c);
                    builder.setTitle("Attention").setMessage("All fields are required").setCancelable(false).setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Intent getRegistrationIntent = getIntent();
                    if (getRegistrationIntent.hasExtra("registrationFullName")  && getRegistrationIntent.hasExtra("registrationUsername") && getRegistrationIntent.hasExtra("registrationPassword") && getRegistrationIntent.hasExtra("registrationPicture")) {

                        String registrationFullName = getRegistrationIntent.getStringExtra("registrationFullName");
                        String registrationUsername = getRegistrationIntent.getStringExtra("registrationUsername");
                        String registrationPassword = getRegistrationIntent.getStringExtra("registrationPassword");
                        Bitmap registrationPicture = getRegistrationIntent.getParcelableExtra("registrationPicture");

                        //TEST DATA
//                        registrationUsername = "1";
//                        registrationPassword = "1";

                        if (username.equals(registrationUsername) && password.equals(registrationPassword)) {
                            Intent putLoginIntent = new Intent(c, EntryList.class);
                            putLoginIntent.putExtra("loginFullName", registrationFullName);
                            putLoginIntent.putExtra("loginPicture", registrationPicture);
                            startActivity(putLoginIntent);

                            Toast.makeText(c, "success", Toast.LENGTH_SHORT).show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(c);
                            builder.setTitle("Login")
                                    .setMessage("Username or Password is incorrect")
                                    .setCancelable(true)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(c);
                        builder.setTitle("Login")
                                .setMessage("Username or Password is incorrect")
                                .setCancelable(true)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }


            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("Register").setMessage("You will be redirected to Registration Page").setCancelable(false).setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        TEST DATA
                        Intent intent = new Intent(c, EntryList.class);
                        intent.putExtra("loginFullName", "Test Name");
    //                    intent.putExtra("loginPicture", BitmapFactory.decodeResource(c.getResources(), R.drawable.coffee1));

 //                       Intent intent = new Intent(c, Registration.class);
                        startActivity(intent);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }
}