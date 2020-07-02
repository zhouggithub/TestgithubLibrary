package com.example.zhouganglibrary.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by zhou_gang on 2019/1/28.
 */

public class ObservableScrollView extends HorizontalScrollView {
    private OnScrollChangedListener onScrollChangedListener;

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnScrollListener(OnScrollChangedListener onScrollChangedListener) {
        this.onScrollChangedListener = onScrollChangedListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        super.onScrollChanged(x, y, oldX, oldY);
        if (onScrollChangedListener != null) {
            onScrollChangedListener.onScrollChanged(x, y, oldX, oldY);
        }
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(int x, int y, int oldX, int oldY);
    }
}
