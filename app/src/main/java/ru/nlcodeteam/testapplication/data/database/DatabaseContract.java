package ru.nlcodeteam.testapplication.data.database;

/**
 * Created by el on 19.11.17.
 */

public class DatabaseContract {
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
                + FIELD_POSTS_LOADED + " INTEGER)";
    }

    public static String createAlbumTableQuery() {
        return "CREATE TABLE " + TABLE_ALBUMS+ "("
                + FIELD_ID + " INTEGER, "
                + FIELD_USER_ID+" INTEGER, "
                + FIELD_TITLE+" TEXT NULL)";
    }

    public static String createPostTableQuery() {
        return "CREATE TABLE " + TABLE_POSTS+ "("
                + FIELD_ID + " INTEGER, "
                + FIELD_USER_ID+" INTEGER, "
                + FIELD_TITLE+" TEXT NULL, "
                + FIELD_CONTENT+" TEXT NULL)";
    }
}
