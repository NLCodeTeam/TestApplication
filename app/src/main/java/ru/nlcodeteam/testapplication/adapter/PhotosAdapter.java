package ru.nlcodeteam.testapplication.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import ru.nlcodeteam.testapplication.R;
import ru.nlcodeteam.testapplication.data.model.PhotoModel;

/**
 * Created by el on 17.11.17.
 */

public class PhotosAdapter extends BaseAdapter {

    private WeakReference<Context> context;
    private List<PhotoModel> photos;

    public PhotosAdapter(Context context, List<PhotoModel> photos){
        this.context = new WeakReference<>(context);
        this.photos = photos;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context.get());
        ViewHolder holder = new ViewHolder();

        if (view == null) {
            view = inflater.inflate(R.layout.photo_list_item, viewGroup, false);
            holder.img = (ImageView) view.findViewById(R.id.photo_item_img);
            holder.description = (TextView) view.findViewById(R.id.photo_item_title_tw);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        holder.description.setText(photos.get(i).title);


        Log.d(context.get().getPackageName(),photos.get(i).thumbnailUrl);

       Picasso.with(context.get())
                .load(photos.get(i).thumbnailUrl)
                .into(holder.img);


        return view;
    }


    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Object getItem(int position) {
        return photos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView description;
        ImageView img;
    }
}
