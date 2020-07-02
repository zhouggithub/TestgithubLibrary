package com.example.zhouganglibrary.views;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import cn.hktoutiao.toutiao.R;

/**
 * Created by ccb on 2017/9/15.
 * 选择照片来源  相机/图库 diaolog
 */

public class ChoosePicDialog implements View.OnClickListener {
    Activity mActivity;
    /**
     * 下面都是弹出框选择图片的内容
     *
     */
    private Button btnPictureStorage;
    private Button btnTakePhoto;
    private Button btnCancel;
    private OnChoseListener mOnChoseListener;
    public interface OnChoseListener {
        void onTakePhoto(); //去拍照
        void onPicAlbum(); //去开启图库
    }

    public ChoosePicDialog(Activity activity, OnChoseListener onChoseListener) {
        this.mActivity = activity;
        this.mOnChoseListener = onChoseListener;
        showDialog();
    }

    private Dialog mDialog;  //更换头像弹出框

    /*弹出框，选择头像*/
    private void showDialog() {
        View view = View.inflate(mActivity, R.layout.photo_choose_dialog, null);
        btnPictureStorage = (Button) view.findViewById(R.id.btn_picture_storage);
        btnTakePhoto = (Button) view.findViewById(R.id.btn_take_photo);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        /**设置点击事件*/
        btnPictureStorage.setOnClickListener(this);
        btnTakePhoto.setOnClickListener(this);
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
    /**
     * dialog点击事件，开启 相机，图库，取消
     */

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_picture_storage://图库
//                mOnChoseListener.onPicAlbum();
                mDialog.dismiss();
                break;
            case R.id.btn_take_photo: //拍照
//                new RxPermissions(mActivity).request(
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.CAMERA
//                ).subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean grant) throws Exception {
//                        if (grant) {
//                            mOnChoseListener.onTakePhoto();
//                            mDialog.dismiss();
//                        } else {
//                            ToastUtil.show(mActivity,"没有权限无法进行拍照");
//                        }
//                    }
//                });
                break;
            case R.id.btn_cancel://取消
                mDialog.dismiss();
                break;
        }
    }
}
