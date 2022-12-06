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
    EditText etxtName, etxtRemark, etxtOther;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale, rbOthers;
    CheckBox cbReading, cbTraveling, cbFishing, cbCrafting, cbTelevision, cbBirdWatching, cbCollecting, cbMusic, cbGardening, cbVideoGames;
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
        etxtName = findViewById(R.id.etxtName);
        etxtRemark = findViewById(R.id.etxtRemark);
        btnCancel = findViewById(R.id.btnCancel);
        btnEditEntry = findViewById(R.id.btnEditEntry);

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

            Toast.makeText(c, entryListPosition+" POS", Toast.LENGTH_SHORT).show();
            String pictureFilePath=getIntent().getStringExtra("entryListPicture");
            File file = new File(pictureFilePath);
            Bitmap individualEntryBtmpPicture = BitmapFactory.decodeFile(file.getAbsolutePath());

ivPicture.setImageBitmap(individualEntryBtmpPicture);
           // editEntryBtmpPicture = individualEntryBtmpPicture;


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

            getHobbies(splitHobbies,cbReading,"Reading");
            getHobbies(splitHobbies,   cbBirdWatching,"Bird Watching");
            getHobbies(splitHobbies,  cbCollecting, "Collecting");
            getHobbies(splitHobbies,  cbCrafting,"Crafting");
            getHobbies(splitHobbies,    cbFishing, "Fishing");
            getHobbies(splitHobbies,cbTraveling,"Traveling");
            getHobbies(splitHobbies,cbGardening,"Gardening");
            getHobbies(splitHobbies,cbMusic,"Music");
            getHobbies(splitHobbies,cbTelevision,"Television");
            getHobbies(splitHobbies, cbVideoGames,"Video Games");


        }

    }

    private void getHobbies(String[] splitHobbies, CheckBox checkBox, String hobby) {
        for(int i =0; i < splitHobbies.length; i++)
        {
            if(splitHobbies[i].contains(hobby))
            {
                checkBox.setChecked(true);
            }
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
                String editEntryGender = etxtOther.getText().toString();
                if (!(cbReading.isChecked() || cbBirdWatching.isChecked() || cbCollecting.isChecked() || cbCrafting.isChecked() || cbFishing.isChecked() || cbTraveling.isChecked() || cbGardening.isChecked() || cbMusic.isChecked() || cbTelevision.isChecked() || cbVideoGames.isChecked()) || editEntryName.equals("") || editEntryRemark.equals("") ||
                        editEntryBirthdate.equals("") || editEntryGender.equals("") || editEntryBirthdate.equals("Date of Birth")) {
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


                    Intent putEditEntryIntent = new Intent();

                 putEditEntryIntent.putExtra("editEntryBirthday", editEntryBirthdate);
                    putEditEntryIntent.putExtra("editEntryHobbies", hobbies);
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