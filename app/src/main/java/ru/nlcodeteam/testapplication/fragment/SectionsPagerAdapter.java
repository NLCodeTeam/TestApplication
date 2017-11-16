package ru.nlcodeteam.testapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.nlcodeteam.testapplication.Util;

/**
 * Created by eldar on 28.10.2017.
 */


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    int userId;
    public SectionsPagerAdapter(FragmentManager fm, int userId) {
        super(fm);
        this.userId = userId;
    }

    @Override
    public Fragment getItem(int position) {

       Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new AlbumFragment();
                fragment.setArguments(getArgs());
                break;
            case 1:
                fragment = new PostFragment();
                fragment.setArguments(getArgs());
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    private Bundle getArgs() {
        Bundle bundle = new Bundle();
        bundle.putInt(Util.USER_ID,userId);
        return bundle;
    }
}