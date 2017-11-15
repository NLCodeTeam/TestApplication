package ru.nlcodeteam.testapplication.network;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.nlcodeteam.testapplication.data.AlbumModel;
import ru.nlcodeteam.testapplication.data.PhotoModel;
import ru.nlcodeteam.testapplication.data.PostModel;
import ru.nlcodeteam.testapplication.data.UserModel;

/**
 * Created by eldar on 29.10.2017.
 */

public interface TypicodeService {

        String BASE_URL = "https://jsonplaceholder.typicode.com";
        String USERS_URL=BASE_URL+"/users";
        String POSTS_URL =USERS_URL+"/{userId}/posts";
        String ALBUMS_URL=USERS_URL+"/{userId}/albums";
        String PHOTOS_URL=BASE_URL+"/photos";


        @GET(USERS_URL)
        Call<List<UserModel>> getUsers();

        @GET(ALBUMS_URL)
        Call<List<AlbumModel>> getAlbumsByUser(@Path("userId") int userId);

        @GET(POSTS_URL)
        Call<List<PostModel>> getPostsByUser(@Path("userId") int userId);

        @GET(PHOTOS_URL)
        Call<List<PhotoModel>> getPhotosByAlbum(@Query("albumId") int albumId);

        @POST("/posts")
        Call<PostModel> addPost(@Body PostModel post);
}
