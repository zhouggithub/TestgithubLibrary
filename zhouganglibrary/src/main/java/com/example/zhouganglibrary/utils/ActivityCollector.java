package com.example.zhouganglibrary.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import cn.hktoutiao.toutiao.ui.activities.MainAct;

/**
 * Created by cxc
 * Activity管理类
 */

public class ActivityCollector {
    public static List<Activity> activitys = new ArrayList<>();
    public static void addActivity(Activity activity) {
        activitys.add(activity);//把传入的Activity添加到List中
    }

    public static void removeActivity(Activity activity) {
        activitys.remove(activity);//根据传入的Activity来删除
    }
    public static void removeAll() {
        for (Activity activity : activitys) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activitys.clear();
    }
    /**
     除了传来的Activity其他的全部删除
     可以传多个Activity
     */
    public static void removeAll(Class<?>... clazz) {
        boolean isExist = false;
        for (Activity act : activitys) {
            for (Class c : clazz) {
                if (act.getClass().isAssignableFrom(c)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                if (!act.isFinishing()) {
                    act.finish();
                }
            } else {
                isExist = false;
            }
        }
    }
    /**
     从Activity集合查询, 传入的Activity是否存在
     如果存在就返回该Activity,不存在就返回null
     */
    public static Activity getActivity() {
        for (int i = 0; i < activitys.size(); i++) {
            // 判断是否是自身或者子类
            if (activitys.get(i) instanceof MainAct) {
                return activitys.get(i);
            }
        }
        return null;
    }

/**
 * 获取最顶层的activity
 */
    public static Activity getTopActivity() {
        return activitys.get(activitys.size()-1);
    }
}
