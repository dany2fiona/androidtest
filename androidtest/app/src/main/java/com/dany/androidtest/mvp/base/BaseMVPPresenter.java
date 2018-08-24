package com.dany.androidtest.mvp.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by dan.y on 2018/8/22.
 */

public abstract class BaseMVPPresenter<T extends MvpView> {

    /**
     * View接口的弱引用
     */
    private Reference<T> mViewRef;
    protected T mMvpView;

    /**
     * 建立关联
     */
    public void attachView(T view){
        mViewRef = new WeakReference<T>(view);
        if(isViewAttached()){
            mMvpView = getView();
        }
    }

    /**
     * 获取View
     * @return
     */
    public T getView() {
        return mViewRef.get();
    }

    /**
     * UI展示相关的操作需要判断一下 Activity 是否已经 finish.
     * @return
     */
    public boolean isViewAttached(){
        return mViewRef!=null && mViewRef.get()!=null;
    }

    /**
     * 解除关联
     */
    public void detachView(){
        if(mViewRef !=null){
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
