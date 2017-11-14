package ru.nlcodeteam.testapplication.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eldar on 29.10.2017.
 */

public class PhotoModel {
    @SerializedName("albumId")
    public int albumId;
    @SerializedName("id")
    public int id;
    @SerializedName("title")
    public String title;
    @SerializedName("url")
    public String url;
    @SerializedName("thumbnailUrl")
    public String thumbnailUrl;
}
