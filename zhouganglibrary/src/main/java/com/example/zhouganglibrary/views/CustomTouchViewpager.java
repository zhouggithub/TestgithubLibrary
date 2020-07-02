package com.example.zhouganglibrary.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

/**
 * Created by zhou_gang on 2018/12/25.
 */

public class CustomTouchViewpager extends ViewPager {
    private boolean interceptTouch=true;

    public CustomTouchViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // interceptTouch是自定义属性控制是否拦截事件
        if (interceptTouch){
            ViewParent parent =this;
            // 循环查找ViewPager, 请求ViewPager不拦截触摸事件
            while(!((parent = parent.getParent()) instanceof ViewPager)){
                // nop
            }
            parent.requestDisallowInterceptTouchEvent(true);
        }

        return super.dispatchTouchEvent(ev);
    }
}
