package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
public class EditEntry extends AppCompatActivity {

    Context c = this;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView ivPicture;
    TextView tvBirthdate;
    EditText etxtName, etxtRemark, etxtOther,etxtHobbies;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale, rbOthers;

    String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    DatePickerDialog datePickerDialog;
    Button btnCancel, btnEditEntry, btnTakePicture;
    Bitmap editEntryBtmpPicture;
String entryPosition ;
    Calendar currentDate = Calendar.getInstance();
    int year = currentDate.get(Calendar.YEAR);
    int month = currentDate.get(Calendar.MONTH);
    int day = currentDate.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);

        initialize();
        process();

    }

    private void initialize() {
        tvBirthdate = findViewById(R.id.tvBirthdate);
        etxtHobbies = findViewById(R.id.etxtHobbies);
        etxtName = findViewById(R.id.etxtName);
        etxtRemark = findViewById(R.id.etxtRemark);
        btnCancel = findViewById(R.id.btnCancel);
        btnEditEntry = findViewById(R.id.btnEditEntry);

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

//display data
        Intent getArrayListIntent = getIntent();
        if (getArrayListIntent.hasExtra("entryListFullName")) {

            String individualEntryFullName = getArrayListIntent.getStringExtra("entryListFullName");
            String individualEntryRemark = getArrayListIntent.getStringExtra("entryListRemark");
            String individualEntryGender = getArrayListIntent.getStringExtra("entryListGender");
            String individualEntryHobbies = getArrayListIntent.getStringExtra("entryListHobbies");
            String individualEntryBirthday = getArrayListIntent.getStringExtra("entryListBirthday");
            String entryListPosition = getArrayListIntent.getStringExtra("entryListPosition");
            entryPosition=entryListPosition;


            String pictureFilePath=getIntent().getStringExtra("entryListPicture");
            File file = new File(pictureFilePath);
            Bitmap individualEntryBtmpPicture = BitmapFactory.decodeFile(file.getAbsolutePath());

ivPicture.setImageBitmap(individualEntryBtmpPicture);
            etxtHobbies.setText(individualEntryHobbies);
            etxtName.setText(individualEntryFullName);
            etxtRemark.setText(individualEntryRemark);
            tvBirthdate.setText(individualEntryBirthday);

            if (individualEntryGender.equals("Male")) {
                rbMale.setChecked(true);
                etxtOther.setText("Male");
            }

            if (individualEntryGender.equals("Female")) {
                rbFemale.setChecked(true);
                etxtOther.setText("Female");
            }

            if (individualEntryGender.equals("Others")) {
                rbOthers.setChecked(true);
                etxtOther.setText("Others");
            }

            if (individualEntryGender.equals("Others")) {
                rbOthers.setChecked(true);
                etxtOther.setText("Others");
            }


            String[] splitHobbies = individualEntryHobbies.split("\n");




        }

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

        btnEditEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editEntryName = etxtName.getText().toString();
                String editEntryRemark = etxtRemark.getText().toString();
                String editEntryBirthdate = tvBirthdate.getText().toString();
                String addEntryHobbies = etxtHobbies.getText().toString();
                String editEntryGender = "";

                //radio button
                if(rbMale.isChecked()){
                    editEntryGender = "Male";
                }else if(rbFemale.isChecked()){
                    editEntryGender = "Female";
                }else if (rbOthers.isChecked()){
                    editEntryGender = etxtOther.getText().toString();
                }

                if (editEntryName.equals("") || addEntryHobbies.equals("") || editEntryRemark.equals("") ||
                        editEntryBirthdate.equals("") || editEntryGender.equals("") || editEntryBirthdate.equals("Date of Birth")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(c);
                    builder.setTitle("Attention").setMessage("Answer all the required fields").setCancelable(false).setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {

                    Intent putEditEntryIntent = new Intent();

                 putEditEntryIntent.putExtra("editEntryBirthday", editEntryBirthdate);
                    putEditEntryIntent.putExtra("editEntryHobbies", addEntryHobbies);
                    putEditEntryIntent.putExtra("editEntryGender", editEntryGender);
                    putEditEntryIntent.putExtra("editEntryPosition", entryPosition);

                   putEditEntryIntent.putExtra("editEntryName", editEntryName);

                    putEditEntryIntent.putExtra("editEntryRemark", editEntryRemark);
                   putEditEntryIntent.putExtra("editEntryPicture", editEntryBtmpPicture);

                    setResult(RESULT_OK,putEditEntryIntent);
                    finish();

                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent putEditEntryIntent = new Intent();
                setResult(RESULT_OK,putEditEntryIntent);
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
            editEntryBtmpPicture = imageBitmap;
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