package com.dany.androidtest.net;

import android.util.Log;

import com.dany.androidtest.BuildConfig;
import com.dany.androidtest.bean.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;

/**
 * Created by dan.y on 2018/8/15.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 23)
public class ResponseTest {

    @Before
    public void setUp() throws Exception {
        ShadowLog.stream = System.out;
        initRxJava2();
    }

    private void initRxJava2(){
        RxJavaPlugins.reset();
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
        RxAndroidPlugins.reset();
        RxAndroidPlugins.setMainThreadSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @Test
    public void getUserTest() throws Exception {
        GithubService.createGithubService()
                .getUser("dany2fiona")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        assertEquals("dany2fiona",user.login);
                        assertEquals("",user.blog);
                        assertEquals(20991725,user.id);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("dan.test",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
