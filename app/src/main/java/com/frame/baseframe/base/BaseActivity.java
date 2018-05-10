package com.frame.baseframe.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.ButterKnife;

/**
 * Created by jiangjw on 2017/9/6.
 */

abstract public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(getLayoutId(),null);
        setContentView(view);
        ButterKnife.bind(this);
//        QMUIStatusBarHelper.translucent(this);
        initData();
    }

    protected abstract int getLayoutId();

    protected void initData(){}
}
