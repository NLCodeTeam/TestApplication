package ru.nlcodeteam.testapplication.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eldar on 29.10.2017.
 */
 public class AlbumModel {
        @SerializedName("userId")
        public int userId;
        @SerializedName("id")
        public int id;
        @SerializedName("title")
        public String title;
    }


