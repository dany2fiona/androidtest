package com.dany.androidtest.powermock;

import com.dany.androidtest.bean.Banana;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;

/**
 * Created by dan.y on 2018/8/9.
 */

public class PowerMockitoNewClassTest {

    @Rule
    public PowerMockRule powerMockRule = new PowerMockRule();

    @Test
    @PrepareForTest({Banana.class})
    public void testNewClass() throws Exception {
        Banana mBanana = PowerMockito.mock(Banana.class);
        PowerMockito.when(mBanana.getBananaInfo()).thenReturn("大香蕉");
        //如果new新对象，则返回这个上面设置的这个对象
        PowerMockito.whenNew(Banana.class).withNoArguments().thenReturn(mBanana);
        //new新的对象
        Banana newBanana = new Banana();
        Assert.assertEquals("大香蕉",newBanana.getBananaInfo());
    }
}
