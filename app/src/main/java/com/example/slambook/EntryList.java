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


//        displayed by default
            entryList.add(new Entry(BitmapFactory.decodeResource(c.getResources(),
                    R.drawable.coffee2), "ds", "dsdsa", "dsdsa", "dsdsa", "dsdsa"));
            entryList.add(new Entry(BitmapFactory.decodeResource(c.getResources(),
                    R.drawable.coffee2), "ds2", "dsdsa2", "dsdsa", "dsdsa", "dsdsa"));
            entryList.add(new Entry(BitmapFactory.decodeResource(c.getResources(),
                    R.drawable.coffee2), "ds2", "dsdsa2", "dsdsa", "dsdsa", "dsdsa"));


            Intent getAddEntryIntent = getIntent();
            if (getAddEntryIntent.hasExtra("addEntryName") && getAddEntryIntent.hasExtra("addEntryRemark")) {
                String addEntryName = getAddEntryIntent.getStringExtra("addEntryName");
                String addEntryRemark = getAddEntryIntent.getStringExtra("addEntryRemark");

                String addBirthday = getAddEntryIntent.getStringExtra("addEntryBirthday");
                String addEntryHobbies = getAddEntryIntent.getStringExtra("addEntryHobbies");
                String addEntryGender = getAddEntryIntent.getStringExtra("addEntryGender");

                Bitmap addEntryBtmpPicture = getAddEntryIntent.getParcelableExtra("addEntryBtmpPicture");

                if (addEntryBtmpPicture != null) {
                    entryList.add(0, new Entry(addEntryBtmpPicture, addEntryName, addEntryRemark, addEntryHobbies, addEntryGender, addBirthday));

                } else {
                    entryList.add(0, new Entry(BitmapFactory.decodeResource(c.getResources(),
                            R.drawable.no_picture), addEntryName, addEntryRemark, addEntryHobbies, addEntryGender, addBirthday));
                }

                Toast.makeText(c, "Item Inserted", Toast.LENGTH_SHORT).show();
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
                    putEntryListIntent.putExtra("loginFullName", loginFullName);
                    putEntryListIntent.putExtra("loginPicture", loginPicture);
                    startActivity(putEntryListIntent);

                }
            });

            recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemEditClicked(int position) {
                    Toast.makeText(c, "updated", Toast.LENGTH_SHORT).show();
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

                    Intent putArrayListIntent = new Intent(c, IndividualEntry.class);

                    putArrayListIntent.putExtra("loginFullName", loginFullName);
                    putArrayListIntent.putExtra("loginPicture", loginPicture);

                    putArrayListIntent.putExtra("entryListFullName", arrayListFullName);

                    putArrayListIntent.putExtra("entryListRemark", arrayListRemark);
                    putArrayListIntent.putExtra("entryListGender", arrayListGender);

                    putArrayListIntent.putExtra("entryListBirthday", arrayListBirthday);
                    putArrayListIntent.putExtra("entryListHobbies", arrayListHobbies);
                    startActivity(putArrayListIntent);

                    recyclerViewAdapter.notifyItemInserted(0);
//                    layoutManager.scrollToPosition(0);
                }
            });

        }
    }

}