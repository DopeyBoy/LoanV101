package com.llkj.creditchecking.utils;

import android.content.Context;

/**
 * <pre>
 *     author:
 *     time  : 2016-12-14
 *     desc  :
 * </pre>
 */
public class Utils {

    public static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }
}
