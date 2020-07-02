package com.example.zhouganglibrary.utils;

import android.app.Activity;
import android.view.WindowManager;

import cn.hktoutiao.toutiao.videolist.VideoPlayView;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by zhou_gang on 2018/12/7.
 */

public class SinglePlayer {
    private static VideoPlayView mVideoPlayView ;
    public static VideoPlayView getPlayer(final Activity activity){
        if (mVideoPlayView == null) {
            mVideoPlayView = new VideoPlayView(activity);
        }
        return mVideoPlayView;
    }

    public static void setmVideoPlayView(VideoPlayView mVideoPlayView) {
        SinglePlayer.mVideoPlayView = mVideoPlayView;
    }

    public void setComp(final Activity activity){
        mVideoPlayView.setCompletionListener(new VideoPlayView.CompletionListener() {
            @Override
            public void completion(IMediaPlayer mp) {


                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                if (mOnCompete != null) {
                    mOnCompete.onCompet();
                }

//                View itemView = mLayoutManager.findViewByPosition(mPosition);
//                FrameLayout frameLayout = null;
//                if (itemView != null) {
//                    frameLayout =  itemView
//                            .findViewById(R.id.layout_video);
//                }
//                if (frameLayout != null) {
//                    frameLayout.removeView(mVideoPlayView);
//                }
//                ViewGroup viewGroup = (ViewGroup) mVideoPlayView.getParent();
//                viewGroup.removeAllViews();

//                mCommonListAdapter.notifyItemChanged(mPosition);
                if (mVideoPlayView != null) {
                    mVideoPlayView.setShowContoller(true);
                    mVideoPlayView.stop();
                    mVideoPlayView.release();
                    mVideoPlayView.onDestroy();
                }
            }
        });
    }

    public interface OnCompete{
        void onCompet();
    };
    private OnCompete mOnCompete;

    public void setOnCompete(OnCompete onCompete) {
        mOnCompete = onCompete;
    }
}
