package com.dany.androidtest.mockito;

import com.dany.androidtest.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.after;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 常用验证方法
 * Created by dan.y on 2018/8/6.
 */

public class MockitoVerifyTest {

    @Mock
    Person mPerson;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testPersonVerifyAfter() throws Exception {
        when(mPerson.getAge()).thenReturn(18);
        //延时1s验证
        System.out.println(mPerson.getAge());
        System.out.println(System.currentTimeMillis());
        verify(mPerson,after(1000)).getAge();
        System.out.println(System.currentTimeMillis());
//抛出异常
//        verify(mPerson,atLeast(2)).getAge();
    }

    @Test
    public void testPersonVerifyAtLeast() throws Exception {
        mPerson.getAge();
        mPerson.getAge();
        //至少验证2次
        verify(mPerson,atLeast(2)).getAge();
    }

    @Test
    public void testPersonVerifyAtMost() throws Exception {
        mPerson.getAge();
        mPerson.getAge();
        //至多验证2次
        verify(mPerson,atMost(2)).getAge();
    }

    @Test
    public void testPersonVerifyTimes() throws Exception {
        mPerson.getAge();
        mPerson.getAge();
//        //验证方法调用两次
//        verify(mPerson,times(2)).getAge();

        //验证方法在100ms超时前调用2次
        verify(mPerson,timeout(100).times(2)).getAge();
    }

}
