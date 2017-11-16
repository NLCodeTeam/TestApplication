package ru.nlcodeteam.testapplication.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;

/**
 * Created by eldar on 29.10.2017.
 */

public class DefaultPageChange extends TabLayout.TabLayoutOnPageChangeListener {

    private FloatingActionButton button;

    public DefaultPageChange(TabLayout tabLayout, FloatingActionButton button) {
        super(tabLayout);
        this.button = button;
        button.hide();
    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        if (position == 0)
            button.hide();
        else
            button.show();
    }
}
