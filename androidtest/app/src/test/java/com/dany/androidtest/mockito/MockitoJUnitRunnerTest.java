package com.dany.androidtest.mockito;

import com.dany.androidtest.bean.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

/**
 * 运行器方法
 * Created by dan.y on 2018/8/6.
 */
@RunWith(MockitoJUnitRunner.class)  //<--使用MockitoJUnitRunner
public class MockitoJUnitRunnerTest {

    @Mock  //<--使用@Mock注解
    Person mPerson;

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }
}
