package com.dany.androidtest.net;

import com.dany.androidtest.bean.User;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dan.y on 2018/8/15.
 */

public interface GithubApi {

    String BASE_URL = "https://api.github.com/";

    @GET("users/{username}")
    Observable<User> getUser(@Path("username") String username);
}
