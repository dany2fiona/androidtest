package com.dany.androidtest.mvp.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dany.androidtest.R;
import com.dany.androidtest.mvp.base.BaseMVPActivity;

/**
 * Created by dan.y on 2018/8/22.
 */

public class LoginMVPActivity extends BaseMVPActivity<LoginMvpView,LoginPresenter> implements
      LoginMvpView, View.OnClickListener{
    private TextView mTvSendIdentify;
    private EditText mEtMobile;
    private EditText mEtIdentify;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEtMobile = (EditText) this.findViewById(R.id.et_mobile);
        mEtIdentify = (EditText) this.findViewById(R.id.et_identify);
        mTvSendIdentify = (TextView) this.findViewById(R.id.tv_send_identify);

        this.findViewById(R.id.tv_login).setOnClickListener(this);
        mTvSendIdentify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_send_identify:
                mTvSendIdentify.setEnabled(false);
                mPresenter.getIdentify();
                break;
            case R.id.tv_login:
                mPresenter.login(mEtMobile.getText().toString().trim(),
                        mEtIdentify.getText().toString().trim());
                break;
            default:
                break;
        }
    }

    @Override
    public void countdownComplete() {
        mTvSendIdentify.setText(R.string.login_send_identify);
        mTvSendIdentify.setEnabled(true);
    }

    @Override
    public void countdownNext(String time) {
        mTvSendIdentify.setText(TextUtils.concat(time, "秒后重试"));
    }

    @Override
    public void loginSuccess() {
        showToast("登录成功");
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }
}
