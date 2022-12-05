package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
        if (getEntryListIntent.hasExtra("loginFullName")) {
            String loginFullName = getEntryListIntent.getStringExtra("loginFullName");
            Bitmap loginPicture = getEntryListIntent.getParcelableExtra("loginPicture");

            Intent addEntryIntent = new Intent(c, EntryList.class);
            addEntryIntent.putExtra("loginFullName", loginFullName);
            addEntryIntent.putExtra("loginPicture", loginPicture);
            startActivity(addEntryIntent);
        } else {
            Toast.makeText(c, "ds", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialize() {
        txtName = findViewById(R.id.txtName);
        txtRemark = findViewById(R.id.txtRemark);
        txtBirthday = findViewById(R.id.txtBirthday);
        txtGender = findViewById(R.id.txtGender);
        txtHobbies = findViewById(R.id.txtHobbies);
        btnBack = findViewById(R.id.btnBack);

        Intent getArrayListIntent = getIntent();
        if (getArrayListIntent.hasExtra("entryListRemark")) {

            String individualEntryFullName = getArrayListIntent.getStringExtra("entryListFullName");
            String individualEntryRemark = getArrayListIntent.getStringExtra("entryListRemark");
            String individualEntryGender = getArrayListIntent.getStringExtra("entryListGender");
            String individualEntryHobbies = getArrayListIntent.getStringExtra("entryListHobbies");
            String individualEntryBirthday = getArrayListIntent.getStringExtra("entryListBirthday");

            txtName.setText(individualEntryFullName);
            txtRemark.setText(individualEntryRemark);

            txtBirthday.setText(individualEntryBirthday);
            txtHobbies.setText(individualEntryHobbies);
            txtGender.setText(individualEntryGender);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent();
            }
        });
    }
}