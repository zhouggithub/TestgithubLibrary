package com.example.zhouganglibrary.views;

import android.content.Context;
import android.util.AttributeSet;

import com.github.lzyzsd.jsbridge.BridgeWebView;


public class ScrollWebView extends  BridgeWebView{



    public ScrollInterface mScrollInterface;
    private OnScrollChangedCallback mOnScrollChangedCallback;
    public ScrollWebView(final Context context) {
        super(context);

    }
    public ScrollWebView(final Context context, final AttributeSet attrs) {
        super(context, attrs);

    }
    public ScrollWebView(final Context context, final AttributeSet attrs,
                         final int defStyle) {        super(context, attrs, defStyle);
    }
    @Override
    protected void onScrollChanged(final int l, final int t, final int oldl,
                                   final int oldt) {        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedCallback != null) {
            mOnScrollChangedCallback.onScroll(l /*- oldl*/, t/* - oldt*/);
        }

        if(mScrollInterface!=null){
            mScrollInterface.onSChanged(l, t, oldl, oldt);
        }

    }
    public OnScrollChangedCallback getOnScrollChangedCallback() {
        return mOnScrollChangedCallback;
    }
    public void setOnScrollChangedCallback(
            final OnScrollChangedCallback onScrollChangedCallback) {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }
    /**
     * Impliment in the activity/fragment/view that you want to listen to the webview
     */
    public  interface OnScrollChangedCallback {
        void onScroll(int dx, int dy);
    }
    public interface ScrollInterface {

        public void onSChanged(int l, int t, int oldl, int oldt);

    }

    public void setOnCustomScroolChangeListener(ScrollInterface scrollInterface) {

        this.mScrollInterface = scrollInterface;

    }

}