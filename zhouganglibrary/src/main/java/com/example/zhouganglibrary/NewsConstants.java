package com.example.zhouganglibrary;

import android.os.Environment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewsConstants {

	// Add by Devin.huang 2015.9.24 Note for release Version 3.1.4
	public static final String TAG = "suishengting";

	public static void showLog(String info) {
		System.out.print(TAG + "   " + info + "  " + "\n");
	}

	public static void showLog(String className, String info) {
		System.out.print(TAG + " : " + className + "   " + info + "  " + "\n");
	}

	public static final String APPID = "0003c";
	public static final String SYSVERSION = android.os.Build.VERSION.RELEASE;// 系统版本
	public static final String MODEL = android.os.Build.MODEL;// 设备型号

	public static final int COMMENT_TYPE_GT = 1;// 跟帖
	public static final int COMMENT_TYPE_HT = 2;// 回贴

	public static final boolean IS_UPLOAD_ERROR_LOG = true;// 是否上传崩溃日志

	// 91市场/小米市场
	public static final boolean MARKET_91 = false;    // Abandon
	public static final boolean MARKET_360 = false;
	public static final boolean MARKET_XIAOMI = false;
	public static final boolean MARKET_BAIDU = false;
	public static final boolean MARKET_YINGYONGBAO = false;

	public static final boolean SHOW_AD = true;// 是否显示广告

	public static String DEVICE_MAC = "";
	public static String JPUSH_ALIAS = "";// 极光推送别名
	public static String DEVICE_UUID = "";
	public static String APP_ID = "wxe5d5bda18d059749";

	// 允许匿名发贴的值
	public static final String VALUE_ANONYMOUS = "1";

	// 文件
	public static final String FILE_NAME_XML_CONFIG = "config"; // 配置信息xml文件
	public static final String FILE_NAME_XML_COVER = "cover"; // 封面xml文件
	public static final String FILE_NAME_XML_ALL_COLUMNS = "all_columns"; // 所有栏目xml文件（推荐版块所有的小栏目2.0）
	public static final String FILE_NAME_XML_RECOMMEND_COLUMNS = "recommend_columns"; // 推荐栏目xml文件1
	public static final String FILE_NAME_FILE_COVER = "cover-img"; // 封面图片文件
	public static final String FILE_NAME_SESSION = "session.ser"; // 封面图片文件
	public static final String FILE_NAME_TODAYRECOMMEND = "todayrecommend";// 今日推荐XML文件

	// 所有栏目的缓存时间
	public static final String MAP_KEY_ALL_COLUMN_REFRESH_TIME = "all-column-refresh-time"; // 所有栏目的刷新时间
	public static final String MAP_KEY_RECOMMEND_COLUMN_REFRESH_TIME = "recommend-column-refresh-time"; // 推荐栏目的刷新时间
	public static final String MAP_KEY_OFFLINE_REFRESH_TIME = "offline-refresh-time"; // 离线下载的刷新时间

	// 文件大小
	public static final long CONST_FILE_SIZE = 40960; // 40KB, 40960B

	/**************************** 应用目录 ****************************/
	public static final String PATH_SD = Environment.getExternalStorageDirectory().getAbsolutePath();
	public static final String PATH_ROOT = PATH_SD + "/listen_news/";
	public static final String ICON_PATH = PATH_ROOT + "icon.png";
	public static final String ERROR_PATH = PATH_ROOT + "errors/";
	public static final String DEBUG_PATH = PATH_ROOT + "debug/";

	// 广告图片目录
	public static final String PATH_AD = PATH_ROOT + "ad/";// 广告目录(/news3/ad)
	// 存储目录
	public static final String PATH_IMAGE = PATH_ROOT + "pictures/";// 拍照目录(/news3/pictures)
	public static final String PATH_AUDIO = PATH_ROOT + "records/";// 录音目录(/news3/records)
	// 电台图片目录
	public static final String PATH_RADIO = PATH_ROOT + "radios/";
	// 缓存总目录
	public static final String PATH_CACHE = PATH_ROOT + "cache/";
	// 缓存子目录
	public static final String CACHE_IMAGE = PATH_CACHE + "images/";// 下载图片缓存目录(/news3/cache/images)
	public static final String CACHE_AUDIO = PATH_CACHE + "audios/";// 下载音频缓存目录(/news3/cache/audios)
	public static final String CACHE_MEDIA = PATH_CACHE + "medias/";// 下载视频缓存目录(/news3/cache/medias)
	public static final String CACHE_LOG = PATH_CACHE + "logs/";// 日志目录(/news3/cache/logs)
	public static final String CACHE_WEATHER = PATH_CACHE + "weather/";// 天气图片目录(/news3/cache/weather)
	public static final String CACHE_WEATHER_ICON = CACHE_WEATHER + "w_icon.png";// 天气图片目录(/news3/cache/weather)
	/**************************** 应用目录 ****************************/

	public static final String MP3_LIST = "mp3List";

	// prefs
	public static final String PREFS_NAME_BASE = "base";// 基础数据prefs
	public static final String PREFS_KEY_DB_INITIALIZED = "db_initialized"; // 是否初始化了数据库
	public static final String FILE_NMAE_USERINFO = "userinfo";// 用户信息
	public static final String PREFS_NAME_COLUMNS = "columns"; // 栏目订阅状态prefs（2.0改成所有栏目的缓存 ）
	public static final String SAVE_AUDIO_SIZE = "audiocachesize"; // 栏目订阅状态prefs（2.0改成所有栏目的缓存 ）
	public static final String SAVE_MEDIA_SIZE = "meidacachesize"; // 栏目订阅状态prefs（2.0改成所有栏目的缓存 ）
	// 数据库
	public static final String DB_NAME = "data.db"; // 数据库名称
	public static final int DB_VERSION = 5; // 数据库版本 第二版（第二期7.28）
	public static final String DB_TBNM_PROVINCE = "province"; // "省"表
	public static final String DB_TBNM_CITY = "city"; // "市"表
	public static final String DB_TBNM_NEWS = "news"; // 新闻表
	public static final String DB_TBNM_ALARM = "alarm"; // 闹钟表
	public static final String DB_TBNM_AUDIO = "audio"; // 音频表
	public static final String DB_TBNM_PLAYLIST = "playlist"; // 播放列表
	public static final String DB_TBNM_NEWSSTATE = "news_state"; // 新闻阅读状态
	public static final String DB_TBNM_RECORDING = "recording";// 录音列表

	// 偏好设置
	public static final String SP_SETTINGS = "settings"; // 设置prefs
	public static final String KEY_FONT_SIZE = "key_font_size"; // 正文字体大小
	public static final String KEY_WIFI_AUTO_OFFLINE = "key_wifi_auto_offline"; // wifi下自动离线
	public static final String KEY_ENABLE_PUSH = "key_enable_push"; // 推送设置
	public static final String KEY_ENABLE_LOOK_LISTEN = "key_enable_look_listen"; // 边看边听
	public static final String KEY_DOWNLOAD_ONLY_WIFI = "key_download_only_wifi"; // 仅wifi下下载图片和音频
	public static final String KEY_DOWNLOAD_MEDIA_ONLY_WIFI = "key_download_media_only_wifi"; // 仅wifi下下载视频
	public static final String KEY_WATCH_MEDIA_ONLY_WIFI = "key_watch_media_only_wifi";// 仅wifi看视频
	public static final String KEY_NIGHT_MODE = "key_night_mode"; // 黑夜模式
	public static final String KEY_RECOMMEND_CODE = "key_recommend_code"; // 推荐code
	public static final String IS_THRID_LOGIN = "is_third_login"; // 当前是否是第三方登录
	public static final String PREFS_KEY_DOWNLOAD_RESOURCE_WIFI_ONLY = "key_download_resource_wifi_only"; // 仅wifi下下载图片和音频

	// 分享
	public static final int MSG_TOAST = 1;
	public static final int MSG_ACTION_CCALLBACK = 2;
	public static final int MSG_CANCEL_NOTIFY = 3;

	// 获取音频文件名
	public static String getAudioPath() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.getDefault());
		StringBuffer sb = new StringBuffer(PATH_AUDIO);
		sb.append(sdf.format(new Date())).append(".mp3");
		return sb.toString();
	}

	// 获取音频文件名
	public static String getAudioPath2() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.getDefault());
		StringBuffer sb = new StringBuffer(PATH_AUDIO);
		sb.append(sdf.format(new Date())).append(".mp3");
		return sb.toString();
	}
}
