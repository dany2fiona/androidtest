package com.dany.androidtest.robolectric.shadow;

import android.util.Log;

import com.dany.androidtest.BuildConfig;
import com.dany.androidtest.bean.Person;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static org.junit.Assert.assertEquals;
import static org.robolectric.shadow.api.Shadow.extract;

/**
 * 自定义Shadow测试
 * Created by dan.y on 2018/8/15.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = 23,
        shadows = {ShadowPerson.class})
public class ShadowTest {
    private final static String TAG  = "dan.y";

    @Before
    public void setUp() throws Exception {
        ShadowLog.stream = System.out;
    }

    @Test
    public void testShadowShadow() throws Exception {
        Person person = new Person();
        //实际滴啊用的是ShadowPerson的方法
        Log.d(TAG,person.getName());
        Log.d(TAG,person.getAge()+"");
        Log.d(TAG,person.getSex()+"");

        //获取Person对象对应的Shadow对象
        ShadowPerson shadowPerson = extract(person);
        assertEquals("AndroidUT",shadowPerson.getName());
        assertEquals(18,shadowPerson.getAge());
        assertEquals(0,shadowPerson.getSex());
    }
}
