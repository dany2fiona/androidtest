package com.dany.androidtest.mvp.base;

import android.content.Context;

/**
 * Created by dan.y on 2018/8/22.
 */

public interface MvpView {

    Context getContext();

    void showProgress();

    void closeProgress();

    void showToast(String string);
}
