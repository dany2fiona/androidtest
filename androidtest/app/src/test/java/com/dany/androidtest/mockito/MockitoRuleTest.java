package com.dany.androidtest.mockito;

import com.dany.androidtest.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertNotNull;

/**
 * MockitoRule方法
 * Created by dan.y on 2018/8/6.
 */

public class MockitoRuleTest {

    @Mock //<--使用@Mock注解
    Person mPerson;

    @Rule //<--使用@Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }
}
