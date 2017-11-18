package ru.nlcodeteam.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostEditActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    protected FloatingActionButton fab;

    @BindView(R.id.post_title_et)
    TextInputEditText mTitle;

    @BindView(R.id.post_content_et)
    TextInputEditText mBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_edit);
        ButterKnife.bind(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mTitle.getText().toString();
                String content = mBody.getText().toString();

                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
                    Intent intent = new Intent();
                    intent.putExtra(Util.TITLE,title);
                    intent.putExtra(Util.POST,content);
                    setResult(RESULT_OK,intent);
                    finish();
                }

            }
        });
    }

}
