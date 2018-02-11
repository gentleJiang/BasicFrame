package com.frame.baseframe.ui.activitys;

import com.frame.baseframe.R;
import com.frame.baseframe.base.BaseFragmentActivity;
import com.frame.baseframe.ui.fragments.systemsetting.MainSettingFragment;

public class SettingActivity extends BaseFragmentActivity {

    @Override
    protected int getContextViewId() {
        return R.id.layout_replace;
    }

    @Override
    protected void initData() {
        MainSettingFragment fragment = new MainSettingFragment();
        startFragment(fragment);
    }
}