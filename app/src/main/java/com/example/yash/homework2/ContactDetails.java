//Assignment Homework 2
//ContactDetails
//Prabhakar Teja Seeda,Yash Ghia
package com.example.yash.homework2;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;


public class ContactDetails implements Serializable {
    String FirstName, LastName, Company, Email, URL, Address, FacebookURL, TwitterURL, Skype, YoutubeChannel,NickName;
    String Birthday;
    String Phone;

    public byte[] getImage() {
        return Image;
    }

    byte[] Image;
    public String getFirstName() {
        return FirstName;
    }
    public String getLastName() {
        return LastName;
    }
    public String getPhone(){
        return Phone;
    }
    public String getYoutubeChannel() {return YoutubeChannel;}
    public String getSkype() {return Skype;}

    public String getAddress() {
        return Address;
    }

    public String getTwitterURL() {return TwitterURL;}
    public String getFacebookURL() {return FacebookURL;}
    public String getURL() {return URL;}
    public String getEmail(){return Email;}


}
