package com.example.zhouganglibrary.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;


public class VideoScrollView extends ScrollView {


    private OnScrollListener listener;
    private boolean mIntercept=false;

    public void setOnScrollListener(OnScrollListener listener) {
        this.listener = listener;
    }

    public VideoScrollView(Context context) {
        super(context);
    }

    public VideoScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface OnScrollListener {
        void onScroll(int scrollY, int oldY);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (listener != null) {
            listener.onScroll(t,oldt);
        }
    }

    public void setIntercept(boolean intercept) {
        mIntercept=intercept;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(mIntercept);
        return super.onInterceptTouchEvent(ev);
    }

}