package com.dany.androidtest.mockito;

import com.dany.androidtest.bean.Home;
import com.dany.androidtest.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by dan.y on 2018/8/7.
 */

public class MockitoSpyTest {
    @Spy
    Person mPerson;

    @InjectMocks
    Home mHome;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testIsNotNull() throws Exception {
        assertNotNull(mPerson);
    }

    @Test
    public void testPersonSpy() throws Exception {
        //输出11
        System.out.println(mPerson.getAge());
    }

    @Test
    public void testHomeInjectMocks() throws Exception {
        when(mPerson.getName()).thenReturn("dan.y");
        System.out.println(mHome.getMaster());
    }
}
