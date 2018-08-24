package com.dany.androidtest.junit;

import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

/**
 * Created by dan.y on 2018/8/6.
 */

public class AssertThatTest {

    @Rule
    public MyRule rule = new MyRule();

    @Test
    public void testAssertThat() throws Exception{
        assertThat(5,is(5));
    }

    @Test
    public void testAssertThat2() throws Exception{
        assertThat("2",nullValue());
    }

    @Test
    public void testAssertThat3() throws Exception{
        assertThat("hello UT",both(startsWith("hello")).and(endsWith("AUT")));
    }

    @Test
    public void testMobilePhone() throws Exception {
//        assertThat("13588888888",new IsMobilePhoneMatcher());
        assertThat("19588888888",new IsMobilePhoneMatcher());
    }
}
