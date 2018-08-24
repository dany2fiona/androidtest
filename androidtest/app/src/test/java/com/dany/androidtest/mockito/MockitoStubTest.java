package com.dany.androidtest.mockito;

import com.dany.androidtest.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * 常用打桩方法示例
 * Created by dan.y on 2018/8/6.
 */

public class MockitoStubTest {

    @Mock
    Person mPerson;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testPersonReturn() throws Exception {
        when(mPerson.getName()).thenReturn("小明");
        when(mPerson.getSex()).thenThrow(new NullPointerException("滑稽，性别不明"));

        //输出"小明"
        System.out.println(mPerson.getName());

        doReturn("小小").when(mPerson).getName();
        System.out.println(mPerson.getName());//输出"小小"

//        //抛出异常
//        System.out.println(mPerson.getSex());
    }

    @Test
    public void testPersonAnswer() throws Exception {
        when(mPerson.eat(anyString())).thenAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return args[0].toString() + "真好吃";
            }
        });
        //输出，饺子真好吃
        System.out.println(mPerson.eat("饺子"));
    }
}
