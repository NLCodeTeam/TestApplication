package ru.nlcodeteam.testapplication.adapter.holder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.nlcodeteam.testapplication.R;

/**
 * Created by eldar on 29.10.2017.
 */

public class TextViewHolder extends RecyclerView.ViewHolder {
    public TextView mTitle;
    public TextViewHolder(View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.album_title_tw);
    }
}
