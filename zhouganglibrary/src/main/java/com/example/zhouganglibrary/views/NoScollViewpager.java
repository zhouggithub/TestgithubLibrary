package com.example.zhouganglibrary.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by zhougang on 2018/6/29.
 * description:
 */

public class NoScollViewpager extends ViewPager {
        //是否可以进行滑动
        private boolean isCanScroll = true;

    public NoScollViewpager(Context context) {
        super(context);
    }

    public NoScollViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScanScroll(boolean isCanScroll){
        this.isCanScroll = isCanScroll;
    }


    @Override
    public void scrollTo(int x, int y){
        if (isCanScroll){
            super.scrollTo(x, y);
        }
    }}
