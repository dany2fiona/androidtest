package com.dany.androidtest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        //断言失败则在控制台输出异常，测试报错结束..
        assertEquals(4, 2 + 2);
        assertNotEquals(4,2+3);
        assertArrayEquals(new int[]{1,2},new int[]{1,2});
        assertNotEquals(new int[]{1,2},new int[]{2,3});
        assertNull(null);
        assertNotNull(new Object());
        assertTrue(true);
        assertFalse(false);
        Object obj = new Object();
        assertSame(obj,obj);
        assertNotSame(new Object(),new Object());
//        assertThat();
    }
}