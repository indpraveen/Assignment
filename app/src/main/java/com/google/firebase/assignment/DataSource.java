package com.google.firebase.assignment;

import android.graphics.Bitmap;

import java.net.URL;

public class DataSource {

    String name;
    String age,location;
   Bitmap imageSource;


    public DataSource(String n,String a,String loc,Bitmap image){
        name=n;
        age=a;
        location=loc;
        imageSource =image;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getLocation() {
        return location;
    }

    public Bitmap getImageSource() {
        return imageSource;
    }
}
