package com.example.zhouganglibrary.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import cn.hktoutiao.toutiao.R;
import cn.hktoutiao.toutiao.ui.fragments.CommonListFragment;


/**
 * Created by hsy on 16/6/28.
 * 正常 圆形 +  提示语进度条
 */
public class DialogUtil implements View.OnClickListener {
    private Dialog mDialog;
    Activity mActivity;
    /**
     * 下面都是弹出框选择图片的内容
     *
     */
    private Button changePic;
    private Button btnCancel;
    private static DialogUtil dialogUtil;

    private ProgressDialog progressDialog;

    public static DialogUtil getInstance() {
        if (dialogUtil == null) {
            dialogUtil = new DialogUtil();
        }
            return dialogUtil;
    }
    /**
     * 创建 dialog
     */
    public ProgressDialog createProgress( Context context,String msg) {
        ProgressDialog   progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        return progressDialog;
    }
    /*弹出框，选择头像*/
    public void showDialog(Activity activity,OnBottListener onBottListener) {
        this.mOnBottListener=onBottListener;
        this.mActivity = activity;
        View view = View.inflate(mActivity, R.layout.fm_choose_dialog, null);
        changePic = (Button) view.findViewById(R.id.change_pic);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        /**设置点击事件*/
        changePic.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        mDialog = new Dialog(mActivity, R.style.transparentFrameWindowStyle);
        mDialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = mDialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.dialog_bottom_in_anim);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = mActivity.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        mDialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();
    }

    private OnBottListener mOnBottListener;
    public interface OnBottListener {
        void changePic(); //更换封面
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_pic://换封面
                mOnBottListener.changePic();
                mDialog.dismiss();
                break;
            case R.id.btn_cancel://取消
                mDialog.dismiss();
                break;
        }
    }



    AlertDialog dialog3G = null;
    // 3G状态对话框
    public void dialog_3g(final Context context, final CommonListFragment commonListFragment, final int position) {
        if (dialog3G != null) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("1站视频");
        builder.setMessage("当前是4G模式，会消耗较多流量，是否继续？");
        builder.setPositiveButton("关闭", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dialog3G = null;
//                dataLoad.stopMusic();
            }
        });
        builder.setNegativeButton("继续", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                SharedPreferences prefs = context.getSharedPreferences(NewsConstants.SP_SETTINGS, Context.MODE_PRIVATE);
//                prefs.edit().putBoolean(NewsConstants.KEY_DOWNLOAD_ONLY_WIFI, false).commit();
//                AppSettings.DOWNLOAD_ONLY_WIFI = false;
                dialog3G.dismiss();
//                services.submitPlay();
                dialog3G = null;

                commonListFragment.startPlay(position, 0);
            }
        });

        if (dialog3G == null) {
            dialog3G = builder.create();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                dialog3G.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
            } else {
                dialog3G.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            }
            dialog3G.show();

        }

    }

}

