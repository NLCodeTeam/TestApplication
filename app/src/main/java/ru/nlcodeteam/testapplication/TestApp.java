package ru.nlcodeteam.testapplication;

import android.app.Application;

import java.lang.ref.WeakReference;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.nlcodeteam.testapplication.data.database.DatabaseStorage;
import ru.nlcodeteam.testapplication.data.preference.PreferenceUtil;
import ru.nlcodeteam.testapplication.network.TypicodeService;
import rx.schedulers.Schedulers;


/**
 * Created by el on 12.11.17.
 */

public class TestApp extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
    }

    public TypicodeService getRestAPI(Retrofit retrofit) {
        return retrofit.create(TypicodeService.class);
    }



    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .baseUrl(TypicodeService.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    public DatabaseStorage getStorage() {
        return DatabaseStorage.getInstance(new WeakReference<>(getApplicationContext()));
    }

    public PreferenceUtil getPreference() {
        return PreferenceUtil.getInstance(new WeakReference<>(getApplicationContext()));
    }

    public boolean areRelevantData() {
        String today = Util.getToday();
        String savedDate = getPreference().getDate();

        return today.compareTo(savedDate) == 0;
    }
}
