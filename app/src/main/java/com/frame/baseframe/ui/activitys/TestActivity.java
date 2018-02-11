package com.frame.baseframe.ui.activitys;

import com.frame.baseframe.R;
import com.frame.baseframe.base.BaseFragment;
import com.frame.baseframe.base.BaseFragmentActivity;
import com.frame.baseframe.ui.fragments.TestFragment;

public class TestActivity extends BaseFragmentActivity {

    @Override
    protected int getContextViewId() {
        return R.id.layout_replace;
    }

    @Override
    protected void initData() {
        BaseFragment fragment = new TestFragment();
        startFragment(fragment);
    }
}
