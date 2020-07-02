package com.example.zhouganglibrary.views;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.util.DensityUtil;

import cn.hktoutiao.toutiao.R;


/**
 * Created by admin on 2017/7/31.
 * 标题栏工具类，封装公共的标题栏
 */
public class TitleBuilder {
    public ImageView getCollect() {
        return collect;
    }

    public void setCollect(ImageView collect) {
        this.collect = collect;
    }

    /**
     * title栏根布局
     */
    private View titleView;
    private TextView left_textview;
    private TextView title_right_textview_with_bg;
    private ImageView left_imageview;
    private TextView middle_textview;
    private TextView right_textview, tv_Back;
    private ImageView right_imageview;
    private ImageView left_imageview_error, font, collect, share,iv_close;
    private Activity context;
    private View line, viewGradient;
    private View ll_search, ll_line;

    /**
     * 第一种  初始化方式
     * 这里是直接引用进文件的初始化方式
     *
     * @param context
     */
    public TitleBuilder(Activity context) {
        this.context = context;
        titleView = context.findViewById(R.id.title_bar);

        left_textview = (TextView) titleView.findViewById(R.id.title_left_textview);
        left_imageview = (ImageView) titleView.findViewById(R.id.title_left_imageview);
        tv_Back = (TextView) titleView.findViewById(R.id.tv_back);

        middle_textview = (TextView) titleView.findViewById(R.id.title_middle_textview);

        right_textview = (TextView) titleView.findViewById(R.id.title_right_textview);
        right_imageview = (ImageView) titleView.findViewById(R.id.title_right_imageview);

        left_imageview_error = (ImageView) titleView.findViewById(R.id.title_left_imageview1);

        title_right_textview_with_bg = (TextView) titleView.findViewById(R.id.title_right_textview_with_bg);
        ll_search = titleView.findViewById(R.id.ll_search);
        ll_line = titleView.findViewById(R.id.ll_line);
        font = titleView.findViewById(R.id.font);
        collect = titleView.findViewById(R.id.collect);
        share = titleView.findViewById(R.id.share);
        iv_close = titleView.findViewById(R.id.iv_close);
    }

    /**
     * 第二种初始化方式
     * 这里是比如你用代码创建布局的时候使用
     *
     * @param context
     */
    public TitleBuilder(View context) {

        titleView = context.findViewById(R.id.title_bar);

        left_textview = (TextView) context.findViewById(R.id.title_left_textview);
        left_imageview = (ImageView) context.findViewById(R.id.title_left_imageview);
        tv_Back = (TextView) context.findViewById(R.id.tv_back);
        middle_textview = (TextView) context.findViewById(R.id.title_middle_textview);

        right_textview = (TextView) context.findViewById(R.id.title_right_textview);
        right_imageview = (ImageView) context.findViewById(R.id.title_right_imageview);

        left_imageview_error = (ImageView) context.findViewById(R.id.title_left_imageview1);
        title_right_textview_with_bg = (TextView) context.findViewById(R.id.title_right_textview_with_bg);
        ll_search = titleView.findViewById(R.id.ll_search);
        ll_line = titleView.findViewById(R.id.ll_line);
        font = titleView.findViewById(R.id.font);
        collect = titleView.findViewById(R.id.collect);
        share = titleView.findViewById(R.id.share);
        iv_close = titleView.findViewById(R.id.iv_close);

    }

    //字体显示
    public TitleBuilder setFontShow(View.OnClickListener listener) {
        if (listener != null) {
            font.setVisibility(View.VISIBLE);
            font.setOnClickListener(listener);
        } else {
            font.setVisibility(View.GONE);
        }

        return this;
    }
    //字体显示
    public TitleBuilder setColllectShow(View.OnClickListener listener,boolean isCollect) {
        if (listener != null) {
            collect.setOnClickListener(listener);
            collect.setVisibility(View.VISIBLE);
        } else {
            collect.setVisibility(View.GONE);
        }
        if (isCollect) {
            collect.setImageResource(R.mipmap.collect_pre);
        } else {
            collect.setImageResource(R.mipmap.collect);
        }

        return this;
    }
    //字体显示
    public TitleBuilder setShareShow(View.OnClickListener listener) {
        if (listener != null) {
            share.setOnClickListener(listener);
            share.setVisibility(View.VISIBLE);
        } else {
            share.setVisibility(View.GONE);
        }

        return this;
    }

    //搜索显示
    public TitleBuilder setViewGradint(boolean isShow) {
        if (isShow) {
            viewGradient.setVisibility(View.VISIBLE);
        } else {
            viewGradient.setVisibility(View.GONE);
        }
        return this;
    }

    //搜索显示
    public TitleBuilder setSearchBar(boolean isShow) {
        if (isShow) {
            ll_search.setVisibility(View.VISIBLE);
        } else {
            ll_search.setVisibility(View.GONE);
        }
        return this;
    }

    public TitleBuilder setSearchBar() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ll_search.getLayoutParams();
        layoutParams.leftMargin = DensityUtil.dp2px(15);
        ll_search.setLayoutParams(layoutParams);
        return this;
    }

    /**
     * 标题栏容器的背景色
     */
    public TitleBuilder setTitleBarBgRes(int resid) {

        titleView.setBackgroundResource(resid);
        return this;
    }

    /**
     * title 的背景色
     */

    public TitleBuilder setMiddleTitleBgRes(int resid) {

        middle_textview.setBackgroundResource(resid);

        return this;
    }

    /**
     * title文字的颜色
     */
    public TitleBuilder setMiddleTitleTextColor(int resid) {
        middle_textview.setTextColor(resid);

        return this;
    }

    /**
     * title的文本
     *
     * @param text
     * @return
     */
    public TitleBuilder setMiddleTitleText(String text) {


        middle_textview.setVisibility(TextUtils.isEmpty(text) ? View.GONE
                : View.VISIBLE);
        middle_textview.setText(text);

        return this;
    }


    public TitleBuilder setLineGone() {
        if (ll_line.getVisibility() == View.VISIBLE)
            ll_line.setVisibility(View.GONE);
        return this;
    }
    /**
     * left
     */
    /**
     * 图片按钮
     *
     * @param resId
     * @return
     */
    public TitleBuilder setLeftImageRes(int resId) {

        left_imageview.setVisibility(resId > 0 ? View.VISIBLE : View.INVISIBLE);
        left_imageview.setImageResource(resId);

        return this;
    }
    /**
     * left
     */
    /**
     * 图片按钮
     * 显示后退按钮，点击即关闭当前页面
     *
     * @param
     * @return
     */
    public TitleBuilder setLeftImageBack() {

        left_imageview.setVisibility(View.VISIBLE);
        left_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });

        return this;
    }

    public ImageView getIv_close() {
        return iv_close;
    }

    public void setIv_close(ImageView iv_close) {
        this.iv_close = iv_close;
    }

    public TitleBuilder setLeftImageBack(final CloseCallback closeCallback) {

        left_imageview.setVisibility(View.VISIBLE);
        left_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeCallback.onClick();
                iv_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.finish();
                    }
                });
            }
        });

        return this;
    }
    private CloseCallback mCloseCallback;

    public interface CloseCallback {
        void  onClick();
    }
    /**
     * left
     */
    /**
     * 文字返回
     * 显示后退按钮，点击即关闭当前页面
     *
     * @param
     * @return
     */
    public TitleBuilder setLeftBackText() {

        tv_Back.setVisibility(View.VISIBLE);
        tv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });

        return this;
    }
    public TitleBuilder setLeftBackText(final CloseCallback closeCallback) {

        tv_Back.setVisibility(View.VISIBLE);
        tv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeCallback.onClick();
                iv_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.finish();
                    }
                });
            }
        });

        return this;
    }

    /**
     * 图片按钮
     * 显示右侧带背景文字按钮，比如意见反馈的提交
     *
     * @param
     * @return
     */
    public TitleBuilder setRightTextWithBg(String text) {

        title_right_textview_with_bg.setVisibility(View.VISIBLE);
        title_right_textview_with_bg.setText(text);

        return this;
    }

    /**
     * 左边文字按钮
     *
     * @param text
     * @return
     */
    public TitleBuilder setLeftText(String text) {

        left_textview.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        left_textview.setText(text);

        return this;
    }


    /**
     * 左边的叉叉图片
     *
     * @param resId
     * @return
     */
    public TitleBuilder setLeftErrorImage(int resId) {

        left_imageview_error.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        left_imageview_error.setImageResource(resId);

        return this;
    }

    /**
     * 设置左边的事件
     */
    public TitleBuilder setLeftImageListener(View.OnClickListener listener) {

        if (left_imageview.getVisibility() == View.VISIBLE) {

            left_imageview.setOnClickListener(listener);

        }

        return this;
    }


    /**
     * 设置左边文本的事件
     */
    public TitleBuilder setLeftTextListener(View.OnClickListener listener) {

        if (left_textview.getVisibility() == View.VISIBLE) {

            left_textview.setOnClickListener(listener);

        }

        return this;
    }

    /**
     * right
     */
    /**
     * 图片按钮
     *
     * @param resId
     * @return
     */
    public TitleBuilder setRightImageRes(int resId) {

        right_imageview.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        right_imageview.setImageResource(resId);

        return this;
    }

    /**
     * 右边文字按钮
     *
     * @param text
     * @return
     */
    public TitleBuilder setRightText(String text) {

        right_textview.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        right_textview.setText(text);
        if (!TextUtils.isEmpty(text)) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ll_search.getLayoutParams();
            layoutParams.rightMargin = DensityUtil.dp2px(60);
            ll_search.setLayoutParams(layoutParams);
        }
        return this;
    }

    public TextView getRightTextView() {
        return right_textview;
    }

    /**
     * 设置右边的事件
     */
    public TitleBuilder setRightTextOrImageListener(View.OnClickListener listener) {

        if (right_imageview.getVisibility() == View.VISIBLE) {

            right_imageview.setOnClickListener(listener);

        } else if (right_textview.getVisibility() == View.VISIBLE) {

            right_textview.setOnClickListener(listener);

        }

        return this;
    }

    public View build() {

        return titleView;
    }

    /**
     * 隐藏标题栏
     */
    public void hideTitle() {
        titleView.setVisibility(View.GONE);
    }

    /**
     * 显示标题栏
     */
    public void displayTitle() {
        titleView.setVisibility(View.VISIBLE);
    }


    /**
     * 隐藏又边文字
     */
    public void hideRightTex() {
        right_textview.setVisibility(View.GONE);
    }


    public void hideError() {
        font.setVisibility(View.GONE);
        collect.setVisibility(View.GONE);
        share.setVisibility(View.GONE);
    }

}

