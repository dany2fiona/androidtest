package com.dany.androidtest.mvp.ui;

import com.dany.androidtest.mvp.base.MvpView;

/**
 * Created by dan.y on 2018/8/22.
 */

public interface LoginMvpView extends MvpView{
    /**
     * 倒计时完成
     */
    void countdownComplete();

    /**
     * 倒计时中
     * @Param time剩余时间
     */
    void countdownNext(String time);

    /**
     * 登录成功
     */
    void loginSuccess();
}
