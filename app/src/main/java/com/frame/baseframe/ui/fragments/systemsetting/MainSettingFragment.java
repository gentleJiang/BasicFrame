package com.frame.baseframe.ui.fragments.systemsetting;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.frame.baseframe.R;
import com.frame.baseframe.base.BaseFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jiangjw on 2018/1/15.
 */

public class MainSettingFragment extends BaseFragment {

    @BindView(R.id.id_top_bar)
    QMUITopBar topBar;

    @BindView(R.id.group_list_view)
    QMUIGroupListView groupListView;


    @Override
    protected View onCreateView() {
        mFragment = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main_setting, null);
        ButterKnife.bind(this, mFragment);
        initData();
        return mFragment;
    }

    private void initData() {
        initTopBar();
        initGroupList();
    }

    private void initTopBar() {
        topBar.setTitle(R.string.common_setting);
        topBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                popBackStack();
            }
        });
    }

    private void initGroupList() {

        QMUICommonListItemView printPageCount = groupListView.createItemView("打印页数");
        printPageCount.setId(R.id.id_setting_mian_print_page_count);
        printPageCount.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        printPageCount.setDetailText("2");

        QMUICommonListItemView autoLogin = groupListView.createItemView("自动签到");
        autoLogin.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        autoLogin.getSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getActivity(), "checked = " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.id_setting_mian_print_page_count:
                        Toast.makeText(getActivity(),  " print page is Clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        QMUIGroupListView.newSection(getContext())
                .setTitle("基础设置")
                .addItemView(printPageCount, onClickListener)
                .addItemView(autoLogin, onClickListener)
                .addTo(groupListView);
    }
}
