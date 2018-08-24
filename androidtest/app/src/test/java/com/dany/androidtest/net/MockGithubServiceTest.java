package com.dany.androidtest.net;

import android.util.Log;

import com.dany.androidtest.BuildConfig;
import com.dany.androidtest.bean.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by dan.y on 2018/8/15.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 23)
public class MockGithubServiceTest {

    private GithubApi mockGithubService;


    /*{
        "login": "dany2fiona",
            "id": 20991725,
            "node_id": "MDQ6VXNlcjIwOTkxNzI1",
            "avatar_url": "https:\/\/avatars1.githubusercontent.com\/u\/20991725?v=4",
            "gravatar_id": "",
            "url": "https:\/\/api.github.com\/users\/dany2fiona",
            "html_url": "https:\/\/github.com\/dany2fiona",
            "followers_url": "https:\/\/api.github.com\/users\/dany2fiona\/followers",
            "following_url": "https:\/\/api.github.com\/users\/dany2fiona\/following{\/other_user}",
            "gists_url": "https:\/\/api.github.com\/users\/dany2fiona\/gists{\/gist_id}",
            "starred_url": "https:\/\/api.github.com\/users\/dany2fiona\/starred{\/owner}{\/repo}",
            "subscriptions_url": "https:\/\/api.github.com\/users\/dany2fiona\/subscriptions",
            "organizations_url": "https:\/\/api.github.com\/users\/dany2fiona\/orgs",
            "repos_url": "https:\/\/api.github.com\/users\/dany2fiona\/repos",
            "events_url": "https:\/\/api.github.com\/users\/dany2fiona\/events{\/privacy}",
            "received_events_url": "https:\/\/api.github.com\/users\/dany2fiona\/received_events",
            "type": "User",
            "site_admin": false,
            "name": null,
            "company": null,
            "blog": "",
            "location": null,
            "email": null,
            "hireable": null,
            "bio": null,
            "public_repos": 16,
            "public_gists": 0,
            "followers": 2,
            "following": 2,
            "created_at": "2016-08-12T13:35:36Z",
            "updated_at": "2018-02-22T08:41:18Z"
    }
*/
    String maockResponseJson = "{\n" +
            "        \"login\": \"dany2fiona\",\n" +
            "            \"id\": 20991725,\n" +
            "            \"node_id\": \"MDQ6VXNlcjIwOTkxNzI1\",\n" +
            "            \"avatar_url\": \"https:\\/\\/avatars1.githubusercontent.com\\/u\\/20991725?v=4\",\n" +
            "            \"gravatar_id\": \"\",\n" +
            "            \"url\": \"https:\\/\\/api.github.com\\/users\\/dany2fiona\",\n" +
            "            \"html_url\": \"https:\\/\\/github.com\\/dany2fiona\",\n" +
            "            \"followers_url\": \"https:\\/\\/api.github.com\\/users\\/dany2fiona\\/followers\",\n" +
            "            \"following_url\": \"https:\\/\\/api.github.com\\/users\\/dany2fiona\\/following{\\/other_user}\",\n" +
            "            \"gists_url\": \"https:\\/\\/api.github.com\\/users\\/dany2fiona\\/gists{\\/gist_id}\",\n" +
            "            \"starred_url\": \"https:\\/\\/api.github.com\\/users\\/dany2fiona\\/starred{\\/owner}{\\/repo}\",\n" +
            "            \"subscriptions_url\": \"https:\\/\\/api.github.com\\/users\\/dany2fiona\\/subscriptions\",\n" +
            "            \"organizations_url\": \"https:\\/\\/api.github.com\\/users\\/dany2fiona\\/orgs\",\n" +
            "            \"repos_url\": \"https:\\/\\/api.github.com\\/users\\/dany2fiona\\/repos\",\n" +
            "            \"events_url\": \"https:\\/\\/api.github.com\\/users\\/dany2fiona\\/events{\\/privacy}\",\n" +
            "            \"received_events_url\": \"https:\\/\\/api.github.com\\/users\\/dany2fiona\\/received_events\",\n" +
            "            \"type\": \"User\",\n" +
            "            \"site_admin\": false,\n" +
            "            \"name\": null,\n" +
            "            \"company\": null,\n" +
            "            \"blog\": \"\",\n" +
            "            \"location\": null,\n" +
            "            \"email\": null,\n" +
            "            \"hireable\": null,\n" +
            "            \"bio\": null,\n" +
            "            \"public_repos\": 16,\n" +
            "            \"public_gists\": 0,\n" +
            "            \"followers\": 2,\n" +
            "            \"following\": 2,\n" +
            "            \"created_at\": \"2016-08-12T13:35:36Z\",\n" +
            "            \"updated_at\": \"2018-02-22T08:41:18Z\"\n" +
            "    }";

    String errorResponseJson = "{\"error\":\"服务器异常\"}";

    @Rule
    public RxJavaRule rule = new RxJavaRule();

    @Before
    public void setUp() throws Exception {
        ShadowLog.stream = System.out;

        //定义Http Client,并添加拦截器
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new MockInterceptor(/*"json数据"*//*errorResponseJson*/maockResponseJson))//<-- 添加拦截器
                .build();

        //设置Http Client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubApi.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mockGithubService = retrofit.create(GithubApi.class);
    }

    @Test
    public void getUserTest() throws Exception {
        mockGithubService.getUser("df")//<-- 这里传入错误的用户名
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        assertEquals("dany2fiona",user.login);
                        assertEquals("",user.blog);
                        assertEquals(20991725,user.id);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("dan.test",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
