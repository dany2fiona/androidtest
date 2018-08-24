package com.dany.androidtest.rxjava;

import android.app.Application;
import android.widget.TextView;

import com.dany.androidtest.BuildConfig;
import com.dany.androidtest.R;
import com.dany.androidtest.ui.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by dan.y on 2018/8/21.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 23)
public class LoginActivityTest {
    private LoginActivity loginActivity;
    private TextView mTvSendIdentify;

    @Rule
    public RxJavaTestSchedulerRule rule = new RxJavaTestSchedulerRule();

    @Before
    public void setUp() throws Exception {
        loginActivity = Robolectric.setupActivity(LoginActivity.class);
        mTvSendIdentify = loginActivity.findViewById(R.id.tv_send_identify);
    }

    @Test
    public void testGetIdentify() throws Exception {
        Application application = RuntimeEnvironment.application;
        assertEquals(mTvSendIdentify.getText().toString(),
                application.getString(R.string.login_send_identify));

        //触发按钮点击
        mTvSendIdentify.performClick();
        //时间到10秒
        rule.getTestScheduler().advanceTimeTo(10, TimeUnit.SECONDS);
        assertFalse(mTvSendIdentify.isEnabled());
        assertEquals(mTvSendIdentify.getText().toString(),"111秒后重试");

        //时间到120秒
        rule.getTestScheduler().advanceTimeTo(120,TimeUnit.SECONDS);
        assertEquals(mTvSendIdentify.getText().toString(),
                application.getString(R.string.login_send_identify));
        assertTrue(mTvSendIdentify.isEnabled());
    }
}
