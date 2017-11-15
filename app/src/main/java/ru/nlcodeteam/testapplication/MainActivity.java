package ru.nlcodeteam.testapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.nlcodeteam.testapplication.adapter.OnItemClickListener;
import ru.nlcodeteam.testapplication.adapter.UserAdapter;
import ru.nlcodeteam.testapplication.data.UserModel;
import ru.nlcodeteam.testapplication.network.TypicodeService;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    @BindView(R.id.recycler_view_users)
    protected RecyclerView mRecyclerViewUsers;
    private UserAdapter mAdapter;
    private List<UserModel> users;

    private TypicodeService mService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);

        TestApp app = (TestApp) getApplication();
        Retrofit retrofit = app.getRetrofit();
        mService = app.getRestAPI(retrofit);

        Call<List<UserModel>> request = mService.getUsers();

        request.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if (response.isSuccessful()) {
                    users = response.body();
                    refreshData();

                } else {
                   showMessage("Error of loading users");
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                 showMessage(t.getMessage());
            }
        });
    }


    @Override
    public void onItemClick(Object obj,int position) {
        UserModel user = (UserModel) obj;
        Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
        intent.putExtra(Util.USER_ID,user);
        startActivity(intent);
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    private void refreshData() {
        mRecyclerViewUsers.setHasFixedSize(true);
        mRecyclerViewUsers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new UserAdapter(users,this);
        mRecyclerViewUsers.setAdapter(mAdapter);
    }
}
