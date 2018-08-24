package com.dany.androidtest.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dan.y on 2018/8/15.
 */

public class GithubService {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(GithubApi.BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public static GithubApi createGithubService(){
        return retrofit.create(GithubApi.class);
    }

    private static OkHttpClient getOkHttpClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();
    }
}
