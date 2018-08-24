package com.dany.androidtest.mockito;

import com.dany.androidtest.bean.Person;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

/**
 * 注解方法
 * Created by dan.y on 2018/8/6.
 */

public class MockitoAnnotationsTest {
    //<--使用@Mock注解
    @Mock
    Person mPerson;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);//<--初始化
    }

    @Test
    public void testIsNotNull() throws Exception {
        assertNotNull(mPerson);
    }
}
