package com.example.zhouganglibrary.views;

/**
 * Created by rui on 2019/1/5.
 */

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import cn.hktoutiao.toutiao.utils.DesignViewUtils;
import cn.hktoutiao.toutiao.utils.LogUtils;


/**
 * @author xuanyouwu
 * @email xuanyouwu@163.com
 * @time 2016-04-15 11:20
 * <p/>
 * 解决 嵌套nestScrolling appbar 刷新冲突
 */
public class SwipyAppBarScrollListener extends RecyclerView.OnScrollListener implements AppBarLayout.OnOffsetChangedListener {
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private boolean isAppBarLayoutOpen = true;
    private boolean isAppBarLayoutClose;
    Context context;
    public SwipyAppBarScrollListener(Context context, AppBarLayout appBarLayout, SmartRefreshLayout refreshLayout, RecyclerView recyclerView) {
        this.appBarLayout = appBarLayout;
        this.refreshLayout = refreshLayout;
        this.recyclerView = recyclerView;
        this.context = context;
        disptachScrollRefresh();
    }


    private void disptachScrollRefresh() {
        if (this.appBarLayout != null && this.recyclerView != null && refreshLayout != null) {
            this.appBarLayout.addOnOffsetChangedListener(this);
            this.recyclerView.addOnScrollListener(this);
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        dispatchScroll();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        isAppBarLayoutOpen = DesignViewUtils.isAppBarLayoutOpen(verticalOffset);
        isAppBarLayoutClose = DesignViewUtils.isAppBarLayoutClose(appBarLayout, verticalOffset);
        dispatchScroll();

        LogUtils.error("verticalOffset"+verticalOffset);
       /* if(verticalOffset == -201){
            refreshLayout.setEnabled(true);
            LogUtils.error("test====+"+0000);
             refreshLayout.setNestedScrollingEnabled(true);
        }
*/
    }

    private void dispatchScroll() {
        if (this.recyclerView != null && this.appBarLayout != null && this.refreshLayout != null) {
            //不可滚动
            if ( ViewCompat.canScrollVertically(recyclerView, 1)) {
                LogUtils.error("verticalOffset" + "head");
                //recyclerView.setNestedScrollingEnabled(true);
               // refreshLayout.setNestedScrollingEnabled(true);
                refreshLayout.setEnabled(true);

            }
            else if(ViewCompat.canScrollVertically(recyclerView, -1)){
                LogUtils.error("verticalOffset" + "tail");
               // appBarLayout.setEnabled(false);
                recyclerView.setNestedScrollingEnabled(true);
                refreshLayout.setNestedScrollingEnabled(true);
                refreshLayout.setEnabled(true);


            }else{
               // appBarLayout.setEnabled(false);
                recyclerView.setNestedScrollingEnabled(true);
                refreshLayout.setNestedScrollingEnabled(true);
              //  refreshLayout.setEnabled(true);
                LogUtils.error("verticalOffset" + "other");
                refreshLayout.setEnabled(true);
            }
        /*    if ((ViewCompat.canScrollVertically(recyclerView, -1) || ViewCompat.canScrollVertically(recyclerView, 1))) {
                refreshLayout.setEnabled(true);
                LogUtils.error("test====+"+1111);
               // refreshLayout.setNestedScrollingEnabled(isAppBarLayoutOpen);
            } else//可以滚动
            {
                if (isAppBarLayoutOpen || isAppBarLayoutClose) {
                    if (!ViewCompat.canScrollVertically(recyclerView, -1) && isAppBarLayoutOpen) {
                        refreshLayout.setEnabled(true);
                        LogUtils.error("test====+"+2222);

                        //refreshLayout.setNestedScrollingEnabled(true);
                    } else if (isAppBarLayoutClose && !ViewCompat.canScrollVertically(recyclerView, 1)) {
                        refreshLayout.setEnabled(true);
                        LogUtils.error("test====+"+3333);

                        //refreshLayout.setNestedScrollingEnabled(true);

                    } else {
                        refreshLayout.setEnabled(false);
                        LogUtils.error("test====+"+4444);

                        //refreshLayout.setNestedScrollingEnabled(false);

                    }
                } else {
                    LogUtils.error("test====+"+5555);
                     refreshLayout.setEnabled(false);
                    //refreshLayout.setNestedScrollingEnabled(false);

                }
            }*/
        }
    }
}