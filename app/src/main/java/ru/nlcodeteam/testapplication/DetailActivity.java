package ru.nlcodeteam.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.nlcodeteam.testapplication.data.model.PostModel;
import ru.nlcodeteam.testapplication.data.model.UserModel;
import ru.nlcodeteam.testapplication.fragment.DefaultPageChange;
import ru.nlcodeteam.testapplication.fragment.SectionsPagerAdapter;
import ru.nlcodeteam.testapplication.network.TypicodeService;

public class DetailActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private int userId;
    private UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = (UserModel) getIntent().getSerializableExtra(Util.USER_ID);
        if (user != null) {
            userId = user.id;
            toolbar.setTitle(user.name);
        }


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),userId);


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), PostEditActivity.class),Util.REQUEST_CODE);
            }
        });

        mViewPager.addOnPageChangeListener(new DefaultPageChange(tabLayout,fab));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Util.REQUEST_CODE) {

            String title = data.getStringExtra(Util.TITLE);
            String content = data.getStringExtra(Util.POST);
            PostModel post = new PostModel();
            post.title = title;
            post.body = content;
            post.userId = userId;

            TestApp app = (TestApp) getApplication();
            Retrofit retrofit = app.getRetrofit();
            TypicodeService mService = app.getRestAPI(retrofit);



            Call<PostModel> request = mService.addPost(post);
            request.enqueue(new Callback<PostModel>() {
                @Override
                public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                    if (response.isSuccessful()) {
                        PostModel model = response.body();
                        PostModel post = new PostModel(model.id,model.userId,model.title,model.body);
                        Intent intent = new Intent(Util.ACTION_POST_ADDED);
                        intent.putExtra(Util.POST,post);
                        LocalBroadcastManager.getInstance(DetailActivity.this).sendBroadcast(intent);
                    }
                    else
                        showMessage("The post was not added");
                }

                @Override
                public void onFailure(Call<PostModel> call, Throwable t) {
                    showMessage(t.getMessage());
                }
            });
        }
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}
