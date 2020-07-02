package com.example.zhouganglibrary.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rui on 2018/12/20.
 */

public class ServiceMyViewHolder extends RecyclerView.ViewHolder{

   /* TextView tv_title;
    ImageView iv_image;

    public ServiceMyViewHolder(View itemView) {
        super(itemView);

        tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        iv_image = itemView.findViewById(R.id.iv_image);

    }*/
   private Map<Integer, View> mCacheView;

    public ServiceMyViewHolder(View itemView) {
        super(itemView);
        mCacheView = new HashMap<>();
    }

    public View getView(int resId) {
        View view;
        if (mCacheView.containsKey(resId)) {
            view = mCacheView.get(resId);
        } else {
            view = itemView.findViewById(resId);
            mCacheView.put(resId, view);
        }
        return view;
    }
}