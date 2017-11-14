package ru.nlcodeteam.testapplication.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eldar on 29.10.2017.
 */

public class UserModel {

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

    public static class Geo {
        @SerializedName("lat")
        public String lat;
        @SerializedName("lng")
        public String lng;
    }

    public static class Address {
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

    public static class Company {
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
