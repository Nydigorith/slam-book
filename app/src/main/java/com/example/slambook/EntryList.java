package com.example.slambook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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
    Button btnLogout, btnDeleteEntry,btnEditEntry;

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
        if(getLoginIntent.hasExtra("loginFullName")) {
            // && getLoginIntent.hasExtra("loginPicture")
            String loginFullName = getLoginIntent.getStringExtra("loginFullName");
//            Bitmap loginPicture = getLoginIntent.getParcelableExtra("loginPicture");

            txtFullName.setText(loginFullName);
//            ivPicture.setImageBitmap(getPicture);


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
            entryList.add(new Entry(R.drawable.coffee1, "ds", "dsdsa"));
            entryList.add(new Entry(R.drawable.coffee2, "ds2", "dsdsa2"));
            entryList.add(new Entry(R.drawable.coffee2, "ds2", "dsdsa2"));



            Intent getAddEntryIntent = getIntent();
            if(getAddEntryIntent.hasExtra("addEntryName") && getAddEntryIntent.hasExtra("addEntryRemark") ) {
//&& getAddEntryIntent.hasExtra("addEntryBtmpPicture")
                String addEntryName = getAddEntryIntent.getStringExtra("addEntryName");
                String addEntryRemark = getAddEntryIntent.getStringExtra("addEntryRemark");
//                Bitmap addEntryBtmpPicture = getAddEntryIntent.getParcelableExtra("addEntryBtmpPicture");
                entryList.add(0, new Entry(R.drawable.coffee1, addEntryName, addEntryRemark));
                //   recyclerViewAdapter.notifyItemInserted(0);
                //               layoutManager.scrollToPosition(0);
                Toast.makeText(c, "Item Inserted", Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(c, "dsdsad", Toast.LENGTH_SHORT).show();
            }







            entryList2 = (ArrayList<Entry>) entryList.clone();
            rvEntryList = findViewById(R.id.rvEntryList);
            btnAddEntry = findViewById(R.id.btnAddEntry);

            layoutManager = new LinearLayoutManager(c);
            rvEntryList.setLayoutManager(layoutManager);
            recyclerViewAdapter = new RecyclerViewAdapter(c, R.layout.slam_book_entry,entryList);
            rvEntryList.setAdapter(recyclerViewAdapter);




            btnAddEntry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {




                    Intent putEntryListIntent = new Intent(c, AddEntry.class);
                    putEntryListIntent.putExtra("entryListFullName", loginFullName);
//                    putEntryListIntent.putExtra("entryListPicture", loginPicture);
                    startActivity(putEntryListIntent);
                }
            });

//            recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
//                @Override
//                public void onItemLongClick(int position) {
//                    entryList.remove(position);
//                    recyclerViewAdapter.notifyItemRemoved(position);
//                    layoutManager.scrollToPosition(position);
//                    Toast.makeText(c, "Item Deleted", Toast.LENGTH_SHORT).show();
//                }
//            });

            recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
//                    deletes data !! importatn
//                    entryList.remove(position);

//                    recyclerViewAdapter.notifyItemRemoved(position);


//                     Bitmap arrayListPicture = (Bitmap) entryList.get(position).getEntryPicture();
                    String arrayListRemark  = entryList.get(position).getEntryRemark();
                  String arrayListFullName =  entryList.get(position).getEntryFullName();

                    Intent putArrayListIntent = new Intent(c, IndividualEntry.class);
                    putArrayListIntent.putExtra("entryListFullName", arrayListFullName);
                    putArrayListIntent.putExtra("entryListRemark", arrayListRemark);
                    startActivity(putArrayListIntent);
                }
            });



        }
    }

}