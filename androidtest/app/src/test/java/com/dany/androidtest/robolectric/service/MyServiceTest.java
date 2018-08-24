package com.dany.androidtest.robolectric.service;

import android.app.Service;

import com.dany.androidtest.BuildConfig;
import com.dany.androidtest.service.MyService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ServiceController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

/**
 * 自定义服务测试
 * Created by dan.y on 2018/8/15.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 23)
public class MyServiceTest {
    private ServiceController<MyService> controller;
    private MyService mService;

    @Before
    public void setUp() throws Exception {
        ShadowLog.stream = System.out;
        controller = Robolectric.buildService(MyService.class);
        mService = controller.get();
    }

    /**
     *  控制Service生命周期进行验证
     * @throws Exception
     */
    @Test
    public void testServiceLifeCycle() throws Exception {
        controller.create();
        controller.startCommand(0,0);
        controller.bind();
        controller.unbind();
        controller.destroy();

    }
}
