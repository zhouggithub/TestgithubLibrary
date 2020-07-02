package com.example.zhouganglibrary.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.hktoutiao.toutiao.R;

/**
 * Created by rui on 2019/1/14.
 */

public class ClearCachDialog extends Dialog{
    private TextView tv;
    private Context context;
    public ClearCachDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉默认的title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉白色边角 我的小米手机在xml里设置 android:background="@android:color/transparent"居然不生效
        //所以在代码里设置，不知道是不是小米手机的原因
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.clear_cach);
        //Log.i("LHD", "LoadingDialog onCreate");
        tv = (TextView) findViewById(R.id.tv);
        tv.setText(context.getResources().getString(R.string.during_clear_cach));
        LinearLayout linearLayout = (LinearLayout) this.findViewById(R.id.LinearLayout);
        linearLayout.getBackground().setAlpha(210);
    }

}
