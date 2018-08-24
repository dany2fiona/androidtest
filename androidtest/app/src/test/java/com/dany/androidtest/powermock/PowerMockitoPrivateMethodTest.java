package com.dany.androidtest.powermock;

import com.dany.androidtest.bean.Banana;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by dan.y on 2018/8/9.
 */

@RunWith(PowerMockRunner.class)
public class PowerMockitoPrivateMethodTest {

    @Test
    @PrepareForTest({Banana.class})
    public void testPrivateMethod() throws Exception {
        Banana mBanana = PowerMockito.mock(Banana.class);
        PowerMockito.when(mBanana.getBananaInfo()).thenCallRealMethod();
        PowerMockito.when(mBanana,"flavor").thenReturn("苦苦的");
        Assert.assertEquals("苦苦的黄色的",mBanana.getBananaInfo());
        //验证flavor是否调用了一次
        PowerMockito.verifyPrivate(mBanana).invoke("flavor");
    }

    @Test
    @PrepareForTest({Banana.class})
    public void skipPrivateMethod() throws Exception {
        Banana mBanana = new Banana();
        //跳过flavor方法
        PowerMockito.suppress(PowerMockito.method(Banana.class,"flavor"));
        Assert.assertEquals("null黄色的",mBanana.getBananaInfo());
    }

    /**
     * 更改父类私有变量
     */
    @Test
    @PrepareForTest({Banana.class})
    public void testChangeParentPrivate() throws Exception {
        Banana mBanana = new Banana();
        MemberModifier.field(Banana.class,"fruit").set(mBanana,"蔬菜");
        Assert.assertEquals("蔬菜",mBanana.getFruit());
    }
}
