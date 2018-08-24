package com.dany.androidtest.net;

import android.util.Log;

import com.dany.androidtest.BuildConfig;
import com.dany.androidtest.bean.User;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by dan.y on 2018/8/20.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 23)
public class MockWebServerTest {

    private GithubApi mockGithubService;
    private MockWebServer server;

    @Rule
    public RxJavaRule rule = new RxJavaRule();

    @Before
    public void setUp() throws Exception {
        ShadowLog.stream = System.out;

        //创建一个MockWebServer
        server = new MockWebServer();

        Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                if(request.getPath().equals("/users/dany2fiona")){
                    return new MockResponse()
                            .addHeader("Content-Type","application/json;charset=utf-8")
                            .addHeader("Cache-Control","no-cache")
                            .setBody("{\"login\": \"dany2fiona\", \"id\": 20991725, \"blog\": \"\"}");
                }else{
                    return new MockResponse()
                            .addHeader("Content-Type","application/json;charset=utf-8")
                            .setResponseCode(404)
                            .throttleBody(5,1, TimeUnit.SECONDS)//一秒传递5个字节，模拟网速慢的情况
                            .setBody("{\"error\": \"网络异常\"}");
                }
            }
        };
//
//        //设置响应，默认返回http code是 200
//        MockResponse mockResponse = new MockResponse()
//                .addHeader("Content-Type","application/json;charset=utf-8")
//                .addHeader("Cache-Control","no-cache")
//                .setBody("{\"login\": \"dany2fiona\", \"id\": 20991725, \"blog\": \"\"}");
//
//        MockResponse mockResponse1 = new MockResponse()
//                .addHeader("Content-Type","application/json;charset=utf-8")
//                .setResponseCode(404)
//                .throttleBody(5,1, TimeUnit.SECONDS)//一秒传递5个字节，模拟网速慢的情况
//                .setBody("{\"error\": \"网络异常\"}");
//
//        server.enqueue(mockResponse);//成功响应
//        server.enqueue(mockResponse1);//失败响应
        server.setDispatcher(dispatcher);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + server.getHostName() + ":" + server.getPort() + "/")//设置对应的Host与端口号
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mockGithubService = retrofit.create(GithubApi.class);

    }

    @Test
    public void getUserTest() throws Exception {
        //请求不变
        mockGithubService.getUser("dany2fiona")
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

        //验证我们的请求客户端是否按预期生成了请求
        RecordedRequest request = server.takeRequest();
        assertEquals("GET /users/dany2fiona HTTP/1.1",request.getRequestLine());
        assertEquals("okhttp/3.9.1",request.getHeader("User-Agent"));

        //关闭服务
        server.shutdown();
    }
}
