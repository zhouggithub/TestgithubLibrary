package com.example.zhouganglibrary.utils;

import android.content.Context;
import android.widget.Toast;

import cn.hktoutiao.toutiao.app.App;


public class ZgToast {

    private static Context mContext = App.getContext();


    private static Toast toast;
    /**
     * 强大的吐司，能够连续弹的吐司
     * @param text
     */
    public static void showZgToast(final String text){
        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if(toast==null){
                    toast = Toast.makeText(mContext, text,Toast.LENGTH_SHORT);
                }else {
                    toast.setText(text);//如果不为空，则直接改变当前toast的文本
                }
                toast.show();
            }
        });

    }
    public static void showToast(String info) {
        Toast.makeText(mContext, info, Toast.LENGTH_SHORT).show();
    }

    public static void reset(){
        toast=null;
    }
}