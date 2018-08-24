package com.dany.androidtest.powermock;

import com.dany.androidtest.bean.Banana;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

/**
 * Created by dan.y on 2018/8/9.
 */

@RunWith(PowerMockRunner.class)
public class PowerMockitoStaticMethodTest {

    @Test
    @PrepareForTest({Banana.class})
    public void testStaticMethod() throws Exception {
        PowerMockito.mockStatic(Banana.class);//<-- mock静态类
        Mockito.when(Banana.getColor()).thenReturn("绿色");
        Assert.assertEquals("绿色",Banana.getColor());
    }

    @Test
    @PrepareForTest({Banana.class})
    public void testChangeColor() throws Exception {
        Whitebox.setInternalState(Banana.class,"COLOR","红色的");
        Assert.assertEquals("红色的",Banana.getColor());
    }
}
