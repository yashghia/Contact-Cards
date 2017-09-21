//Assignment Homework 2
//CreateNewActivity
//Prabhakar Teja Seeda,Yash Ghia
        package com.example.yash.homework2;
        import android.app.DatePickerDialog;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.icu.text.SimpleDateFormat;
        import android.icu.util.Calendar;
        import android.os.Build;
        import android.support.annotation.RequiresApi;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.Toast;
        import java.io.ByteArrayOutputStream;
        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;

public class CreateNewActivity extends AppCompatActivity {
    public static final int CAMERA_REQUEST_CODE = 1000;
    public SimpleDateFormat dateFormatter;
    public DatePickerDialog fromDatePickerDialog;
    final static String CONTACT_KEY = "CONTACT";
    ImageButton imageview;
    Bitmap image;
    public Boolean isEdit = false;
    int position=0;
    List<ContactDetails> contactDetailsList = new ArrayList<ContactDetails>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);
        if(getIntent().getExtras().getSerializable(DisplayContactActivity.CONTACT_FROMDISPLAY)!= null){
            ContactDetails contactDetails = new ContactDetails();
            position = (int) getIntent().getExtras().get(DisplayContactActivity.CONTACT_POSITION);
            contactDetails =(ContactDetails)getIntent().getExtras().getSerializable(DisplayContactActivity.CONTACT_FROMDISPLAY);
            if (getIntent().getFlags()==101) {
                isEdit = true;
            }
            ((EditText) findViewById(R.id.firstName)).setText(contactDetails.getFirstName());
            ((EditText) findViewById(R.id.lastName)).setText(contactDetails.getLastName());
            ((EditText) findViewById(R.id.companyName)).setText(contactDetails.Company);
            ((EditText) findViewById(R.id.phoneNumber)).setText(contactDetails.Phone);
            ((EditText) findViewById(R.id.emailID)).setText(contactDetails.Email);
            ((EditText) findViewById(R.id.urlField)).setText(contactDetails.URL);
            ((EditText) findViewById(R.id.addressField)).setText(contactDetails.Address);
            ((EditText) findViewById(R.id.birthday)).setText(contactDetails.Birthday);
            ((EditText) findViewById(R.id.nickName)).setText(contactDetails.NickName);
            ((EditText) findViewById(R.id.facebookURL)).setText(contactDetails.FacebookURL);
            ((EditText) findViewById(R.id.twitterURL)).setText(contactDetails.TwitterURL);
            ((EditText) findViewById(R.id.skypeField)).setText(contactDetails.Skype);
            ((EditText) findViewById(R.id.youtubeChannel)).setText(contactDetails.YoutubeChannel);
            image = BitmapFactory.decodeByteArray(contactDetails.Image,0,contactDetails.Image.length);
            ((ImageButton) findViewById((R.id.imageButton))).setImageBitmap(image);
        }
        Intent i = new Intent();
        //i.setFlags(100);
        final EditText birthdayText = (EditText) findViewById(R.id.birthday);
        birthdayText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    setDateFields();
                } else {

                }
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setDateFields(){
        final EditText birthdayText = (EditText) findViewById(R.id.birthday);
        final EditText nickNameText = (EditText) findViewById(R.id.nickName);
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                birthdayText.setText(monthOfYear+"/"+dayOfMonth+"/"+year);
                birthdayText.clearFocus();
                nickNameText.requestFocus();

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        fromDatePickerDialog.show();
    }
    public void openCamera(View view){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        imageview = (ImageButton) findViewById(R.id.imageButton); //sets imageview as the bitmap

        if (requestCode == CAMERA_REQUEST_CODE) {
            if (getIntent().getExtras() != null) {
                image = (Bitmap) data.getExtras().get("data");
                imageview.setImageBitmap(image);
            } else {
                imageview.setImageResource(R.drawable.default_image);
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void SaveContactDetails(View view) {
        if (getIntent().getExtras()!=null){
            Log.d("Message","The flag is "+getIntent().getFlags());
            if (getIntent().getFlags()==101) {
                isEdit = true;
            }
        }
                if(isEdit)
                {
                    contactDetailsList = (List<ContactDetails>) getIntent().getExtras().getSerializable(DisplayContactActivity.CONTACT_LIST_FROMDISPLAY);
                }
                else {
                    contactDetailsList = (List<ContactDetails>) getIntent().getExtras().getSerializable(MainActivity.CONTACT_LIST_KEY);
                }

                ContactDetails contactDetails = new ContactDetails();
                Intent i = new Intent(CreateNewActivity.this, MainActivity.class);
                contactDetails.FirstName = ((EditText) findViewById(R.id.firstName)).getText().toString();
                contactDetails.LastName = ((EditText) findViewById(R.id.lastName)).getText().toString();
                contactDetails.Company = ((EditText) findViewById(R.id.companyName)).getText().toString();
                contactDetails.Phone = ((EditText) findViewById(R.id.phoneNumber)).getText().toString();
                EditText emailText = (EditText) findViewById(R.id.emailID);
                contactDetails.Email = ((EditText) findViewById(R.id.emailID)).getText().toString();
                String email = emailText.getText().toString().trim();
                contactDetails.URL = ((EditText) findViewById(R.id.urlField)).getText().toString();
                contactDetails.Address = ((EditText) findViewById(R.id.addressField)).getText().toString();
                contactDetails.Birthday = ((EditText) findViewById(R.id.birthday)).getText().toString();
                contactDetails.NickName = ((EditText) findViewById(R.id.nickName)).getText().toString();
                contactDetails.FacebookURL = ((EditText) findViewById(R.id.facebookURL)).getText().toString();
                contactDetails.TwitterURL = ((EditText) findViewById(R.id.twitterURL)).getText().toString();
                contactDetails.Skype = ((EditText) findViewById(R.id.skypeField)).getText().toString();
                contactDetails.YoutubeChannel = ((EditText) findViewById(R.id.youtubeChannel)).getText().toString();
                if (image != null) {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    byte[] bArray = bos.toByteArray();
                    contactDetails.Image = bArray;
                } else {
                    contactDetails.Image = new byte[1];
                }
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (!contactDetails.FirstName.equals("") && !contactDetails.Email.equals("") && !contactDetails.LastName.equals("") && !contactDetails.Phone.equals("")) {
                    if (!contactDetails.Email.matches(emailPattern)) {
                        Toast.makeText(this, "Please Enter valid Email,First Name,Lastname,", Toast.LENGTH_LONG).show();
                        EditText emailID = (EditText) findViewById(R.id.emailID);
                        emailID.setText("");
                        emailID.requestFocus();
                    }else {
                        if(!isEdit) {
                            if (contactDetailsList==null){
                                contactDetailsList = new ArrayList<ContactDetails>();
                                contactDetailsList.add(contactDetails);
                            }else {
                                contactDetailsList.add(contactDetails);
                            }
                            i.putExtra(CONTACT_KEY, (Serializable) contactDetailsList);
                            i.setFlags(101);
                            startActivity(i);

                        }else {
                            contactDetailsList.set(position, contactDetails);
                            i.putExtra(CONTACT_KEY, (Serializable) contactDetailsList);
                            i.setFlags(101);
                            startActivity(i);
                        }
                    }
                }
    }

}