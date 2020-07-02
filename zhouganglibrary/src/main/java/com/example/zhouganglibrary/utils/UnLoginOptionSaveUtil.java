package com.example.zhouganglibrary.utils;

import android.content.Context;
import android.text.TextUtils;

import java.util.LinkedList;

/**
 * Created by ccb on 2017/11/4.
 * 搜索历史关键词保存工具类
 */

public class UnLoginOptionSaveUtil {
    /**
     * 搜索历史 关键词 sp保存key
     */
    private static final String SEARCH_HISTORY_KEY =Params.OPTION_UNLOGIN;
    /**
     * 分隔符 防注入
     */
    public static final String SPLITE_SIGNAL = "!@#$";
    public static final String SPLITE_SIGNAL_FORMAT = "\\!\\@\\#\\$";

    /**
     * 关键词 总字数 数量上限 45 三行
     */
    private static final int KEY_LENGTH = 45;

    /***
     * 插入搜索历史关键词
     * @param context
     * @param searchKey
     */
    public static void insert(Context context, String searchKey) {
        LinkedList<String> list = query(context);
        list.add(0, searchKey);
        for (int i = 1; i < list.size(); i++) { //去重
            if (list.get(i).equals(list.get(0))) {
                list.remove(i);
                break;
            }
        }
        //最多10个
//        if (list.size() > 10) {
//            list.remove(list.size()-1);
//        }



        //计算总长度 过长 去尾部数据
//        int length = 0;
//        for (int i = 0; i < list.size(); i++) {
//            length += list.get(i).length();
//        }
//        while (length > KEY_LENGTH) {
//            list.remove(list.size() - 1);
//            length = 0;
//            for (int i = 0; i < list.size(); i++) {
//                length += list.get(i).length();
//            }
//        }
        //存储数据到sp
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                str += list.get(i) + SPLITE_SIGNAL;
            } else {
                str += list.get(i);
            }
        }
        SpUtils.putString(context, SEARCH_HISTORY_KEY, str);
    }

    /***
     * 查询 搜索历史关键词
     * @return
     */
    public static LinkedList<String> query(Context context) {
        LinkedList<String> list = new LinkedList<>();
        String keys = SpUtils.getString(context, SEARCH_HISTORY_KEY, "");
        if (!TextUtils.isEmpty(keys)) {
            if (keys.length() > 1) {
                String[] keyStrs = keys.split(",");
                for (int i = 0; i < keyStrs.length; i++) {
                    LogUtils.info("keyStrs", keyStrs[i]);
                    list.add(i, keyStrs[i]);
                }
            } else {
                list.add(keys);
            }

        }
        return list;
    }

    public static  void delete(Context context){
        SpUtils.putString(context, SEARCH_HISTORY_KEY, "");
    }
}
