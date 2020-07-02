package com.example.zhouganglibrary.views;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cn.hktoutiao.toutiao.R;
import cn.hktoutiao.toutiao.adapter.OptionAdater;
import cn.hktoutiao.toutiao.beans.AllOptionBean;
import cn.hktoutiao.toutiao.beans.SuperBean;
import cn.hktoutiao.toutiao.event.RefreshOption;
import cn.hktoutiao.toutiao.net.http.RetrofitClient;
import cn.hktoutiao.toutiao.net.subscribers.ProgressSubscriber;
import cn.hktoutiao.toutiao.net.subscribers.SubscriberListener;
import cn.hktoutiao.toutiao.utils.Constants;
import cn.hktoutiao.toutiao.utils.LogUtils;
import cn.hktoutiao.toutiao.utils.LoginOptionSaveUtil;
import cn.hktoutiao.toutiao.utils.Params;
import cn.hktoutiao.toutiao.utils.SpUtils;
import cn.hktoutiao.toutiao.utils.UnLoginOptionSaveUtil;
import cn.hktoutiao.toutiao.utils.Urls;
import rx.Subscriber;

/**
 * Created by ccb on 2017/9/15.
 * 选择照片来源  相机/图库 diaolog
 */

public class ChannelDialog implements View.OnClickListener {
    Activity mActivity;
    /**
     * 下面都是弹出框选择图片的内容
     */
    private Button btnPictureStorage;
    private Button btnTakePhoto;
    private Button btnCancel;
    private OnChoseListener mOnChoseListener;
    private List<AllOptionBean.OptionalSignListBean> optionalSignList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private Dialog mDialog;  //更换头像弹出框
    private OptionAdater optionAdater;
    private List<AllOptionBean.OptionalSignListBean> allOptionalSignList = new ArrayList<>();
    private String alertType;
    private TextView tv_complete;
    private ImageView iv_close;
    private StringBuffer mSbChannels = new StringBuffer();

    private RelativeLayout ll;

    public interface OnChoseListener {
        void onTakePhoto(); //去拍照

        void onPicAlbum(); //去开启图库
    }

    public ChannelDialog(Activity activity, OnChoseListener onChoseListener) {
        this.mActivity = activity;
        this.mOnChoseListener = onChoseListener;
        showDialog();
    }

    /*弹出框，选择头像*/
    private void showDialog() {
        View view = View.inflate(mActivity, R.layout.channel_dialog, null);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        tv_complete = view.findViewById(R.id.tv_complete);
        iv_close = view.findViewById(R.id.iv_close);

        tv_complete.setOnClickListener(this);
        iv_close.setOnClickListener(this);


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


        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        optionAdater = new OptionAdater(R.layout.item_option, allOptionalSignList, alertType);
        mRecyclerView.setAdapter(optionAdater);

        getData();

    }


    private void getData() {

        allOptionalSignList.clear();


        SubscriberListener<AllOptionBean> listener = new SubscriberListener<AllOptionBean>() {

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AllOptionBean allOptionBean) {
                String json = new Gson().toJson(allOptionBean);
                LogUtils.info("信源json", json);

                optionalSignList = allOptionBean.getOptionalSignList();

//                mRlTop.setVisibility(View.VISIBLE);
//                mTvDesc.setVisibility(View.VISIBLE);

                allOptionalSignList.addAll(optionalSignList);

                AllOptionBean.OptionalSignListBean optionalSignListBean2 = new AllOptionBean.OptionalSignListBean();
                optionalSignListBean2.setTitle("底部");
                allOptionalSignList.add(optionalSignListBean2);


                if (TextUtils.isEmpty(SpUtils.getString(mActivity, "token", ""))) {
                    LinkedList<String> unLoginOptions = UnLoginOptionSaveUtil.query(mActivity);

                    for (int i = 0; i < allOptionalSignList.size()-1; i++) {
                        List<AllOptionBean.OptionalSignListBean.HomeChannelListBean> homeChannelList = allOptionalSignList.get(i).getHomeChannelList();
                        for (int i1 = 0; i1 < homeChannelList.size(); i1++) {
                            if (unLoginOptions.contains(homeChannelList.get(i1).getChannelId() + "")) {
                                homeChannelList.get(i1).setSelected(true);
                                LogUtils.info("channelMange已选中" + homeChannelList.get(i1).getTitle());
                            } else {
                                homeChannelList.get(i1).setSelected(false);
                                LogUtils.info("channelMange未选中" + homeChannelList.get(i1).getTitle());
                            }

                        }
                    }

                } else {
                    LinkedList<String> loginOptions = LoginOptionSaveUtil.query(mActivity);
                    for (int i = 0; i < allOptionalSignList.size()-1; i++) {
                        List<AllOptionBean.OptionalSignListBean.HomeChannelListBean> homeChannelList = allOptionalSignList.get(i).getHomeChannelList();
                        for (int i1 = 0; i1 < homeChannelList.size(); i1++) {
                            if (loginOptions.contains(homeChannelList.get(i1).getChannelId() + "")) {
                                homeChannelList.get(i1).setSelected(true);
                            } else {
                                homeChannelList.get(i1).setSelected(false);
                            }
                        }
                    }

                }

                if (optionAdater != null) {
                    optionAdater.notifyDataSetChanged();
                }

                mDialog.show();
            }
        };
        HashMap<String, String> hm = new HashMap<>();
        hm.put("userid", "0");
        RetrofitClient.getInstance(mActivity).createBaseApi().getAllOptionData(Urls.SELECTCHANNELSIGNLIST, hm, new ProgressSubscriber(listener, mActivity));
    }

    /**
     * dialog点击事件，开启 相机，图库，取消
     */

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_close:
            case R.id.tv_complete:
                compelteAdd();
                mDialog.dismiss();
                break;
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

    private void compelteAdd() {
        if (TextUtils.isEmpty(SpUtils.getString(mActivity, "token", ""))) {
            for (int i = 0; i < allOptionalSignList.size() - 1; i++) {
                List<AllOptionBean.OptionalSignListBean.HomeChannelListBean> homeChannelList = allOptionalSignList.get(i).getHomeChannelList();
                for (int i1 = 0; i1 < homeChannelList.size(); i1++) {
                    if (homeChannelList.get(i1).isSelected()) {
                        mSbChannels.append(homeChannelList.get(i1).getChannelId() + ",");
                    }
                }
            }


        } else {
            for (int i = 0; i < allOptionalSignList.size() - 1; i++) {
                List<AllOptionBean.OptionalSignListBean.HomeChannelListBean> homeChannelList = allOptionalSignList.get(i).getHomeChannelList();
                for (int i1 = 0; i1 < homeChannelList.size(); i1++) {
                    if (homeChannelList.get(i1).isSelected()) {
                        mSbChannels.append(homeChannelList.get(i1).getChannelId() + ",");
                    }
                }
            }


        }

        if (mSbChannels.length() != 0) {
            mSbChannels.deleteCharAt(mSbChannels.length() - 1);
            LogUtils.info("ChannelManageActmSbChannels==", mSbChannels.toString());
            SpUtils.putBoolean(mActivity, "showOption", false);
            //存储
            if (TextUtils.isEmpty(SpUtils.getString(mActivity, "token", ""))) {
                SpUtils.putString(mActivity, Params.OPTION_UNLOGIN, mSbChannels.toString());
            } else {
                SpUtils.putString(mActivity, Params.OPTION_LOGIN, mSbChannels.toString());
            }
        } else {
            LogUtils.info("ChannelManageActmSbChannels==", "kongl");
            SpUtils.putBoolean(mActivity, "showOption", true);
            //存储
            if (TextUtils.isEmpty(SpUtils.getString(mActivity, "token", ""))) {
                SpUtils.putString(mActivity, Params.OPTION_UNLOGIN, "");
            } else {
                SpUtils.putString(mActivity, Params.OPTION_LOGIN, "");
            }
        }

//        ZgToast.showZgToast(mSbChannels.toString());
        postChannelAdd();
        EventBus.getDefault().post(new RefreshOption(mSbChannels.toString()));
    }

    private void postChannelAdd() {
        Map<String, String> hm = new HashMap<>();
        hm.put(Params.CHANNELID, mSbChannels.toString());
        LogUtils.info("CHANNELS", mSbChannels.toString());
        hm.put(Params.UID, TextUtils.isEmpty(SpUtils.getString(mActivity, Params.UID, "")) ? "0" : SpUtils.getString(mActivity, Params.UID, ""));
        hm.put(Params.TOKEN, TextUtils.isEmpty(SpUtils.getString(mActivity, Params.TOKEN, "")) ? "0" : SpUtils.getString(mActivity, Params.TOKEN, ""));
        hm.put(Params.SERIAL, Constants.SERIAL);
        hm.put(Params.APPID, "006b");
        LogUtils.info("serial===", Constants.SERIAL);
//        RetrofitClient.getInstance(this).createBaseApi().getConfigData(url, new ProgressSubscriber(listener, this));
        RetrofitClient.getInstance(mActivity).createBaseApi().postChannelData(Urls.SELECTCHANNEADD, hm, new Subscriber<SuperBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(SuperBean data) {
                String listjson = new Gson().toJson(data);
                LogUtils.info("添加信源===", listjson);
            }
        });
    }

}
