package com.example.slambook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EntryList extends AppCompatActivity {
    Context c = this;
    ImageView ivPicture;
    TextView txtFullName;
    Button btnLogout, btnDeleteEntry, btnEditEntry;
final int ADD_ENTRY_REQUEST_CODE  =1;
    Button btnAddEntry;
    RecyclerView rvEntryList;

    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;

    ArrayList<Entry> entryList = new ArrayList<>();
    ArrayList<Entry> entryList2 = new ArrayList<>();

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
                    builder.setTitle("Ylogout").setMessage("Ylogout").setCancelable(false).setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
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

            //edit
            Intent getEditEntryIntent = getIntent();
            if (getEditEntryIntent.hasExtra("editEntryName") ) {
                String editEntryName = getEditEntryIntent.getStringExtra("editEntryName");
                String editEntryRemark = getEditEntryIntent.getStringExtra("editEntryRemark");
                String entryListPosition = getEditEntryIntent.getStringExtra("editEntryPosition");
                String editBirthday = getEditEntryIntent.getStringExtra("editEntryBirthday");
                String editEntryHobbies = getEditEntryIntent.getStringExtra("editEntryHobbies");
                String editEntryGender = getEditEntryIntent.getStringExtra("editEntryGender");
                Toast.makeText(c, entryListPosition+"", Toast.LENGTH_SHORT).show();

                Bitmap editEntryBtmpPicture = getEditEntryIntent.getParcelableExtra("editEntryBtmpPicture");

                if (editEntryBtmpPicture != null) {
//                    if (getEditEntryIntent.hasExtra("editEntryCancel") ) {
//                        entryList.set(Integer.parseInt(entryListPosition), new Entry(editEntryBtmpPicture, editEntryName, editEntryRemark, editEntryHobbies, editEntryGender, editBirthday));
//                    } else {
                        entryList.add(Integer.parseInt(entryListPosition), new Entry(editEntryBtmpPicture, editEntryName, editEntryRemark, editEntryHobbies, editEntryGender, editBirthday));

//                    }
                } else {
//                    if (getEditEntryIntent.hasExtra("editEntryCancel") ) {
//                        entryList.set(Integer.parseInt(entryListPosition), new Entry(BitmapFactory.decodeResource(c.getResources(),
//                                R.drawable.no_picture), editEntryName, editEntryRemark, editEntryHobbies, editEntryGender, editBirthday));
//                    } else {
                        entryList.add(Integer.parseInt(entryListPosition), new Entry(BitmapFactory.decodeResource(c.getResources(),
                                R.drawable.no_picture), editEntryName, editEntryRemark, editEntryHobbies, editEntryGender, editBirthday));
//                    }
                }

                Toast.makeText(c, "Item Inserted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(c, "no intent", Toast.LENGTH_SHORT).show();
            }


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

                    putEntryListIntent.putExtra("entryListFullName", arrayListFullName);
                    putEntryListIntent.putExtra("entryListPosition", String.valueOf(position));
                    putEntryListIntent.putExtra("entryListRemark", arrayListRemark);
                    putEntryListIntent.putExtra("entryListGender", arrayListGender);

                    putEntryListIntent.putExtra("entryListBirthday", arrayListBirthday);
                    putEntryListIntent.putExtra("entryListHobbies", arrayListHobbies);

                    startActivity(putEntryListIntent);
                }

                @Override
                public void onItemDeleteClicked(int position) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(c);
                    builder.setTitle("delete").setMessage("Delete").setCancelable(false).setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
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

                    putArrayListIntent.putExtra("loginFullName", loginFullName);
                    putArrayListIntent.putExtra("loginPicture", loginPicture);

                    putArrayListIntent.putExtra("entryListFullName", arrayListFullName);

                    putArrayListIntent.putExtra("entryListRemark", arrayListRemark);
                    putArrayListIntent.putExtra("entryListGender", arrayListGender);
                    Toast.makeText(c, arrayListPicture+"", Toast.LENGTH_SHORT).show();
                    putArrayListIntent.putExtra("entryListBirthday", arrayListBirthday);
                    putArrayListIntent.putExtra("entryListHobbies", arrayListHobbies);
                    putArrayListIntent.putExtra("entryListPicture", arrayListPicture);

                    startActivity(putArrayListIntent);

                }
            });

        }
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
                        entryList.add(0, new Entry(addEntryBtmpPicture, addEntryName, addEntryRemark, addEntryHobbies, addEntryGender, addBirthday));

                    } else {
                        entryList.add( 0,new Entry(BitmapFactory.decodeResource(c.getResources(),
                                R.drawable.no_picture), addEntryName, addEntryRemark, addEntryHobbies, addEntryGender, addBirthday));

                    }
                Toast.makeText(c, addEntryName+addEntryRemark+addBirthday+addEntryHobbies+addEntryGender+""+addEntryBtmpPicture, Toast.LENGTH_SHORT).show();

                    Toast.makeText(c, "Item Inserted", Toast.LENGTH_SHORT).show();
                recyclerViewAdapter.notifyItemInserted(0);
                layoutManager.scrollToPosition(0);
            }

        }
    }
}