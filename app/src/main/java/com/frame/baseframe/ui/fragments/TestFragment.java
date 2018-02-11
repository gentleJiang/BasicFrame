package com.frame.baseframe.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.frame.baseframe.R;
import com.frame.baseframe.base.BaseFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout;
import com.zhuang.zbannerlibrary.PageTransformer.AccordionTransformer;
import com.zhuang.zbannerlibrary.PageTransformer.AccordionTransformer1;
import com.zhuang.zbannerlibrary.PageTransformer.DepthPageTransformer;
import com.zhuang.zbannerlibrary.PageTransformer.DrawerTransformer;
import com.zhuang.zbannerlibrary.PageTransformer.Flip3DTransformer;
import com.zhuang.zbannerlibrary.PageTransformer.FlipHorizontalTransformer;
import com.zhuang.zbannerlibrary.PageTransformer.RotateDownTransformer;
import com.zhuang.zbannerlibrary.ZBanner;
import com.zhuang.zbannerlibrary.ZBannerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TestFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar qmuiTopBar;

    @BindView(R.id.pull_to_refresh)
    QMUIPullRefreshLayout qmuiPullRefreshLayout;

    @BindView(R.id.listview)
    ListView listView;

    @BindView(R.id.banner)
    ZBanner banner;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_test, null);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {

        qmuiTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popBackStack();
            }
        });

        qmuiTopBar.setTitle("测试fragment");
        qmuiTopBar.addRightImageButton(R.mipmap.icon_topbar_overflow, R.id.id_topbar_right_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showBottomSheet();
                    }
                });

        final List<String> data = new ArrayList<>(Arrays.asList("112", "112", "111", "44455"));
        final ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);

        qmuiPullRefreshLayout.setOnPullListener(new QMUIPullRefreshLayout.OnPullListener() {
            @Override
            public void onMoveTarget(int offset) {

            }

            @Override
            public void onMoveRefreshView(int offset) {

            }

            @Override
            public void onRefresh() {
                data.add("eeeee");
                adapter.notifyDataSetChanged();
                qmuiPullRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        qmuiPullRefreshLayout.finishRefresh();
                    }
                }, 2000);
            }
        });

        banner.setAdapter(new MyAdapte(getFragmentManager()));
        banner.star(1000, 2000);
        banner.setPageTransformer(new Flip3DTransformer());
    }

    private void showBottomSheet() {

        new QMUIBottomSheet.BottomListSheetBuilder(getActivity())
                .addItem("AccordionTransformer")
                .addItem("AccordionTransformer1")
                .addItem("DepthPageTransformer")
                .addItem("DrawerTransformer")
                .addItem("Flip3DTransformer")
                .addItem("FlipHorizontalTransformer")
                .addItem("RotateDownTransformer")
                .addItem("StackTransformer")
                .addItem("ZoomOutTransformer")
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        switch (position){
                            case 0:
                                banner.setPageTransformer(new AccordionTransformer());
                                break;

                            case 1:
                                banner.setPageTransformer(new AccordionTransformer1());
                                break;

                            case 2:
                                banner.setPageTransformer(new DepthPageTransformer());
                                break;

                            case 3:
                                banner.setPageTransformer(new DrawerTransformer());
                                break;

                            case 4:
                                banner.setPageTransformer(new Flip3DTransformer());
                                break;

                            case 5:
                                banner.setPageTransformer(new FlipHorizontalTransformer());
                                break;

                            case 6:
                                banner.setPageTransformer(new RotateDownTransformer());
                        }
                    }
                })
                .build()
                .show();
    }

    public static class BannerFragment extends Fragment {

        private int position;
        public static BannerFragment newInstance(int position) {
            BannerFragment fragment = new BannerFragment();
            fragment.position = position;
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            TextView textView = new TextView(getContext());
            textView.setText("position " + position);
            textView.setTextColor(Color.BLACK);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(30);
            textView.setBackgroundColor(Color.BLUE);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return textView;
        }
    }

    class MyAdapte extends ZBannerAdapter {

        public MyAdapte(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new BannerFragment().newInstance(position);
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
