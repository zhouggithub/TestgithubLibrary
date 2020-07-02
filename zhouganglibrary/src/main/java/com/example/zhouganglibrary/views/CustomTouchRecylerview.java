package com.example.zhouganglibrary.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

/**
 * Created by zhou_gang on 2018/12/25.
 */

public class CustomTouchRecylerview extends RecyclerView {
    private int mLastX;
    private int mLastY;
    private boolean interceptTouch =true;

    public CustomTouchRecylerview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//
//        int x = (int)ev.getX();
//        int y = (int)ev.getY();
//
//        switch (ev.getAction()){
//
//            case MotionEvent.ACTION_DOWN:
//                mLastX = x;
//                mLastY = y;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int dx = Math.abs(x - mLastX);
//                int dy = Math.abs(y - mLastY);
//
//                if(dx * 4 > dy){
//                    // 上一页
//                    if(x >= mLastX){
//                        return ViewCompat.canScrollHorizontally(this,-1);
//                    }else {
//                        // 下一页
//                        return ViewCompat.canScrollHorizontally(this,1);
//                    }
//                }
//                break;
//        }
//
//        return super.onInterceptTouchEvent(ev);
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        int x = (int)ev.getX();
//        int y = (int)ev.getY();
//
//        switch (ev.getAction()){
//
//            case MotionEvent.ACTION_DOWN:
//                mLastX = x;
//                mLastY = y;
//                getParent().requestDisallowInterceptTouchEvent(true);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int dx = Math.abs(x - mLastX);
//                int dy = Math.abs(y - mLastY);
//
//                if(dx * 4 > dy){
//                    if(x >= mLastX){
//                        // 上一页
//                        if(ViewCompat.canScrollHorizontally(this,-1)){
//                            getParent().requestDisallowInterceptTouchEvent(true);
//                        }else {
//                            getParent().requestDisallowInterceptTouchEvent(false);
//                        }
//                    }else {
//                        // 下一页
//                        if(ViewCompat.canScrollHorizontally(this,1)){
//                            getParent().requestDisallowInterceptTouchEvent(true);
//                        }else {
//                            getParent().requestDisallowInterceptTouchEvent(false);
//                        }
//                    }
//                }else {
//                    getParent().requestDisallowInterceptTouchEvent(false);
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                getParent().requestDisallowInterceptTouchEvent(false);
//                break;
//        }
//
//        return super.dispatchTouchEvent(ev);
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
}}
