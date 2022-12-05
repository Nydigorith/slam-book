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
    EditText etxtName, etxtRemark, etxtOther;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale, rbOthers;
    CheckBox cbReading, cbTraveling, cbFishing, cbCrafting, cbTelevision, cbBirdWatching, cbCollecting, cbMusic, cbGardening, cbVideoGames;
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
        tvBirthdate = findViewById(R.id.tvBirthdate);
        etxtName = findViewById(R.id.etxtName);
        etxtRemark = findViewById(R.id.etxtRemark);
        btnCancel = findViewById(R.id.btnCancel);
        btnAddEntry = findViewById(R.id.btnAddEntry);

        etxtOther = findViewById(R.id.etxtOther);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        rbOthers = findViewById(R.id.rbOthers);
        cbReading = findViewById(R.id.cbReading);
        cbTraveling = findViewById(R.id.cbTraveling);
        cbFishing = findViewById(R.id.cbFishing);
        cbCrafting = findViewById(R.id.cbCrafting);
        cbTelevision = findViewById(R.id.cbTelevision);
        cbBirdWatching = findViewById(R.id.cbBirdWatching);
        cbCollecting = findViewById(R.id.cbCollecting);
        cbMusic = findViewById(R.id.cbMusic);
        cbGardening = findViewById(R.id.cbGardening);
        cbVideoGames = findViewById(R.id.cbVideoGames);
        cbGardening = findViewById(R.id.cbGardening);

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
                String addEntryGender = etxtOther.getText().toString();
                if (!(cbReading.isChecked() || cbBirdWatching.isChecked() || cbCollecting.isChecked() || cbCrafting.isChecked() || cbFishing.isChecked() || cbTraveling.isChecked() || cbGardening.isChecked() || cbMusic.isChecked() || cbTelevision.isChecked() || cbVideoGames.isChecked()) || addEntryName.equals("") || addEntryRemark.equals("") ||
                        addEntryBirthdate.equals("") || addEntryGender.equals("") || addEntryBirthdate.equals("Date of Birth")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(c);
                    builder.setTitle("Attention").setMessage("Answer reqquired fields ").setCancelable(false).setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    String hobbies = "";

                    if (cbReading.isChecked()) {
                        hobbies += cbReading.getText().toString() + "\n";

                    }
                    if (cbBirdWatching.isChecked()) {
                        hobbies += cbBirdWatching.getText().toString() + "\n";
                    }
                    if (cbCollecting.isChecked()) {
                        hobbies += cbCollecting.getText().toString() + "\n";
                    }
                    if (cbCrafting.isChecked()) {
                        hobbies += cbCrafting.getText().toString() + "\n";
                    }
                    if (cbFishing.isChecked()) {
                        hobbies += cbFishing.getText().toString() + "\n";
                    }
                    if (cbTraveling.isChecked()) {
                        hobbies += cbTraveling.getText().toString() + "\n";
                    }
                    if (cbGardening.isChecked()) {
                        hobbies += cbGardening.getText().toString() + "\n";
                    }
                    if (cbMusic.isChecked()) {
                        hobbies += cbMusic.getText().toString() + "\n";
                    }
                    if (cbTelevision.isChecked()) {
                        hobbies += cbTelevision.getText().toString() + "\n";
                    }
                    if (cbVideoGames.isChecked()) {
                        hobbies += cbVideoGames.getText().toString() + "\n";
                    }

                    Intent getEntryListIntent = getIntent();
                    if (getEntryListIntent.hasExtra("loginFullName")) {
                        String loginFullName = getEntryListIntent.getStringExtra("loginFullName");
                        Bitmap loginPicture = getEntryListIntent.getParcelableExtra("loginPicture");

                        Intent putAddEntryIntent = new Intent(c, EntryList.class);
                        putAddEntryIntent.putExtra("loginFullName", loginFullName);
                        putAddEntryIntent.putExtra("loginPicture", loginPicture);

                        putAddEntryIntent.putExtra("addEntryBirthday", addEntryBirthdate);
                        putAddEntryIntent.putExtra("addEntryHobbies", hobbies);
                        putAddEntryIntent.putExtra("addEntryGender", addEntryGender);
                        putAddEntryIntent.putExtra("addEntryName", addEntryName);
                        putAddEntryIntent.putExtra("addEntryRemark", addEntryRemark);
                        putAddEntryIntent.putExtra("addEntryBtmpPicture", addEntryBtmpPicture);
                        startActivity(putAddEntryIntent);
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent();
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
            RadioButton rb = (RadioButton) v;

            if (v.getId() == R.id.rbMale) {
                etxtOther.setText("Male");
            } else if (rb.getId() == R.id.rbFemale) {
                etxtOther.setText("Female");
            } else if (rb.getId() == R.id.rbOthers) {
                etxtOther.setText("Others");
            }

        }
    };
}


