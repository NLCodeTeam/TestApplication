package ru.nlcodeteam.testapplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by el on 15.11.17.
 */

public class Util {
    public static final String POST ="Post";
    public static final String POSITION="Pos";
    public static final String MODE="Mode";
    public static final String USER_ID = "UserId";

    public static final int ADD_POST= 1;
    public static final int EDIT_POST= 2;

    public static final String TITLE = "Title";
    public static final String ACTION_POST_ADDED = "PostAdded";
    public static final String ALBUM_ID = "albumId";
    public static final int REQUEST_CODE = 111;

    private final static String pivotalFormatShort = "dd.MM.yyyy";

    public static String getToday() {
        return new SimpleDateFormat(pivotalFormatShort, Locale.getDefault()).format(new Date());
    }

}
