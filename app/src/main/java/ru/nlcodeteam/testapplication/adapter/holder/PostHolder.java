package ru.nlcodeteam.testapplication.adapter.holder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.nlcodeteam.testapplication.R;

/**
 * Created by eldar on 29.10.2017.
 */

public class PostHolder extends RecyclerView.ViewHolder {
    public TextView mTitle,mContent;
    public PostHolder(View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.post_title_tw);
        mContent = itemView.findViewById(R.id.post_content_tw);
    }
}
