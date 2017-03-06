package com.StrangerPings2340.app;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rishab on 2/24/2017.
 */

public class User implements Parcelable {
    private UserType userType;
    private String email;
    private String address;

    public User() {}

    public User(String email, String address, UserType userType) {
        this.email = email;
        this.address = address;
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getUserAddress() {
        return address;
    }

    public void setUserAddress(String address) {
        this.address = address;
    }





    protected User(Parcel in) {
        userType = (UserType) in.readValue(UserType.class.getClassLoader());
        email = in.readString();
        address = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userType);
        dest.writeString(email);
        dest.writeString(address);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String toString() {
        return email + ", " + address + ", " + userType.toString();
    }
}