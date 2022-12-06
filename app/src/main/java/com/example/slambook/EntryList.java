package com.example.slambook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class EntryList extends AppCompatActivity {
    Context c = this;
    ImageView ivPicture;
    TextView txtFullName;
    Button btnLogout, btnDeleteEntry, btnEditEntry;
final int ADD_ENTRY_REQUEST_CODE  =1;
    final int INDIVIDUAl_ENTRY_REQUEST_CODE  =1;
    final int EDIT_ENTRY_REQUEST_CODE  =1;
    Button btnAddEntry;
    RecyclerView rvEntryList;

    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;

    ArrayList<Entry> entryList = new ArrayList<>();
    ArrayList<Entry> entryList2 = new ArrayList<>();
Bitmap currentEntryPicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);
        initialize();
    }

    private void initialize() {
        ivPicture = findViewById(R.id.ivPicture);
        txtFullName = findViewById(R.id.txtFullName);
        btnLogout = findViewById(R.id.btnLogout);

        btnAddEntry = findViewById(R.id.btnAddEntry);
        btnEditEntry = findViewById(R.id.btnEditEntry);
        btnDeleteEntry = findViewById(R.id.btnDeleteEntry);
        rvEntryList = findViewById(R.id.rvEntryList);

        Intent getLoginIntent = getIntent();
        if (getLoginIntent.hasExtra("loginFullName")) {
            // && getLoginIntent.hasExtra("loginPicture")
            String loginFullName = getLoginIntent.getStringExtra("loginFullName");
            Bitmap loginPicture = getLoginIntent.getParcelableExtra("loginPicture");
            txtFullName.setText(loginFullName);
            ivPicture.setImageBitmap(loginPicture);

            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(c);
                    builder.setTitle("Logout").setMessage("Are you sure you want to log out?").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(c, Login.class);
                            startActivity(intent);
                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });


  //      displayed by default
            entryList.add(new Entry(BitmapFactory.decodeResource(c.getResources(),
                    R.drawable.coffee2), "1", "dsdsa", "Fishing\nCollecting", "Male", "dsdsa"));
            entryList.add(new Entry(BitmapFactory.decodeResource(c.getResources(),
                    R.drawable.coffee2), "2", "dsdsa2", "Fishing\nCollecting", "Female", "dsdsa"));
            entryList.add(new Entry(BitmapFactory.decodeResource(c.getResources(),
                    R.drawable.coffee2), "3", "dsdsa2", "Fishing\nCollecting", "Others", "dsdsa"));



            entryList2 = (ArrayList<Entry>) entryList.clone();
            rvEntryList = findViewById(R.id.rvEntryList);
            btnAddEntry = findViewById(R.id.btnAddEntry);

            layoutManager = new LinearLayoutManager(c);
            rvEntryList.setLayoutManager(layoutManager);
            recyclerViewAdapter = new RecyclerViewAdapter(c, R.layout.slam_book_entry, entryList);
            rvEntryList.setAdapter(recyclerViewAdapter);

            btnAddEntry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent putEntryListIntent = new Intent(c, AddEntry.class);

                    startActivityForResult(putEntryListIntent,ADD_ENTRY_REQUEST_CODE);

                }
            });

            recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemEditClicked(int position) {
                    Intent putEntryListIntent = new Intent(c, EditEntry.class);
                    putEntryListIntent.putExtra("loginFullName", loginFullName);
                    putEntryListIntent.putExtra("loginPicture", loginPicture);

                    String arrayListRemark = entryList.get(position).getEntryRemark();
                    String arrayListFullName = entryList.get(position).getEntryFullName();

                    String arrayListGender = entryList.get(position).getEntryGender();
                    String arrayListBirthday = entryList.get(position).getEntryBirthday();
                    String arrayListHobbies = entryList.get(position).getEntryHobbies();
                    Bitmap arrayListPicture = entryList.get(position).getEntryPicture();

                    currentEntryPicture = arrayListPicture;
                    putEntryListIntent.putExtra("entryListFullName", arrayListFullName);
                    putEntryListIntent.putExtra("entryListPosition", String.valueOf(position));
                    putEntryListIntent.putExtra("entryListRemark", arrayListRemark);
                    putEntryListIntent.putExtra("entryListGender", arrayListGender);

                    putEntryListIntent.putExtra("entryListBirthday", arrayListBirthday);
                    putEntryListIntent.putExtra("entryListHobbies", arrayListHobbies);

                    String pictureFilePath= tempFileImage(c,arrayListPicture,"name");
                    putEntryListIntent.putExtra("entryListPicture", pictureFilePath);
                    startActivityForResult(putEntryListIntent,EDIT_ENTRY_REQUEST_CODE);
                }

                @Override
                public void onItemDeleteClicked(int position) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(c);
                    builder.setTitle("Delete").setMessage("Are you sure you want to delete this entry?").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            entryList.remove(position);
                            recyclerViewAdapter.notifyItemRemoved(position);
//                    layoutManager.scrollToPosition(position);
                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

                public void onItemDisplayClicked(int position) {
                    String arrayListRemark = entryList.get(position).getEntryRemark();
                    String arrayListFullName = entryList.get(position).getEntryFullName();

                    String arrayListGender = entryList.get(position).getEntryGender();
                    String arrayListBirthday = entryList.get(position).getEntryBirthday();
                    String arrayListHobbies = entryList.get(position).getEntryHobbies();
                    Bitmap arrayListPicture = entryList.get(position).getEntryPicture();

                    Intent putArrayListIntent = new Intent(c, IndividualEntry.class);
                  //  putArrayListIntent.putExtra("entryListFullName", arrayListFullName);
                 //   putArrayListIntent.putExtra("loginFullName", loginFullName);
                //    putArrayListIntent.putExtra("loginPicture", loginPicture);

                    putArrayListIntent.putExtra("entryListFullName", arrayListFullName);

                    putArrayListIntent.putExtra("entryListRemark", arrayListRemark);
                    putArrayListIntent.putExtra("entryListGender", arrayListGender);

                    putArrayListIntent.putExtra("entryListBirthday", arrayListBirthday);
                    putArrayListIntent.putExtra("entryListHobbies", arrayListHobbies);

                    //image size too large
                    String pictureFilePath= tempFileImage(c,arrayListPicture,"name");
                    putArrayListIntent.putExtra("entryListPicture", pictureFilePath);
                    startActivityForResult(putArrayListIntent,INDIVIDUAl_ENTRY_REQUEST_CODE);
                    //startActivity(putArrayListIntent);

                }
            });

        }
    }

    public static String tempFileImage(Context context, Bitmap bitmap, String name) {

        File outputDir = context.getCacheDir();
        File imageFile = new File(outputDir, name + ".jpg");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.e(context.getClass().getSimpleName(), "Error writing file", e);
        }

        return imageFile.getAbsolutePath();
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ENTRY_REQUEST_CODE && resultCode == RESULT_OK) {
            if(data.hasExtra("addEntryName")) {
                    String addEntryName = data.getStringExtra("addEntryName");
                    String addEntryRemark = data.getStringExtra("addEntryRemark");

                    String addBirthday = data.getStringExtra("addEntryBirthday");
                    String addEntryHobbies = data.getStringExtra("addEntryHobbies");
                    String addEntryGender = data.getStringExtra("addEntryGender");

                    Bitmap addEntryBtmpPicture = data.getParcelableExtra("addEntryBtmpPicture");

                    if (addEntryBtmpPicture != null) {
                        entryList.add( new Entry(addEntryBtmpPicture, addEntryName, addEntryRemark, addEntryHobbies, addEntryGender, addBirthday));

                    } else {
                        entryList.add( new Entry(BitmapFactory.decodeResource(c.getResources(),
                                R.drawable.no_picture), addEntryName, addEntryRemark, addEntryHobbies, addEntryGender, addBirthday));

                    }


                   // Toast.makeText(c, "Item Inserted", Toast.LENGTH_SHORT).show();
                recyclerViewAdapter.notifyItemInserted(entryList.size()-1);
                layoutManager.scrollToPosition(0);

            }

        }

        if (requestCode == INDIVIDUAl_ENTRY_REQUEST_CODE && resultCode == RESULT_OK) {
            if(data.hasExtra("addEntryName")) {

            }

        }
        if (requestCode == EDIT_ENTRY_REQUEST_CODE && resultCode == RESULT_OK) {
            //edit

            if (data.hasExtra("editEntryRemark") ) {

                String editEntryName = data.getStringExtra("editEntryName");
                String editEntryRemark = data.getStringExtra("editEntryRemark");
                String entryListPosition = data.getStringExtra("editEntryPosition");
                String editBirthday = data.getStringExtra("editEntryBirthday");
                String editEntryHobbies = data.getStringExtra("editEntryHobbies");
                String editEntryGender = data.getStringExtra("editEntryGender");
              Bitmap editEntryPicture = data.getParcelableExtra("editEntryPicture");

                if (editEntryPicture != null) {
                    entryList.set(Integer.parseInt(entryListPosition), new Entry(editEntryPicture, editEntryName, editEntryRemark, editEntryHobbies, editEntryGender, editBirthday));

                } else {
                    entryList.set(Integer.parseInt(entryListPosition), new Entry(currentEntryPicture, editEntryName, editEntryRemark, editEntryHobbies, editEntryGender, editBirthday));

                }

               // Toast.makeText(c, "Item Edited", Toast.LENGTH_SHORT).show();
                recyclerViewAdapter.notifyDataSetChanged();
            }
        }
    }
}