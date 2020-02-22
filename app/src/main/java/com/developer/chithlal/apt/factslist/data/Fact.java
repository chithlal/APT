package com.developer.chithlal.apt.factslist.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Fact implements Parcelable {
    private final String title ;
    private final String description;
    private final String imgURL;

    private Fact(Parcel in){
        this.title = in.readString();
        this.description = in.readString();
        this.imgURL =  in.readString();
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImgURL() {
        return imgURL;
    }

    Fact(String title, String description, String imgURL) {
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
