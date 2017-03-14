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
    /**
     * Creates a user Constructor
     */
    public User() {}
    /**
     * Creates a user Constructor
     * @param email to store user email
     * @param address to store user address
     * @param userType  to store user type
     */
    public User(String email, String address, UserType userType) {
        this.email = email;
        this.address = address;
        this.userType = userType;
    }
    /**
     * Getter method for User Type
     * @return the User Type
     */
    public UserType getUserType() {
        return userType;
    }
    /**
     * Setter method for User type
     * @param userType an object of UserType to set the User type
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    /**
     * Getter method for User Email
     * @return the User Email
     */
    public String getEmail() {
        return email;
    }
    /**
     * Setter method for User email
     * @param email a String variable to set email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Getter method for User address
     * @return the User address
     */
    public String getUserAddress() {
        return address;
    }
    /**
     * Setter method for User address
     * @param address a String variable to set address
     */
    public void setUserAddress(String address) {
        this.address = address;
    }



    /**
     * Creates a user Constructor
     * @param in of type Parcel which takes in user type, email and address
     *           as inputs from the user
     */

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