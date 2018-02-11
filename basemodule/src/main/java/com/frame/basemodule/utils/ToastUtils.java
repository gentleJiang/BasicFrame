package com.frame.basemodule.utils;

/**
 * Created by Jiangjw on 2017/3/4.
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.LevelListDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.frame.basemodule.R;


public class ToastUtils {

    private static Context mContext;

    private static LayoutInflater inflater;

    private static View myToastView ;

    private static TextView msgView;

    private static final int TYPE_CODE_SUCCESS = 0x01;

    private static final int TYPE_CODE_ERROR = 0x02;

    private static final int DEFAULT_TIME_DELAY = 50;

    private static Toast toast;

    private static Handler handler;

    private static LevelListDrawable levelListDrawable;



    public static void init(Context context){
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        myToastView = inflater.inflate(R.layout.toast_view, null);
        msgView = (TextView) myToastView.findViewById(R.id.tv_msg_text);
        levelListDrawable = (LevelListDrawable)msgView.getBackground();
    }


    public static void showSuccessMsg(int msgResId) {
        try {
            showSuccessMsg(mContext.getString(msgResId));
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void showErrorMsg(int msgResId) {
        try {
            showErrorMsg(mContext.getString(msgResId));
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void showSuccessMsg(String msg) {
        showMsg(TYPE_CODE_SUCCESS, msg);
    }

    public static void showErrorMsg(String msg) {
        showMsg(TYPE_CODE_ERROR, msg);
    }

    private static void showMsg(final int typeCode, final String msg) {

        if (mContext == null || msg == null) {
            return;
        }

        if (toast == null) {
            toast = new Toast(mContext);
        }

        if (handler == null) {
            handler = new Handler();
        }

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                switch (typeCode) {
                    case TYPE_CODE_SUCCESS:
                        levelListDrawable.setLevel(2);
                        break;
                    case TYPE_CODE_ERROR:
                        levelListDrawable.setLevel(8);
                        break;
                    default:
                        levelListDrawable.setLevel(2);
                        break;
                }
                msgView.setText(msg);
                toast.setView(myToastView);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
            }
        }, DEFAULT_TIME_DELAY);
    }

    private static void cancelToast() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }
}