package com.dany.androidtest.junit;

import com.dany.androidtest.utils.DateUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by dan.y on 2018/8/6.
 */
@RunWith(Parameterized.class)
public class DateFormatTest {
    private String time;
    public DateFormatTest(String time){
        this.time = time;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers(){
        return Arrays.asList(new String[]{
                "2017-10-15",
                "2017-10-15 16:00:02",
                "2017年10月15日 16时00分02秒"
        });
    }

    @Test(expected = ParseException.class)//测试是否抛出异常，没有抛出异常则失败
    public void dateToStampTest1() throws Exception{
        DateUtil.dateToStamp(time);
    }

}
