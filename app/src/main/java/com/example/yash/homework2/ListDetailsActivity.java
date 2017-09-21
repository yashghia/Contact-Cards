//Assignment Homework 2
//ListDetailsActivity
//Prabhakar Teja Seeda,Yash Ghia
package com.example.yash.homework2;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yash.homework2.R;

import java.util.ArrayList;
import java.util.List;

public class ListDetailsActivity extends AppCompatActivity {
   ContactDetails contactDetailsList = new ContactDetails();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            contactDetailsList = (ContactDetails) bundle.getSerializable(DisplayContactActivity.CONTACT_FROMDISPLAY);
           // contactDetailsList = (ContactDetails) getIntent().getExtras().getSerializable(DisplayContactActivity.CONTACT_LIST_FROMDISPLAY);
            ((TextView) findViewById(R.id.nameText)).setText(contactDetailsList.getFirstName());
            ((TextView) findViewById(R.id.addressText)).setText(contactDetailsList.getAddress());
            ((TextView) findViewById(R.id.fbTextView)).setText(contactDetailsList.getFacebookURL());
            ((TextView) findViewById(R.id.twitterURL)).setText(contactDetailsList.getTwitterURL());
            ((TextView) findViewById(R.id.skypeURL)).setText(contactDetailsList.getSkype());
            ((TextView) findViewById(R.id.phoneNumberText)).setText(contactDetailsList.getPhone());
            ((TextView) findViewById(R.id.emailTextView)).setText(contactDetailsList.getEmail());
            if (contactDetailsList.getImage().length>1) {
                ((ImageView) findViewById(R.id.contactImageView)).setImageBitmap(BitmapFactory.decodeByteArray(contactDetailsList.getImage(), 0, contactDetailsList.getImage().length));
            }else {
                ((ImageView) findViewById(R.id.contactImageView)).setImageResource(R.drawable.default_image);
            }
        }
    }
}
