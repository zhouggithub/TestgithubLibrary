package com.example.zhouganglibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/8/2
 *     desc  : SP读写工具类
 * </pre>
 */
public class SpUtils {

    private SpUtils() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

    /**
     * SP的name值
     * <p>可通过修改PREFERENCE_NAME变量修改SP的name值</p>
     */
    public static String PREFERENCE_NAME = "ANDROID_UTIL_CODE";

    /**
     * SP中写入String类型value
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     * @return true: 写入成功<br>false: 写入失败
     */
    public static boolean putString(Context context, String key, String value) {
        return getSP(context).edit().putString(key, value).commit();
    }

    /**
     * SP中读取String
     *
     * @param context 上下文
     * @param key     键
     * @return 存在返回对应值，不存在返回默认值null
     */
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    /**
     * SP中读取String
     *
     * @param context      上下文
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值defaultValue
     */
    public static String getString(Context context, String key, String defaultValue) {
        return getSP(context).getString(key, defaultValue);
    }

    /**
     * SP中写入int类型value
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     * @return true: 写入成功<br>false: 写入失败
     */
    public static boolean putInt(Context context, String key, int value) {
        return getSP(context).edit().putInt(key, value).commit();
    }

    /**
     * SP中读取int
     *
     * @param context 上下文
     * @param key     键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    /**
     * SP中读取int
     *
     * @param context      上下文
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值defaultValue
     */
    public static int getInt(Context context, String key, int defaultValue) {
        return getSP(context).getInt(key, defaultValue);
    }

    /**
     * SP中写入long类型value
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     * @return true: 写入成功<br>false: 写入失败
     */
    public static boolean putLong(Context context, String key, long value) {
        return getSP(context).edit().putLong(key, value).commit();
    }

    /**
     * SP中读取long
     *
     * @param context 上下文
     * @param key     键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public static long getLong(Context context, String key) {
        return getLong(context, key, -1);
    }

    /**
     * SP中读取long
     *
     * @param context      上下文
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值defaultValue
     */
    public static long getLong(Context context, String key, long defaultValue) {
        return getSP(context).getLong(key, defaultValue);
    }

    /**
     * SP中写入float类型value
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     * @return true: 写入成功<br>false: 写入失败
     */
    public static boolean putFloat(Context context, String key, float value) {
        return getSP(context).edit().putFloat(key, value).commit();
    }

    /**
     * SP中读取float
     *
     * @param context 上下文
     * @param key     键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1);
    }

    /**
     * SP中读取float
     *
     * @param context      上下文
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值defaultValue
     */
    public static float getFloat(Context context, String key, float defaultValue) {
        return getSP(context).getFloat(key, defaultValue);
    }

    /**
     * SP中写入boolean类型value
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     * @return true: 写入成功<br>false: 写入失败
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        return getSP(context).edit().putBoolean(key, value).commit();
    }

    /**
     * SP中读取boolean
     *
     * @param context 上下文
     * @param key     键
     * @return 存在返回对应值，不存在返回默认值false
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * SP中读取boolean
     *
     * @param context      上下文
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值defaultValue
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getSP(context).getBoolean(key, defaultValue);
    }

    /**
     * 获取name为PREFERENCE_NAME的SP对象
     *
     * @param context 上下文
     * @return SP
     */
    private static SharedPreferences getSP(Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }


    /**
     * 保存登录信息
     *
     * @param context
     * @param result
     */

    /**
     * 保存 对于文章点赞状态
     * @param context
     * @param articleId  资讯/话题 id
     * @param isLike  true 为已点赞
     */

    public static void saveLikeState(Context context, int articleId, boolean isLike) {
        SpUtils.putBoolean(context, "article_id_" + articleId, isLike);
    }

    /**
     * 获取当前文章是是否点赞状态
     * @param context
     * @param articleId
     * @return
     */
    public static boolean getLikeState(Context context, int articleId) {
       return SpUtils.getBoolean(context, "article_id_" + articleId, false);
    }
    /**
     * 保存 对于文章点赞状态
     * @param context
     * @param articleId  资讯/话题 id
     * @param isLike  true 为已点赞
     */

    public static void saveCommentLikeState(Context context, int articleId, boolean isLike) {
        SpUtils.putBoolean(context, "article_id_comment_id" + articleId, isLike);
    }

    /**
     * 获取当前文章是是否点赞状态
     * @param context
     * @param articleId
     * @return
     */
    public static boolean getCommentLikeState(Context context, int articleId) {
        return SpUtils.getBoolean(context, "article_id_comment_id" + articleId, false);
    }



    public static void saveRecruitState(Context context, int articleId,boolean isRecruit) {
        SpUtils.putBoolean(context, "recruit_article_id_" + articleId,isRecruit);
    }

    public static boolean getRecruitState(Context context, int articleId) {
        return SpUtils.getBoolean(context, "recruit_article_id_" + articleId, false);
    }

    public static void putSet(String key) {
////保存搜索记录
//        HashSet<String> hashSet = (HashSet<String>) SpUtils.get(mContext, Constant.HISTORYTEXT, new HashSet<String>());
//        //直接在返回对象上修改了值
//        hashSet.add(key);
//        boolean isSuccess = SpUtils.putCommit(mContext, Constant.HISTORYTEXT, hashSet);
//        //打印同步写入的结果
//        Logger.d("isSuccess=" + isSuccess);

    }
}
