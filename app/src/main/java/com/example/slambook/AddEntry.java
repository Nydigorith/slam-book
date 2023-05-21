package com.example.slambook;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddEntry extends AppCompatActivity {

    Context c = this;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView ivPicture;
    TextView tvBirthdate;
    EditText etxtName, etxtRemark, etxtOther,etxtHobbies;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale, rbOthers;

    String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    DatePickerDialog datePickerDialog;
    Button btnCancel, btnAddEntry, btnTakePicture;
    Bitmap addEntryBtmpPicture;

    Calendar currentDate = Calendar.getInstance();
    int year = currentDate.get(Calendar.YEAR);
    int month = currentDate.get(Calendar.MONTH);
    int day = currentDate.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        initialize();
        process();

    }


    private void initialize() {
        tvBirthdate = findViewById(R.id.tvBirthdate);
        etxtName = findViewById(R.id.etxtName);
        etxtRemark = findViewById(R.id.etxtRemark);
        btnCancel = findViewById(R.id.btnCancel);
        btnAddEntry = findViewById(R.id.btnAddEntry);
        etxtHobbies = findViewById(R.id.etxtHobbies);

        etxtOther = findViewById(R.id.etxtOther);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        rbOthers = findViewById(R.id.rbOthers);


        ivPicture = findViewById(R.id.ivPicture);
        btnTakePicture = findViewById(R.id.btnTakePicture);


        rbMale.setOnClickListener(getRadio);
        rbFemale.setOnClickListener(getRadio);
        rbOthers.setOnClickListener(getRadio);


    }

    private void process() {
        datePickerDialog = new DatePickerDialog(c, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datepicker, int year, int month, int dayofmonth) {
                tvBirthdate.setText(months[month] + " " + dayofmonth + ", " + year);
//                tvBirthdate.setText((month + 1) + "/" + dayofmonth + "/" + year);
            }
        }, year, month, day);

        tvBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        btnAddEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addEntryName = etxtName.getText().toString();
                String addEntryRemark = etxtRemark.getText().toString();
                String addEntryBirthdate = tvBirthdate.getText().toString();
                String addEntryHobbies = etxtHobbies.getText().toString();
                String addEntryGender = "";

                //radio button
                if(rbMale.isChecked()){
                    addEntryGender = "Male";
                }else if(rbFemale.isChecked()){
                    addEntryGender = "Female";
                }else if (rbOthers.isChecked()){
                    addEntryGender = etxtOther.getText().toString();
                }

                if ( addEntryName.equals("") || addEntryHobbies.equals("") || addEntryRemark.equals("") ||
                        addEntryBirthdate.equals("") || addEntryGender.equals("") || addEntryBirthdate.equals("Date of Birth")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(c);
                    builder.setTitle("Attention").setMessage("Answer all the required fields").setCancelable(false).setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {


                        Intent putAddEntryIntent = new Intent();

                        putAddEntryIntent.putExtra("addEntryBirthday", addEntryBirthdate);
                        putAddEntryIntent.putExtra("addEntryHobbies", addEntryHobbies);
                        putAddEntryIntent.putExtra("addEntryGender", addEntryGender);
                        putAddEntryIntent.putExtra("addEntryName", addEntryName);
                        putAddEntryIntent.putExtra("addEntryRemark", addEntryRemark);
                        putAddEntryIntent.putExtra("addEntryBtmpPicture", addEntryBtmpPicture);
                        setResult(RESULT_OK,putAddEntryIntent);
                        finish();

                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivPicture.setImageBitmap(imageBitmap);
            addEntryBtmpPicture = imageBitmap;
        }
    }

    View.OnClickListener getRadio = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (rbMale.isChecked()) {
                etxtOther.setVisibility(View.GONE);
                etxtOther.setText(null);
            } else if (rbFemale.isChecked()) {
                etxtOther.setVisibility(View.GONE);
                etxtOther.setText(null);
            } else if (rbOthers.isChecked()) {
                etxtOther.setVisibility(View.VISIBLE);
            }

        }
    };

}


