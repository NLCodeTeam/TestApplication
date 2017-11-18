package ru.nlcodeteam.testapplication.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import ru.nlcodeteam.testapplication.data.model.AlbumModel;
import ru.nlcodeteam.testapplication.data.model.PostModel;
import ru.nlcodeteam.testapplication.data.model.UserModel;

/**
 * Created by el on 18.11.17.
 */

public class DatabaseStorage extends SQLiteOpenHelper{

    private static DatabaseStorage ourInstance;

    public static synchronized DatabaseStorage getInstance(WeakReference<Context> context) {

        if (ourInstance == null)
            ourInstance = new DatabaseStorage(context);

        return ourInstance;
    }

    // database
    public static final String DATABASE_NAME = "Typicode.db";
    public static final int DATABASE_VERSION = 1;

    // tables
    public static final String TABLE_USERS="Users";
    public static final String TABLE_ALBUMS="Albums";
    public static final String TABLE_POSTS="Posts";


    // user fields
    public static final String FIELD_ID="Id";
    public static final String FIELD_NAME="Name";
    public static final String FIELD_ADDRESS="Address";
    public static final String FIELD_EMAIL="Email";
    public static final String FIELD_ALBUMS_LOADED="Albums_loaded";
    public static final String FIELD_POSTS_LOADED="Posts_loaded";

    // albums
    public static final String FIELD_USER_ID="User_id";
    public static final String FIELD_TITLE="Title";

    // posts
    public static final String FIELD_CONTENT="Content";



    public static String createUserTableQuery() {
        return "CREATE TABLE " + TABLE_USERS + "("
                + FIELD_ID + " INTEGER, "
                + FIELD_NAME+" TEXT NULL, "
                + FIELD_ADDRESS+" TEXT NULL, "
                + FIELD_EMAIL+ " TEXT NULL,"
                + FIELD_ALBUMS_LOADED + " INTEGER, "
                + FIELD_POSTS_LOADED + "INTEGER)";
    }

    public static String createAlbumTableQuery() {
        return "CREATE TABLE " + TABLE_ALBUMS+ "("
                + FIELD_ID + " INTEGER, "
                + FIELD_USER_ID+" INTEGER, "
                + FIELD_TITLE+" TEXT NULL)";
    }

    public static String createPostTableQuery() {
        return "CREATE TABLE " + TABLE_ALBUMS+ "("
                + FIELD_ID + " INTEGER, "
                + FIELD_USER_ID+" INTEGER, "
                + FIELD_TITLE+" TEXT NULL, "
                + FIELD_CONTENT+" TEXT NULL)";
    }

    private DatabaseStorage(WeakReference<Context> context) {
        super(context.get(), DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        createTables(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        dropTables(database);
        onCreate(database);
    }

    private void createTables(SQLiteDatabase database) {
        database.execSQL(createUserTableQuery());
        database.execSQL(createAlbumTableQuery());
        database.execSQL(createPostTableQuery());
    }

    private void dropTables(SQLiteDatabase database) {
        String drop = "DROP TABLE IF EXISTS ";
        database.execSQL(drop+TABLE_USERS);
        database.execSQL(drop+TABLE_ALBUMS);
        database.execSQL(drop+TABLE_POSTS);
    }


    public void saveUsers(List<UserModel> list) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values;

        for (UserModel user : list) {
            values = new ContentValues();
            values.put(FIELD_ID,user.id);
            values.put(FIELD_NAME, user.name);
            values.put(FIELD_ADDRESS, user.address.street);
            values.put(FIELD_EMAIL, user.email);
            values.put(FIELD_ALBUMS_LOADED,0);
            values.put(FIELD_POSTS_LOADED,0);

            database.insert(TABLE_USERS, null, values);

        }

        closeDatabase(database);
    }

    public List<UserModel> getUsers() {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(TABLE_USERS,null,null,null,null,null,null);


        List<UserModel> users = new ArrayList<>();
        if (checkCursorFirst(cursor)) {
            UserModel user = null;
            do {

                user = new UserModel();
                user.id = cursor.getInt(0);
                user.name = cursor.getString(1);
                user.address.street = cursor.getString(2);
                user.email = cursor.getString(3);

                users.add(user);
            }
            while(cursor.moveToNext());
        }

        closeCursor(cursor);
        closeDatabase(database);

        return users;
    }



    public void saveAlbums(List<AlbumModel> list) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values;

        for (AlbumModel album : list) {
            values = new ContentValues();
            values.put(FIELD_ID,album.id);
            values.put(FIELD_USER_ID, album.userId);
            values.put(FIELD_TITLE, album.title);


            database.insert(TABLE_ALBUMS, null, values);

        }

        closeDatabase(database);
    }

    public List<AlbumModel> getAlbumsByUserId(String userId) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_ALBUMS,null,
                FIELD_USER_ID + " = ? ",
                 new String[] {userId},
                null,null,null);


        List<AlbumModel> albums = new ArrayList<>();
        if (checkCursorFirst(cursor)) {
            AlbumModel album = null;
            do {

                album = new AlbumModel();
                album.id = cursor.getInt(0);
                album.userId = cursor.getInt(1);
                album.title = cursor.getString(2);

                albums.add(album);


            }while(cursor.moveToNext());
        }
        closeCursor(cursor);
        closeDatabase(database);

        return albums;
    }

    public List<PostModel> getPostsByUserId(String userId) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_POSTS,null,
                FIELD_USER_ID + " = ? ",
                new String[] {userId},
                null,null,null);


        List<PostModel> posts = new ArrayList<>();
        if (checkCursorFirst(cursor)) {
            PostModel post = null;
            do {

                post = new PostModel();
                post.id = cursor.getInt(0);
                post.userId = cursor.getInt(1);
                post.title = cursor.getString(2);
                post.body = cursor.getString(3);

                posts.add(post);


            }while(cursor.moveToNext());
        }
        closeCursor(cursor);
        closeDatabase(database);

        return posts;
    }




    public void savePosts(List<PostModel> list) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values;

        for (PostModel post : list) {
            values = new ContentValues();
            values = new ContentValues();
            values.put(FIELD_ID,post.id);
            values.put(FIELD_USER_ID, post.userId);
            values.put(FIELD_TITLE, post.title);
            values.put(FIELD_CONTENT, post.body);

            database.insert(TABLE_POSTS, null, values);

        }

        closeDatabase(database);
    }


    public boolean albumsAreLoaded(String userId) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_USERS,new String[] {FIELD_ALBUMS_LOADED},
                FIELD_ID + " = ? ",
                new String[] {userId},
                null,null,null);

        boolean loaded = false;
        if (checkCursorFirst(cursor)) {
            loaded = cursor.getInt(0) == 1 ? true : false;
        }
        closeCursor(cursor);
        closeDatabase(database);

        return loaded;
    }

    public boolean postsAreLoaded(String userId) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_USERS,new String[] {FIELD_POSTS_LOADED},
                FIELD_ID + " = ? ",
                new String[] {userId},
                null,null,null);

        boolean loaded = false;
        if (checkCursorFirst(cursor)) {
            loaded = cursor.getInt(0) == 1 ? true : false;
        }
        closeCursor(cursor);
        closeDatabase(database);

        return loaded;
    }


    public long updateAlbumsLoadedByUserId(String userId) {
        ContentValues values = new ContentValues();
        values.put(FIELD_ALBUMS_LOADED,1);

        return updateUserField(userId,values);
    }

    public long updatePostsLoadedByUserId(String userId) {
        ContentValues values = new ContentValues();
        values.put(FIELD_POSTS_LOADED,1);

        return updateUserField(userId,values);
    }




    private long updateUserField(String id, ContentValues values) {
        SQLiteDatabase database = this.getWritableDatabase();
        long count = database.update(TABLE_USERS, values,FIELD_ID + "=?", new String[]{ id });

        closeDatabase(database);
        return count;
    }


    private void closeCursor(Cursor cursor) {
        if (cursor != null && !cursor.isClosed())
            cursor.close();
    }

    private boolean checkCursorFirst(Cursor cursor) {
        if (cursor != null && cursor.moveToFirst())
            return true;
        return false;
    }


    private void closeDatabase(SQLiteDatabase database) {
        if (database != null && database.isOpen())
            database.close();
    }

}
