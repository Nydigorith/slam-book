package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class IndividualEntry extends AppCompatActivity {
Context c = this;

    TextView txtName, txtRemark, txtBirthday, txtGender, txtHobbies;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_entry);
    initialize();

    }
    private void intent() {
        Intent getEntryListIntent = getIntent();
        if(getEntryListIntent.hasExtra("entryListFullName") ) {
//          && getEntryListIntent.hasExtra("entryListPicture")
            String entryListFullName = getEntryListIntent.getStringExtra("entryListFullName");
//            Bitmap entryListPicture = getEntryListIntent.getParcelableExtra("entryListPicture");

            Intent addEntryIntent = new Intent(c, EntryList.class);
            addEntryIntent.putExtra("loginFullName", entryListFullName);
//           addEntryIntent.putExtra("loginFullName", entryListPicture);

            startActivity(addEntryIntent);
        }
    }
    private void initialize() {
        txtName= findViewById(R.id.txtName);
        txtRemark= findViewById(R.id.txtRemark);
        txtBirthday= findViewById(R.id.txtBirthday);
                txtGender= findViewById(R.id.txtGender);
        txtHobbies = findViewById(R.id.txtHobbies);
        btnBack = findViewById(R.id.btnBack);

        Intent getArrayListIntent = getIntent();
        if(getArrayListIntent.hasExtra("entryListRemark")) {
            // && getLoginIntent.hasExtra("loginPicture")
            String individualEntryFullName = getArrayListIntent.getStringExtra("entryListFullName");
            String individualEntryRemark = getArrayListIntent.getStringExtra("entryListRemark");

            txtName.setText(individualEntryFullName);
            txtRemark.setText(individualEntryRemark);


        } else {
            Toast.makeText(this, "no tang", Toast.LENGTH_SHORT).show();
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent();
            }
        });
    }
}