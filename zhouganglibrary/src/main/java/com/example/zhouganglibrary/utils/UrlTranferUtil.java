package com.example.zhouganglibrary.utils;

import android.text.TextUtils;

import cn.hktoutiao.toutiao.app.App;
import cn.hktoutiao.toutiao.beans.ConfigBean;

/**
 * Created by zhougang on 2018/8/2.
 * description:
 */

public class UrlTranferUtil {
    public static String getImageUrl(String url){
        if(TextUtils.isEmpty(url)){
            return "";
        }
        if(url.startsWith("http")||url.startsWith("https")){
            return url;
        }

        String configJson = ACache.get(App.appContext).getAsString(Params.CONFIGURL);
        ConfigBean configBean = GsonUtil.parseJsonToBean(configJson, ConfigBean.class);
        if(configBean!=null){
            Urls.DOMAINNAME = configBean.getDomainName();
        }


        if (!url.startsWith("/")) {
            return Urls.DOMAINNAME +"/"+url;
        }
        LogUtils.info("图片地址",Urls.DOMAINNAME +url);
        return Urls.DOMAINNAME +url;
    }

    public static String getShareImageUrl(String url){
         if(TextUtils.isEmpty(url)){
            return "";
        }

        String configJson = ACache.get(App.appContext).getAsString(Params.CONFIGURL);
        ConfigBean configBean = GsonUtil.parseJsonToBean(configJson, ConfigBean.class);
        if(configBean!=null){
            Urls.SHAREDOMAINURL = configBean.getShareDomainName();
        }


      
        if(url.startsWith("https://www.c.tap2world.com")){
            return url.replace("https://www.c.tap2world.com",Urls.SHAREDOMAINURL);
        }
        if(url.startsWith("https://www.k.tap2world.com"))
            return url.replace("https://www.k.tap2world.com",Urls.SHAREDOMAINURL);

		 if(url.startsWith("http")||url.startsWith("https")){
            return url;
        }
        if (!url.startsWith("/")) {
            return Urls.SHAREDOMAINURL +"/"+url;
        }
        LogUtils.info("图片地址",Urls.SHAREDOMAINURL +url);
        return Urls.SHAREDOMAINURL +url;
    }


    public static String getFinalUrl(String url){



        if(TextUtils.isEmpty(url)){
            return "";
        }
        if(url.startsWith("http")||url.startsWith("https")){
            return url;
        }

        String configJson = ACache.get(App.appContext).getAsString(Params.CONFIGURL);
        ConfigBean configBean = GsonUtil.parseJsonToBean(configJson, ConfigBean.class);
        if(configBean!=null){
            Urls.DOMAINNAME = configBean.getDomainName();
        }


        return Urls.DOMAINNAME + url;
    }
    public static String getShareFinalUrl(String url){
        if(TextUtils.isEmpty(url)){
            return "";
        }

        String configJson = ACache.get(App.appContext).getAsString(Params.CONFIGURL);
        ConfigBean configBean = GsonUtil.parseJsonToBean(configJson, ConfigBean.class);
        if(configBean!=null){
            Urls.SHAREDOMAINURL = configBean.getShareDomainName();
        }

        if(url.startsWith("https://www.c.tap2world.com")){
            return url.replace("https://www.c.tap2world.com",Urls.SHAREDOMAINURL);
        }
        if(url.startsWith("https://www.k.tap2world.com"))
            return url.replace("https://www.k.tap2world.com",Urls.SHAREDOMAINURL);

		if(url.startsWith("http")||url.startsWith("https")){
            return url;
        }
		if (!url.startsWith("/")) {
            return Urls.SHAREDOMAINURL +"/"+url;
        }
        return Urls.SHAREDOMAINURL + url;
    }
    public static String geDownFinalUrl(String url){
        if(TextUtils.isEmpty(url)){
            return "";
        }
        if(url.startsWith("http")||url.startsWith("https")){
            return url;
        }
        return "http://192.168.147.164:9000/" + url;
    }
}
