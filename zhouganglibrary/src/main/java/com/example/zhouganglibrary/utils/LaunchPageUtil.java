package com.example.zhouganglibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by zhougang on 2018/6/28.
 * description:
 */

public class LaunchPageUtil {
    public static void launch(Class<?> cls,Context context) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }
    public static void launch(Class<?> cls, Bundle bundle,Context context) {
        Intent intent = new Intent(context, cls);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    public static void launch(Class<?> cls, String key, String value,Context context) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(key, value);
        context.startActivity(intent);
    }
}
