package ru.nlcodeteam.testapplication.data.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;

/**
 * Created by el on 19.11.17.
 */

public class PreferenceUtil {
    private static PreferenceUtil ourInstance;

    public static synchronized PreferenceUtil getInstance(WeakReference<Context> context) {

        if (ourInstance == null)
            ourInstance = new PreferenceUtil(context);

        return ourInstance;
    }

    private WeakReference<Context> mContext;
    private PreferenceUtil(WeakReference<Context> context) {
         this.mContext = context;
    }

    private static  final String FIELD_DATE = "Date";

    public boolean saveDate(String today) {
        boolean success = false;

        if (mContext.get() != null) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext.get());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(FIELD_DATE, today);
            editor.apply();
            success = true;
        }

        return success;
    }


    public String getDate() {
        if (mContext.get() != null) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext.get());
            return  preferences.getString(FIELD_DATE, "");
        }

        return "";
    }
}
