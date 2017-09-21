//Assignment Homework 2
//DisplayContactActivity
//Prabhakar Teja Seeda,Yash Ghia
package com.example.yash.homework2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DisplayContactActivity extends AppCompatActivity {

    public List<ContactDetails> contactDetailsList = new ArrayList<ContactDetails>();
    public final static String CONTACT_LIST_FROMDISPLAY = "contactList";
    public final static String CONTACT_FROMDISPLAY = "contact";
    public final static String CONTACT_POSITION = "contact position";
    public final static String BOOLEAN_VALUE = "Edit";
    ListView list;
    String[] name;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);
        if (getIntent().getExtras() != null) {
            contactDetailsList = (List<ContactDetails>) getIntent().getExtras().getSerializable(MainActivity.CONTACT_LIST_KEY);
        }
            Intent i = getIntent();
            if (i.getFlags() == 101) {
                CustomListAdapter adapter = new CustomListAdapter(this, contactDetailsList);
                list = (ListView) findViewById(R.id.listview);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Intent i = new Intent(DisplayContactActivity.this,CreateNewActivity.class);
                        i.putExtra(CONTACT_LIST_FROMDISPLAY,(Serializable) contactDetailsList);
                        ContactDetails contactDetails = contactDetailsList.get(position);
                        i.putExtra(CONTACT_FROMDISPLAY,contactDetails);
                        i.putExtra(CONTACT_POSITION,position);
                        //i.putExtra(BOOLEAN_VALUE, true);
                        i.setFlags(101);
                        startActivity(i);
                    }
                });
            }
            else if (i.getFlags() == 102) {
                CustomListAdapter adapter = new CustomListAdapter(this, contactDetailsList);
                list = (ListView) findViewById(R.id.listview);
                list.setAdapter(adapter);
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            final int position, long id) {
                        builder.setTitle("Do you really want to delete this?")
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                contactDetailsList.remove(position);
                                Intent intent = new Intent(DisplayContactActivity.this,MainActivity.class);
                                intent.putExtra(CONTACT_LIST_FROMDISPLAY, (Serializable) contactDetailsList);
                                intent.setFlags(110);
                                Log.d("delete","delete yes");
                                startActivity(intent);
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d("delete","delete no");
                                //alertDialog.dismiss();
                            }
                        }).setCancelable(false);
                        final AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                });

            } else if (i.getFlags() == 103) {
                CustomListAdapter adapter = new CustomListAdapter(this, contactDetailsList);
                list = (ListView) findViewById(R.id.listview);
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        //TODO implement implicit intent for urls
                        Intent i = new Intent(DisplayContactActivity.this,ListDetailsActivity.class);
                        i.putExtra(CONTACT_LIST_FROMDISPLAY,(Serializable) contactDetailsList);
                        ContactDetails contactDetails = contactDetailsList.get(position);
                        i.putExtra(CONTACT_FROMDISPLAY, (ContactDetails) contactDetails);
                        //i.putExtra(CONTACT_POSITION,position);
                        startActivity(i);
                    }
                });
            }
    }
}
