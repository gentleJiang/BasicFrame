package com.frame.baseframe.ui;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.frame.basemodule.utils.LogUtils;
import com.frame.basemodule.utils.ParamsUtils;
import com.frame.basemodule.utils.ToastUtils;

import java.util.LinkedList;

/**
 * Created by jiangjw on 2017/9/6.
 */

public class App extends Application {

    private static App instance;

    private static LinkedList<Activity> mActivityLinkedList;


    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mActivityLinkedList = new LinkedList();
        init();
    }

    private void init() {
        new LogUtils.Builder(getApplicationContext())
                .setLog2FileSwitch(true)
                .setLogSwitch(true)
                .setGlobalTag("jiangjw")
                .setBorderSwitch(false)
                .create();

        ParamsUtils.init(getApplicationContext());
        ToastUtils.init(getApplicationContext());
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                LogUtils.d("onCreate " + activity.getClass().getSimpleName());
                if (null != mActivityLinkedList && null != activity) {
                    mActivityLinkedList.addFirst(activity);
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                LogUtils.d("onDestroy " + activity.getClass().getSimpleName());
                if (null != mActivityLinkedList && null != activity) {
                    if (mActivityLinkedList.contains(activity)) {
                        mActivityLinkedList.remove(activity);
                    }
                }
            }
        });
    }

    public void exitApplication() {

        if (null != mActivityLinkedList) {
            for (Activity activity : mActivityLinkedList) {
                if (null != activity) {
                    activity.finish();
                }
            }
        }
        System.exit(0);
    }
}
