package com.example.slambook;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Registration extends AppCompatActivity {

    Context c = this;
    static  final int REQUEST_IMAGE_CAPTURE = 1;
    String months [] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    ImageView ivPicture;
    TextView tvBirthdate;
    EditText etxtUsername, etxtPassword, etxtConfirmPassword, etxtFirstName, etxtLastName,etxtMiddleName, etxtEmailAddress, etxtAddress, etxtContact, etxtOther, etxtBarangayAddress,etxtStreetAddress,etxtNumberAddress,etxtMunicipalityAddress,etxtProvinceAddress,sprAnswer1,sprAnswer2,sprAnswer3;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale, rbOthers;
    CheckBox cbReading, cbTraveling, cbFishing, cbCrafting, cbTelevision, cbBirdWatching, cbCollecting, cbMusic, cbGardening, cbVideoGames;
    Spinner sprQuestion1, sprQuestion2, sprQuestion3;
    DatePickerDialog datePickerDialog;
    Button btnSubmit, btnTakePicture;
    Bitmap btmpPicture;

    Calendar currentDate = Calendar.getInstance();
    int year = currentDate.get(Calendar.YEAR);
    int month = currentDate.get(Calendar.MONTH);
    int day = currentDate.get(Calendar.DAY_OF_MONTH);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initialize();
        process();
    }

    private void initialize() {
        tvBirthdate = findViewById(R.id.tvBirthdate);
        etxtUsername = findViewById(R.id.etxtUsername);
        etxtPassword = findViewById(R.id.etxtPassword);
        etxtConfirmPassword = findViewById(R.id.etxtConfirmPassword);
        etxtFirstName = findViewById(R.id.etxtFirstName);
        etxtLastName = findViewById(R.id.etxtLastName);
        etxtMiddleName = findViewById(R.id.etxtMiddleName);
        etxtEmailAddress = findViewById(R.id.etxtEmailAddress);

        etxtBarangayAddress = findViewById(R.id.etxtBarangayAddress);
        etxtStreetAddress = findViewById(R.id.etxtStreetAddress);
        etxtNumberAddress = findViewById(R.id.etxtNumberAddress);
        etxtMunicipalityAddress = findViewById(R.id.etxtMunicipalityAddress);
        etxtProvinceAddress = findViewById(R.id.etxtProvinceAddress);

        etxtContact = findViewById(R.id.etxtContact);
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
        sprQuestion1 = findViewById(R.id.sprQuestion1);
        sprQuestion2 = findViewById(R.id.sprQuestion2);
        sprQuestion3 = findViewById(R.id.sprQuestion3);
        btnSubmit = findViewById(R.id.btnSubmit);
        ivPicture = findViewById(R.id.ivPicture);
        btnTakePicture = findViewById(R.id.btnTakePicture);

        sprAnswer1 = findViewById(R.id.sprAnswer1);
        sprAnswer2 = findViewById(R.id.sprAnswer2);
        sprAnswer3 = findViewById(R.id.sprAnswer3);

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

        String questions[] = {"In what city were you born?",
                "What is the name of your favorite pet?",
                "What is your mother's maiden name?",
                "What high school did you attend?",
                "What was the name of your elementary school?"
        };
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_item, questions);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sprQuestion1.setAdapter(myAdapter);
        sprQuestion2.setAdapter(myAdapter);
        sprQuestion3.setAdapter(myAdapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etxtUsername.getText().toString();
                String password = etxtPassword.getText().toString();
                String confirmPassword = etxtConfirmPassword.getText().toString();
                String firstName = etxtFirstName.getText().toString();
                String middleName = etxtMiddleName.getText().toString();
                String lastName = etxtLastName.getText().toString();
                String email = etxtEmailAddress.getText().toString();
                String birthdate = tvBirthdate.getText().toString();

                String barangay = etxtBarangayAddress.getText().toString();
                String street = etxtStreetAddress.getText().toString();
                String number = etxtNumberAddress.getText().toString();
                String municipality = etxtMunicipalityAddress.getText().toString();
                String province = etxtProvinceAddress.getText().toString();

                String answer1 = sprAnswer1.getText().toString();
                String answer2 = sprAnswer2.getText().toString();
                String answer3 = sprAnswer3.getText().toString();

                String contact = etxtContact.getText().toString();
                String gender = etxtOther.getText().toString();

                if (!(cbReading.isChecked() || cbBirdWatching.isChecked() || cbCollecting.isChecked() || cbCrafting.isChecked() || cbFishing.isChecked() || cbTraveling.isChecked() || cbGardening.isChecked() || cbMusic.isChecked() || cbTelevision.isChecked() || cbVideoGames.isChecked()) || username.equals("") || password.equals("") || confirmPassword.equals("") || firstName.equals("") ||
                        lastName.equals("") || middleName.equals("") || email.equals("") || birthdate.equals("") || barangay.equals("") || street.equals("") || number.equals("") || municipality.equals("") || province.equals("") || answer1.equals("") || answer2.equals("") || answer3.equals("") ||
                        contact.equals("") || gender.equals("") || birthdate.equals("Date of Birth") || ivPicture.getDrawable() == null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(c);
                    builder.setTitle("Attention").setMessage("All fields are required").setCancelable(false).setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    if (password.equals(confirmPassword)) {

                        if (sprQuestion1.getSelectedItemPosition() == sprQuestion2.getSelectedItemPosition()) {
                            sprSameQuestionAlert();
                        } else if (sprQuestion1.getSelectedItemPosition() == sprQuestion3.getSelectedItemPosition()) {
                            sprSameQuestionAlert();
                        } else if (sprQuestion2.getSelectedItemPosition() == sprQuestion3.getSelectedItemPosition()) {
                            sprSameQuestionAlert();
                        } else {
                            //checkboxes
                            String hobbies = "";

                            if (cbReading.isChecked()) {
                                hobbies += "\t\t\t" + cbReading.getText().toString() + "\n";

                            }
                            if (cbBirdWatching.isChecked()) {
                                hobbies += "\t\t\t" +cbBirdWatching.getText().toString() + "\n";
                            }
                            if (cbCollecting.isChecked()) {
                                hobbies += "\t\t\t" +cbCollecting.getText().toString() + "\n";
                            }
                            if (cbCrafting.isChecked()) {
                                hobbies += "\t\t\t" +cbCrafting.getText().toString() + "\n";
                            }
                            if (cbFishing.isChecked()) {
                                hobbies += "\t\t\t" +cbFishing.getText().toString() + "\n";
                            }
                            if (cbTraveling.isChecked()) {
                                hobbies += "\t\t\t" +cbTraveling.getText().toString() + "\n";
                            }
                            if (cbGardening.isChecked()) {
                                hobbies += "\t\t\t" +cbGardening.getText().toString() + "\n";
                            }
                            if (cbMusic.isChecked()) {
                                hobbies += "\t\t\t" +cbMusic.getText().toString() + "\n";
                            }
                            if (cbTelevision.isChecked()) {
                                hobbies += "\t\t\t" +cbTelevision.getText().toString() + "\n";
                            }
                            if (cbVideoGames.isChecked()) {
                                hobbies += "\t\t\t" +cbVideoGames.getText().toString() + "\n";
                            }


                            String information = "";


                            information += "Username: " + username + "\n";
                            information += "Password: " + password + "\n";

                            information += "Naeme: " + firstName + " " + middleName + " " + lastName +"\n";
                            information += "Email: " + email + "\n";
                            information += "Birthdday: " + birthdate + "\n";

                            information += "Gender: " + etxtOther.getText().toString() + "\n";
                            information += "Address: " + number +" " + street  +", " + barangay  +", " + municipality  +", " + province + "\n";
                            information += "Contact: " + contact + "\n";
                            information += "Hobbies:\n " + hobbies + "\n";
                            information += "Q1: " + sprQuestion1.getSelectedItem().toString() + "\n";
                            information += "A1: " + answer1+ "\n";

                            information += "Q2: " + sprQuestion2.getSelectedItem().toString() + "\n";
                            information += "A2: " + answer2 + "\n";

                            information += "Q3: " + sprQuestion3.getSelectedItem().toString() + "\n";
                            information += "A3: " + answer3 + "\n";


                            AlertDialog.Builder builder = new AlertDialog.Builder(c);
                            builder.setTitle("All Information").setMessage(information).setCancelable(false).setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(c, "Registration successful", Toast.LENGTH_SHORT).show();
                                    Intent putRegistrationIntent = new Intent(c, Login.class);
                                    putRegistrationIntent.putExtra("registrationFullName", firstName + " " + middleName + " " + lastName);
                                    putRegistrationIntent.putExtra("registrationUsername", username);
                                    putRegistrationIntent.putExtra("registrationPassword", password);
                                    putRegistrationIntent.putExtra("registrationPicture", btmpPicture);
                                    startActivity(putRegistrationIntent);
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(c);
                        builder.setTitle("Attention").setMessage("Password do not match").setCancelable(false).setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
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
        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                if(takePictureIntent.resolveActivity(getPackageManager())!=null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });


    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivPicture.setImageBitmap(imageBitmap);
            btmpPicture = imageBitmap;
        }
    }



    void sprSameQuestionAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Attention").setMessage("Please pick another question").setCancelable(false).setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
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


