package com.dany.androidtest.robolectric.ui;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.dany.androidtest.BuildConfig;
import com.dany.androidtest.R;
import com.dany.androidtest.ui.LoginActivity;
import com.dany.androidtest.ui.MainActivity;
import com.dany.androidtest.ui.fragment.SampleFragment;

import org.apache.tools.ant.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by dan.y on 2018/8/10.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 23)
public class MainActivityTest {
    private final String TAG = "dan.test";
    private MainActivity mainActivity;
    private Button mJumpBtn;
    private Button mToastBtn;
    private Button mDialogBtn;
    private Button mInverseBtn;
    private CheckBox checkBox;

    @Before
    public void setUp() throws Exception {
        //输出日志
        ShadowLog.stream = System.out;
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        mJumpBtn = mainActivity.findViewById(R.id.button1);
        mToastBtn = mainActivity.findViewById(R.id.button2);
        mDialogBtn = mainActivity.findViewById(R.id.button3);
        mInverseBtn = mainActivity.findViewById(R.id.button4);
        checkBox = mainActivity.findViewById(R.id.checkbox);
    }

    /**
     * 创建Activity测试
     */
    @Test
    public void testMainActivity() throws Exception {
        assertNotNull(mainActivity);
        Log.d(TAG, "测试Log输出");
    }

    /**
     * 验证点击事件是否触发了页面跳转，验证目标页面是否预期页面
     *
     * @throws Exception
     */
    @Test
    public void testJump() throws Exception {
        assertEquals(mJumpBtn.getText().toString(),"Activity跳转");
        //触发按钮点击
        mJumpBtn.performClick();

        //获取对应的Shadow类
        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        //借助Shadow类获取启动下一Activity的Intent
        Intent nextIntent = shadowActivity.getNextStartedActivity();
        //校验Intent的正确性
        assertEquals(nextIntent.getComponent().getClassName(), LoginActivity.class.getName());
    }

    /**
     * 验证Toast是否正确弹出
     *
     * @throws Exception
     */
    @Test
    public void testToast() throws Exception {
        Toast toast = ShadowToast.getLatestToast();
        //判断Toast尚未弹出
        assertNull(toast);

        mToastBtn.performClick();
        toast = ShadowToast.getLatestToast();
        //判断Toast已经弹出
        assertNotNull(toast);
        //获取Shadow类进行验证
        ShadowToast shadowToast = Shadows.shadowOf(toast);
        assertEquals(Toast.LENGTH_LONG,shadowToast.getDuration());
        assertEquals("hello UT!",ShadowToast.getTextOfLatestToast());
    }

    /**
     * 验证Dialog是否正确弹出
     *
     * @throws Exception
     */
    @Test
    public void testDialog() throws Exception {
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        //判断Dialog尚未弹出
        assertNull(dialog);

        mDialogBtn.performClick();
        dialog = ShadowAlertDialog.getLatestAlertDialog();
        //判断Dialog已经弹出
        assertNotNull(dialog);
        //获取Shadow类进行验证
        ShadowAlertDialog shadowDialog = Shadows.shadowOf(dialog);
        assertEquals("Hello UT!",shadowDialog.getMessage());
    }

    /**
     * 验证UI组件状态
     *
     * @throws Exception
     */
    @Test
    public void testCheckBoxState() throws Exception {
        //验证CheckBox初始状态
        assertFalse(checkBox.isChecked());

        //点击按钮反转CheckBox状态
        mInverseBtn.performClick();
        //验证状态是否正确
        assertTrue(checkBox.isChecked());

        //点击按钮反转CheckBox状态
        mInverseBtn.performClick();
        //验证状态是否正确
        assertFalse(checkBox.isChecked());
    }

    /**
     * 验证Fragment
     * @throws Exception
     */
    @Test
    public void testFragment() throws Exception {
        SampleFragment sampleFragment = new SampleFragment();
        //添加Fragment到Activity中，会触发Fragment的onCreateView()
        SupportFragmentTestUtil.startFragment(sampleFragment);
        assertNotNull(sampleFragment.getView());
    }

    /**
     * 测试访问资源文件
     * @throws Exception
     */
    @Test
    public void testResources() throws Exception {
        Application application = RuntimeEnvironment.application;
        String appName = application.getString(R.string.app_name);
        assertEquals("AndroidUT",appName);
    }

    /**
     *Activity生命周期测试
     * @throws Exception
     */
    @Test
    public void testLifeCycle() throws Exception {
        //创建Activity控制器
        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        MainActivity activity = controller.get();
        assertNull(activity.getLifecycleState());

        //调用Actvity的performCreate方法
        controller.create();
        assertEquals("onCreate",activity.getLifecycleState());

        //调用Activity的performStart方法
        controller.start();
        assertEquals("onStart",activity.getLifecycleState());

        //调用Activity的performResume方法
        controller.resume();
        assertEquals("onResume",activity.getLifecycleState());

        //调用Activity的performPause方法
        controller.pause();
        assertEquals("onPause",activity.getLifecycleState());

        //调用Activity的performStop方法
        controller.stop();
        assertEquals("onStop",activity.getLifecycleState());

        //调用Activity的performRestart方法
        controller.restart();
        // 注意此处应该是onStart，因为performRestart不仅会调用restart，还会调用onStart
        assertEquals("onStart",activity.getLifecycleState());

        //调用Activity的performDestroy方法
        controller.destroy();
        assertEquals("onDestroy",activity.getLifecycleState());
    }
}
