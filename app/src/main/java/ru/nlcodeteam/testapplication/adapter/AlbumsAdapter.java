package ru.nlcodeteam.testapplication.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.nlcodeteam.testapplication.R;
import ru.nlcodeteam.testapplication.adapter.holder.TextViewHolder;
import ru.nlcodeteam.testapplication.data.model.AlbumModel;

/**
 * Created by eldar on 29.10.2017.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<TextViewHolder> {

    private List<AlbumModel> albums;
    private OnItemClickListener listener;

    public AlbumsAdapter(List<AlbumModel> albums, OnItemClickListener listener) {
        this.albums = albums;
        this.listener = listener;
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        TextViewHolder holder = new  TextViewHolder(view);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.album_item;
    }

    @Override
    public void onBindViewHolder(final TextViewHolder holder, final int position) {
        final AlbumModel album = albums.get(position);
        holder.mTitle.setText(album.title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onItemClick(album,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }
}
