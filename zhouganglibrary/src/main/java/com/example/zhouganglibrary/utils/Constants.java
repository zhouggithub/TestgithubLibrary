package com.example.zhouganglibrary.utils;

import android.os.Environment;

/**
 * Created by ccb on 2017/10/11.
 * 常量类
 */

public class Constants {
    public static final String TAG = "cn.hktoutiao.toutiao";
    public static final String PARAM_TARGET_URL = "PARAM_TARGET_URL";
    public static final String PARAM_TITLE = "PARAM_TITLE";
    public static final String PARAM_IMAGE_URL="PARAM_IMAGE_URL";
    public static final String PARAM_SUMMARY = "PARAM_SUMMARY";
    public static final String PARAM_APPNAME = "PARAM_APPNAME";
    public static final String PARAM_APP_SOURCE = "PARAM_APP_SOURCE";
    public static  String DEVICE_MAC = "";
    public static  String DEVICE_UUID = "";
    public static  String SERIAL = "";
    public static  String APPID = "006b";
    public static  String WEIXINID = "wxb68f83925d8a8bff";
    public static  String QQID = "1106563596";
    public static  String VERSIONCODE ="2" ;
    public static  long COUNTSECONDS = 8000;

   /* public static String shareUrl = "http://www.tap2world.com";
    public static String getUpdateVersionInfo = "http://192.168.147.164:9002/api/VerNotice/GetVerNotice";//http://192.168.147.164:9002
    public static String uploadVersionInfo =  "http://192.168.147.164:9002/api/VerNotice/Times";
    public static String jpushNotifyList = "http://lyatest1.eastday.com:82/api/PushMessage/List";*/

    public static int TEXT_SIZE = 0;
    //登录
    public static int FACEBOOK=101;
    public static int GOOGLE_PLUS=102;
    public static int WE_CHAT=103;
    public static int QQ = 104;

    public static String AF_DEV_KEY = "6r3vf9jP7LqkFjfd6aQky";//6r3vf9jP7LqkFjfd6aQky
   // public static String serviceUrl = baseUrl+"/service.json";
   // public static String loginUrl = "http://192.168.146.156:8080/api/user/thirdPartyLogin";
   // public static String checkLoginStateUrl = "http://192.168.146.156:8080/api/user/checkUserState";
   // public static String logoutUrl = "http://192.168.146.156:8080/api/user/logout";
   // public static String collectionUrl = baseUrl + "/api/favorite/list";
  //  public static String historyUrl = baseUrl + "/api/history/list";

    //public static String feedbackUrl = baseUrl + "/api/Opinion/Insert";
  //  public static String uploadPicUrl = baseUrl + "/api/Opinion/UploadFile";

   // public static String errorListUrl = baseUrl + "/api/Error/typeList";
   // public static String commitErrorUrl = baseUrl + "/api/Error/Complain";
    //public static String benifitHeadUrl = baseUrl + "/preferential.json";
   // public static String benifitUrl = "http://47.52.202.1/api/Home/GetSettings" + "/api/Preferential/list";//UrlTranferUtil.getFinalUrl(Urls.DOMAINNAME + "/api/Preferential/list");


   // public static String chineseTraditional = "CNT";
   // public static String chineseSimple = "CNS";
   // public static String chineseMainland = "CN";
  //  public static String chineseHK = "HK";


    public static String WECHAT_LOGIN = "2";
    public static String QQ_LOGIN = "3";
    public static String FACEBOOK_LOGIN = "5";
    public static String GOOGLE_PLUS_LOGIN = "6";


    //showActivity的常量
    public static class ShowActivity{
        public static final String CLASSNAME = "CLASSNAME";
        public static final String ARGS = "ARGS";
    }
    public static  String ADVER_STORAGE_DIR = "";
    public static  String Glide_STORAGE_DIR = "";
    public static final String PIC_STORAGE_DIR = Environment.getExternalStorageDirectory() + "/dianzhitianxia/webpic/";
    public static boolean isMain = false;
}
