package com.dany.androidtest.robolectric.broadcast;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dany.androidtest.BuildConfig;
import com.dany.androidtest.broadcast.MyReceiver;
import com.thoughtworks.xstream.security.InterfaceTypePermission;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 广播测试
 * Created by dan.y on 2018/8/14.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 23)
public class MyReceiverTest {
    private final String action = "com.dany.androidtest";

    @Test
    public void testRegister() throws Exception {
        ShadowApplication shadowApplication = ShadowApplication.getInstance();
        Intent intent = new Intent(action);
        //验证是否注册了相应的Receiver
        assertTrue(shadowApplication.hasReceiverForIntent(intent));
    }

    @Test
    public void testReceive() throws Exception {
        //发送广播
        Intent intent = new Intent(action);
        intent.putExtra(MyReceiver.NAME,"AndroidUT");
        MyReceiver myReceiver = new MyReceiver();
        myReceiver.onReceive(RuntimeEnvironment.application,intent);
        //验证广播的处理逻辑是否正确
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(RuntimeEnvironment.application);
        assertEquals("AndroidUT",preferences.getString(MyReceiver.NAME,""));

    }
}
