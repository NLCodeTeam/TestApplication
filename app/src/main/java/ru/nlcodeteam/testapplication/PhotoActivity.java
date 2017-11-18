package ru.nlcodeteam.testapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.nlcodeteam.testapplication.adapter.PhotosAdapter;
import ru.nlcodeteam.testapplication.data.model.PhotoModel;
import ru.nlcodeteam.testapplication.network.TypicodeService;

public class PhotoActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    protected ListView mListView;
    private PhotosAdapter mAdapter;
    private List<PhotoModel> mPhotos;


    TypicodeService mService;

    private int albumId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        mPhotos = new ArrayList<>();
        albumId = getIntent().getIntExtra(Util.ALBUM_ID,-1);

        TestApp app = (TestApp) getApplication();
        Retrofit retrofit = app.getRetrofit();
        mService = app.getRestAPI(retrofit);
        loadPhotos(mService);

    }




    private void loadPhotos(TypicodeService typicodeService) {

        Call<List<PhotoModel>> request = typicodeService.getPhotosByAlbum(albumId);
        request.enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                if (response.isSuccessful()) {
                    mPhotos = response.body();
                    refreshData();
                }  else
                    showMessage("Error of loading photos");
            }

            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
                 showMessage(t.getMessage());
            }
        });

    }


    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    private void refreshData() {
        mAdapter = new PhotosAdapter(getApplicationContext(),mPhotos);
        mListView.setAdapter(mAdapter);
    }
}
