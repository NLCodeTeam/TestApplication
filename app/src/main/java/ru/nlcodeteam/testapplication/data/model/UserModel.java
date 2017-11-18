package ru.nlcodeteam.testapplication.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by eldar on 29.10.2017.
 */

public class UserModel implements Serializable {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("username")
    public String username;
    @SerializedName("email")
    public String email;
    @SerializedName("address")
    public Address address;
    @SerializedName("phone")
    public String phone;
    @SerializedName("website")
    public String website;
    @SerializedName("company")
    public Company company;

    public static class Geo implements Serializable {
        @SerializedName("lat")
        public String lat;
        @SerializedName("lng")
        public String lng;
    }

    public static class Address implements Serializable{
        @SerializedName("street")
        public String street;
        @SerializedName("suite")
        public String suite;
        @SerializedName("city")
        public String city;
        @SerializedName("zipcode")
        public String zipcode;
        @SerializedName("geo")
        public Geo geo;
    }

    public static class Company implements Serializable{
        @SerializedName("name")
        public String name;
        @SerializedName("catchPhrase")
        public String catchPhrase;
        @SerializedName("bs")
        public String bs;
    }



    public String[] getMappingItems() {
        return new String[] {
                this.name,this.address.street,this.email
        };
    }
}
