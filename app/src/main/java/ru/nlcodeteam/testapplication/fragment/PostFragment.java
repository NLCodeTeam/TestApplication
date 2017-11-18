package ru.nlcodeteam.testapplication.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.nlcodeteam.testapplication.R;
import ru.nlcodeteam.testapplication.TestApp;
import ru.nlcodeteam.testapplication.Util;
import ru.nlcodeteam.testapplication.adapter.OnItemClickListener;
import ru.nlcodeteam.testapplication.adapter.PostsAdapter;
import ru.nlcodeteam.testapplication.data.model.PostModel;
import ru.nlcodeteam.testapplication.network.TypicodeService;

/**
 * Created by eldar on 28.10.2017.
 */

public class PostFragment extends Fragment implements OnItemClickListener {
    protected RecyclerView mRecyclerViewPosts;
    private PostsAdapter mAdapter;
    private List<PostModel> mPosts;
    private int userId = -1;
    private TypicodeService mService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_posts, container, false);
        mPosts = new ArrayList<>();
        mAdapter = new PostsAdapter(mPosts,this);
        mRecyclerViewPosts = view.findViewById(R.id.recycler_view_posts);
        userId = getArguments().getInt(Util.USER_ID);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TestApp app = (TestApp) getActivity().getApplication();
        Retrofit retrofit = app.getRetrofit();
        mService = app.getRestAPI(retrofit);

        Call<List<PostModel>> request = mService.getPostsByUser(userId);


        request.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (response.isSuccessful()) {
                    mPosts = response.body();
                    refreshData();
                }  else
                    showMessage("Error of loading posts");
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                  showMessage(t.getMessage());
            }
        });

    }

    @Override
    public void onItemClick(Object object, int position) {
       /* TODO implements if need
       Post post = (Post) object;
       Intent intent = new Intent(getContext(), PostEditActivity.class);
       intent.putExtra(Util.POST,post);
       intent.putExtra(Util.MODE,Util.EDIT_POST);
       intent.putExtra(Util.POSITION,position);
       startActivityForResult(intent,requestCode);*/
    }







    private void refreshData() {
        mAdapter = new PostsAdapter(mPosts,this);
        mRecyclerViewPosts.setHasFixedSize(true);
        mRecyclerViewPosts.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewPosts.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerViewPosts.setAdapter(mAdapter);
    }

    private void showMessage(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(refreshReciever);
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(refreshReciever,new IntentFilter(Util.ACTION_POST_ADDED));

    }

    BroadcastReceiver refreshReciever =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            PostModel post = (PostModel) intent.getSerializableExtra(Util.POST);
            mPosts.add(post);
            refreshData();
        }
    };
}
