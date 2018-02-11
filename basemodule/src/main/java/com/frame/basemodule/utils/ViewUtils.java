package com.frame.basemodule.utils;


import android.animation.ObjectAnimator;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

/**
 * View帮助类
 * @author CB
 * @time 2015-5-7 下午9:45:15
 */
public class ViewUtils {
	
	/**
	 * 抖动动画效果
	 * @param view 指定view实现左右抖动
	 */
	public static void shakeAnimatie(View view) {

		ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 0, 30, 0, -30, 0).setDuration(350);
		animator.setInterpolator(new DecelerateInterpolator());
		animator.start();
	}
	
	/**
	 * 当传入内容为空时隐藏该TextView
	 * @param content
	 * @param textView
	 */
	public static void setGoneTextView(String content, TextView textView) {
		if (content == null) {
			textView.setVisibility(View.GONE);
		} else {
			textView.setText(content);
		}
	}

    private static long lastClickTime;
    /**
     * 是否为快速点击
     * @return
     */
    public static boolean isFastClick() {      
        return isFastClick(500);     
    } 
    /**
     * 是否为快速点击
     * @return
     */
    public static boolean isFastClick(int millisecond) {  
    	//系统开机时间
        long time = SystemClock.elapsedRealtime();     
        if ( time - lastClickTime < millisecond) {     
            return true;     
        }     
        lastClickTime = time;     
        return false;     
    } 
    
    /**
     * 设置获取焦点
     * @param view
     */
    public static void setFocus(View view) {
		view.setFocusable(true);
		view.setFocusableInTouchMode(true);
		view.requestFocus();
	}
}
