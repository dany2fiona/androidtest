package com.dany.androidtest.mockito;

import com.dany.androidtest.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;

/**
 * 常用参数匹配器示例
 * Created by dan.y on 2018/8/6.
 */

public class MockitoMatchersTest {

    @Mock
    Person mPerson;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testPersonAny() throws Exception {
        when(mPerson.eat(any(String.class))).thenReturn("米饭");
        //或
//        when(mPerson.eat(anyString())).thenReturn("米饭");
        //输出米饭
        System.out.println(mPerson.eat("面条"));
    }

    @Test
    public void testPersonContains() throws Exception {
        when(mPerson.eat(contains("面"))).thenReturn("面条");
        //输出面条
        System.out.println(mPerson.eat("面"));
    }

    @Test
    public void testPersonArgThat() throws Exception {
        //自定义输入字符长度为偶数时，输出面条。
        when(mPerson.eat(argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(String argument) {
                return argument.length() %2 == 0;
            }
        }))).thenReturn("面条");
        //输出面条
        System.out.println(mPerson.eat("1234"));
    }
}
