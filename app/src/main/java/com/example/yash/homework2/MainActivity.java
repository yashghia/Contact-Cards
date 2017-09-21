//Assignment Homework 2
//Main Activity
//Prabhakar Teja Seeda,Yash Ghia
package com.example.yash.homework2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final static String CONTACT_LIST_KEY = "contactList";
    public final static String CONTACT_EDIT = "EDIT";
    List<ContactDetails> contactDetailsList = new ArrayList<ContactDetails>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onButtonClick(View view){
        Intent i;
        switch (view.getId()){
            case R.id.create:
                if (getIntent().getExtras() != null) {
                    contactDetailsList = (List<ContactDetails>) getIntent().getExtras().getSerializable(CreateNewActivity.CONTACT_KEY);
                }
                i = new Intent(MainActivity.this,CreateNewActivity.class);
                i.setFlags(100);
                i.putExtra(CONTACT_LIST_KEY, (Serializable) contactDetailsList);
                startActivity(i);
                break;
            case R.id.editFunction:
                contactDetailsList = (List<ContactDetails>) getIntent().getExtras().getSerializable(CreateNewActivity.CONTACT_KEY);
                    if (contactDetailsList!=null) {
                        i = new Intent(MainActivity.this, DisplayContactActivity.class);
                        i.setFlags(101);
                        i.putExtra(CONTACT_EDIT,true);
                        i.putExtra(CONTACT_LIST_KEY, (Serializable) contactDetailsList);
                        startActivity(i);
                    }
                break;
            case R.id.delete:
                if (getIntent().getExtras() != null) {
                        if (getIntent().getFlags()==110) {
                            contactDetailsList = (List<ContactDetails>) getIntent().getExtras().getSerializable(DisplayContactActivity.CONTACT_LIST_FROMDISPLAY);
                        }else {
                            contactDetailsList = (List<ContactDetails>) getIntent().getExtras().getSerializable(CreateNewActivity.CONTACT_KEY);
                        }
                    i = new Intent(MainActivity.this, DisplayContactActivity.class);
                    i.setFlags(102);
                    i.putExtra(CONTACT_LIST_KEY, (Serializable) contactDetailsList);
                    startActivity(i);
                }
                break;
            case R.id.display:
                if (getIntent().getFlags()==110) {
                    contactDetailsList = (List<ContactDetails>) getIntent().getExtras().getSerializable(DisplayContactActivity.CONTACT_LIST_FROMDISPLAY);
                }else {
                    contactDetailsList = (List<ContactDetails>) getIntent().getExtras().getSerializable(CreateNewActivity.CONTACT_KEY);
                }
                    i = new Intent(MainActivity.this, DisplayContactActivity.class);
                    i.setFlags(103);
                    i.putExtra(CONTACT_LIST_KEY, (Serializable) contactDetailsList);
                    startActivity(i);

                break;
            case R.id.finish:
                finish();
                System.exit(0);
                break;
        }
    }
}
