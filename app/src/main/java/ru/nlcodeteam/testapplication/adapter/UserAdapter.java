package ru.nlcodeteam.testapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.nlcodeteam.testapplication.R;
import ru.nlcodeteam.testapplication.adapter.holder.UserHolder;
import ru.nlcodeteam.testapplication.data.model.UserModel;

/**
 * Created by el on 14.11.17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserHolder> {

    private List<UserModel> list;
    private OnItemClickListener mListener;


    public UserAdapter(List<UserModel> list, OnItemClickListener mListener) {
        this.list = list;
        this.mListener = mListener;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return  new  UserHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.user_item;
    }

    @Override
    public void onBindViewHolder(final UserHolder holder, final int position) {
        final UserModel user = list.get(position);


        holder.mName.setText(user.getName());
        holder.mAddress.setText(user.getAddress().getStreet());
        holder.mEmail.setText(user.getEmail());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null)
                    mListener.onItemClick(user,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}
