package com.frame.baseframe.ui.activitys;

import android.app.Dialog;
import android.content.Intent;
import android.provider.Settings;
import android.view.KeyEvent;

import com.frame.baseframe.R;
import com.frame.baseframe.base.BaseActivity;
import com.frame.baseframe.commmon.TTTTTTT;
import com.frame.baseframe.ui.App;
import com.frame.basemodule.utils.LogUtils;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private Dialog backDialog;

    @BindView(R.id.id_top_bar)
    QMUITopBar qmuiTopBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initData() {
        qmuiTopBar.setTitle("主页");
        TTTTTTT t = new TTTTTTT();
        t.setQwe("111111");
        LogUtils.d(t.getQwe());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            initBackDialog();
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void initBackDialog(){

        if(backDialog == null) {
            backDialog = new QMUIDialog.MessageDialogBuilder(this)
                    .setTitle("提示")
                    .setMessage("退出应用")
                    .addAction(R.string.common_cancle, new QMUIDialogAction.ActionListener() {

                        @Override
                        public void onClick(QMUIDialog dialog, int index) {
                            dialog.dismiss();
                            startActivity(new Intent(Settings.ACTION_SETTINGS));
                        }
                    })
                    .addAction(0, R.string.common_confirm, QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {

                        @Override
                        public void onClick(QMUIDialog dialog, int index) {
                            dialog.dismiss();
                            App.getInstance().exitApplication();
                        }
                    })
                    .create();
        }
        backDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(backDialog != null){
            backDialog.dismiss();
            backDialog = null;
        }
    }
}
