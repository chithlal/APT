package com.developer.chithlal.apt.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Fact implements Parcelable {
    private String title ;
    private String description;
    private String imgURL;

    public Fact(Parcel in){
        this.title = in.readString();
        this.description = in.readString();
        this.imgURL =  in.readString();
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Fact(String title, String description, String imgURL) {
        this.title = title;
        this.description = description;
        this.imgURL = imgURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.imgURL);

    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Fact createFromParcel(Parcel in) {
            return new Fact(in);
        }

        public Fact[] newArray(int size) {
            return new Fact[size];
        }
    };
}
