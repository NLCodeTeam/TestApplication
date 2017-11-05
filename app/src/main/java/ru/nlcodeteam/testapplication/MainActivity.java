package ru.nlcodeteam.testapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mNotes;
    private Button mSend;
    private boolean flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = findViewById(R.id.et_main_activity_email);
        mNotes = findViewById(R.id.et_main_activity_notes);
        mSend = findViewById(R.id.btn_main_activity_send);

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String notes = mNotes.getText().toString();

                if (TextUtils.isEmpty(email) == false &&
                    TextUtils.isEmpty(notes) == false) {
                    Toast.makeText(getApplicationContext(),email+" - "+notes,Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Заполните все поля",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
