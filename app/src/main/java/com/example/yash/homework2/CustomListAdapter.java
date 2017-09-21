//Assignment Homework 2
//CustomListAdapter
//Prabhakar Teja Seeda,Yash Ghia
package com.example.yash.homework2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class CustomListAdapter extends ArrayAdapter<ContactDetails> {
    private final Activity context;
    private final List<ContactDetails> contact;
    public CustomListAdapter(Activity context, List<ContactDetails> contactDetails) {
        super(context, R.layout.mylist, (List<ContactDetails>) contactDetails);
        this.context = context;
        this.contact = contactDetails;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);
        ContactDetails dataModel;
        dataModel = getItem(position);
        TextView name = (TextView) rowView.findViewById(R.id.name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.profileimage);
        TextView mobilenumber = (TextView) rowView.findViewById(R.id.mobilenumber);
        name.setText(dataModel.getFirstName() + " " + dataModel.getLastName());
        if(dataModel.Image.length==1)
        {
            imageView.setImageResource(R.drawable.default_image);
        }
        else {
            Bitmap bitmap = BitmapFactory.decodeByteArray(dataModel.Image, 0, dataModel.Image.length);
            imageView.setImageBitmap(bitmap);
        }
        mobilenumber.setText(dataModel.getPhone());
        return rowView;
    };
}