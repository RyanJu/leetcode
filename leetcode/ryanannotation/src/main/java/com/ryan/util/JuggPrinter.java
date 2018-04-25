package com.ryan.util;

import android.util.Log;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/25 16:02 1.0
 * @time 2018/1/25 16:02
 * @project leetcode com.ryan.util
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/25 16:02
 */

public class JuggPrinter {
    public static final boolean DEBUG = true;
    private static final String TAG = "Jugg";

    public static void print(String text) {
        print(TAG, text);
    }

    public static void print(String tag, String text) {
        if (DEBUG)
            Log.i(tag, "" + text);
    }


}
