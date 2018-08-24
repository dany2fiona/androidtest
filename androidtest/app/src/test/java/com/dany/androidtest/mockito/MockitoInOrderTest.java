package com.dany.androidtest.mockito;

import com.dany.androidtest.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.inOrder;

/**
 * Created by dan.y on 2018/8/7.
 */

public class MockitoInOrderTest {

    @Mock
    Person mPerson;

    @Mock
    Person mPerson1;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testPersonInOrder() throws Exception {
        mPerson.setName("小明");
        mPerson.setSex(1);

        mPerson1.setName("小红");
        mPerson1.setSex(0);

        InOrder mInOrder = inOrder(mPerson,mPerson1);
        //执行顺序正确
        mInOrder.verify(mPerson).setName("小明");
        mInOrder.verify(mPerson).setSex(1);
        //执行顺序错误
        mInOrder.verify(mPerson1).setSex(0);
        mInOrder.verify(mPerson1).setName("小红");
    }
}
