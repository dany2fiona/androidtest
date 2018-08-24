package com.dany.androidtest.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 网络请求的拦截器，用于Mock响应数据
 * Created by dan.y on 2018/8/15.
 */

public class MockInterceptor implements Interceptor {
    private final String responseString;//你要模拟返回的数据

    public MockInterceptor(String responseString){
        this.responseString = responseString;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response response = new Response.Builder()
                .code(/*0*/200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"),responseString.getBytes()))
                .addHeader("content-type", "application/json")
                .build();

        return response;
    }
}
