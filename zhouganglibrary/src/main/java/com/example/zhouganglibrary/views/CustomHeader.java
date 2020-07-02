package com.example.zhouganglibrary.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.api.RefreshHeader;

import cn.hktoutiao.toutiao.R;

/**
 * Created by zhou_gang on 2018/12/26.
 */

public class CustomHeader implements  xiao.free.horizontalrefreshlayout.RefreshHeader {
    private final Context mContext;

    public CustomHeader(Context context) {
        mContext = context;
    }



    @Override
    public void onStart(int dragPosition, View refreshHead) {

    }

    @Override
    public void onDragging(float distance, float percent, View refreshHead) {

    }

    @Override
    public void onReadyToRelease(View refreshHead) {

    }

    @NonNull
    @Override
    public View getView(ViewGroup container) {
        return LayoutInflater.from(mContext).inflate(R.layout.custom_head, container,false);
    }

    @Override
    public void onRefreshing(View refreshHead) {

    }
}
