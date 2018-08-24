package com.dany.androidtest.mockito;

import com.dany.androidtest.bean.Person;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * 测试的类之间会有或多或少的耦合，导致无法顺利的进行测试，
 * Mockito库能够Mock（理解为模拟）对象，替换我们原先依赖的真实对象，
 * 这样就可以避免外部的影响，只测试本类，得到更准确的结果。
 * 普通方法
 * Created by dan.y on 2018/8/6.
 */

public class MockitoTest {

    @Test
    public void testIsNotNull(){
        Person mPerson = mock(Person.class);//<--使用mock方法

        assertNotNull(mPerson);
    }

    @Test
    public void testFunc() throws Exception {
        for (int i=1;i<=20;i++){
//            System.out.println("func("+i+")=="+func(i));
            System.out.print(func(i)+",");
        }
    }

    private long func(long p){
        if(p==1L)return 1L;
        else{
            if(p%2 == 1L){
                return func(p-1)+func(p-2);
            }else{
                return 2*func(p-1);
            }
        }
    }
}
