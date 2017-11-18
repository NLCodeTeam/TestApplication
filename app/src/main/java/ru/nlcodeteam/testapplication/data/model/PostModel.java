package ru.nlcodeteam.testapplication.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by eldar on 29.10.2017.
 */

public class PostModel implements Serializable {


        @SerializedName("userId")
        public int userId;
        @SerializedName("id")
        public int id;
        @SerializedName("title")
        public String title;
        @SerializedName("body")
        public String body;

        public PostModel(int id, int userId, String title, String body) {
                this.id = id;
                this.userId = userId;
                this.title = title;
                this.body = body;
        }

        public PostModel() {
        }
}


