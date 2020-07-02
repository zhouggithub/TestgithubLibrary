package com.example.zhouganglibrary.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by rui on 2018/12/29.
 */

public class ServiceItemLayout extends LinearLayout {

        public  ServiceItemLayout  (Context context, AttributeSet attrs,
                           int defStyle) {
            super(context, attrs, defStyle);
        }

        public ServiceItemLayout (Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public ServiceItemLayout (Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            setMeasuredDimension(getDefaultSize(0, widthMeasureSpec),
                    getDefaultSize(0, heightMeasureSpec));

            int childWidthSize = getMeasuredWidth();
            // 高度和宽度一样
            heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(
                    childWidthSize, MeasureSpec.EXACTLY);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }


