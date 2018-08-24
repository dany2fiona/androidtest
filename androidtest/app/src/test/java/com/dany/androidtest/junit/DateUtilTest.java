package com.dany.androidtest.junit;

import com.dany.androidtest.utils.DateUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import static org.junit.Assert.*;

/**
 * Created by dan.y on 2018/8/6.
 */

public class DateUtilTest {

    private String time = "2017-10-15 16:00:02";
    private long timeStamp = 1508054402000L;
    private Date mDate;

    @Before
    public void setSup() throws Exception{
        System.out.println("测试开始！");
        mDate = new Date();
        mDate.setTime(timeStamp);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试结束！");
    }

    @Test
    public void stampToDateTest() throws Exception {
        Assert.assertNotEquals ("预期时间", DateUtil.stampToDate(timeStamp));
    }

    @Test
    public void dateToStampTest() throws Exception {
        assertNotEquals(4,DateUtil.dateToStamp(time));
    }

    @Test(expected = ParseException.class)
    public void dateToStampTest1() throws Exception {
        DateUtil.dateToStamp("2017-10-15");
    }
}
