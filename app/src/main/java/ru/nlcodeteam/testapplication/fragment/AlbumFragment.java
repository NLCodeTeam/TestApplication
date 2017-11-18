package ru.nlcodeteam.testapplication.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import ru.nlcodeteam.testapplication.PhotoActivity;
import ru.nlcodeteam.testapplication.R;
import ru.nlcodeteam.testapplication.TestApp;
import ru.nlcodeteam.testapplication.Util;
import ru.nlcodeteam.testapplication.adapter.AlbumsAdapter;
import ru.nlcodeteam.testapplication.adapter.OnItemClickListener;
import ru.nlcodeteam.testapplication.data.model.AlbumModel;
import ru.nlcodeteam.testapplication.network.TypicodeService;

/**
 * Created by eldar on 28.10.2017.
 */

public class AlbumFragment extends Fragment implements OnItemClickListener {

    protected RecyclerView mRecyclerViewAlbums;
    private AlbumsAdapter mAdapter;
    private List<AlbumModel> mAlbums;
    int userId = -1;
    private TypicodeService mService;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_albums, container, false);
        mRecyclerViewAlbums = (RecyclerView) view.findViewById(R.id.recycler_view_albums);
        mAlbums = new ArrayList<>();
        userId = getArguments().getInt(Util.USER_ID);
        return view;
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TestApp app = (TestApp) getActivity().getApplication();
        Retrofit retrofit = app.getRetrofit();
        mService = app.getRestAPI(retrofit);



        Call<List<AlbumModel>> request = mService.getAlbumsByUser(userId);
        request.enqueue(new Callback<List<AlbumModel>>() {
            @Override
            public void onResponse(Call<List<AlbumModel>> call, Response<List<AlbumModel>> response) {
                if (response.isSuccessful()) {
                    mAlbums = response.body();
                    refreshData();
                }  else
                    showMessage("Error of loading albums");
            }

            @Override
            public void onFailure(Call<List<AlbumModel>> call, Throwable t) {
                showMessage(t.getMessage());
            }
        });



    }


    @Override
    public void onItemClick(Object object, int position) {
        AlbumModel album = (AlbumModel) object;
        Intent intent = new Intent(getActivity(), PhotoActivity.class);
        intent.putExtra(Util.ALBUM_ID,album.id);
        startActivity(intent);
    }

    private void refreshData() {
        mAdapter = new AlbumsAdapter(mAlbums,this);
        mRecyclerViewAlbums.setHasFixedSize(true);
        mRecyclerViewAlbums.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewAlbums.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerViewAlbums.setAdapter(mAdapter);
    }

    private void showMessage(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }
}
