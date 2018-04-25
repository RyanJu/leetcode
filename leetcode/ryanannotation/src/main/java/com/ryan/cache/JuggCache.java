package com.ryan.cache;

import android.support.v4.util.ArrayMap;

import com.ryan.parser.ViewIdParser;
import com.ryan.thread.BackgroundThreadService;
import com.ryan.thread.MainThreadService;
import com.ryan.thread.MultiThreadService;
import com.ryan.thread.ThreadService;

import java.lang.ref.WeakReference;
import java.util.Map;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/25 17:11 1.0
 * @time 2018/1/25 17:11
 * @project leetcode com.ryan.cache
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/25 17:11
 */

public class JuggCache {
    private ThreadService mMainThread;
    private ThreadService mBackgroundThread;
    private ThreadService mMultiThread;

    private Map<String, WeakReference<Object>> sCache = new ArrayMap<>();

    private JuggCache() {
    }

    public static JuggCache get() {
        return Holder.SINGLETON;
    }

    public ViewIdParser getViewIdParser() {
        try {
            return (ViewIdParser) find(ViewIdParser.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object find(Class<?> aClass) throws IllegalAccessException, InstantiationException {
        String key = ViewIdParser.class.getName();
        if (sCache.containsKey(key)) {
            WeakReference<Object> valueRef = sCache.get(key);
            if (valueRef != null && valueRef.get() != null) {
                Object obj = valueRef.get();
                if (obj != null && obj.getClass() == aClass) {
                    return obj;
                }
            }
        }
        Object obj = aClass.newInstance();
        sCache.put(aClass.getName(), new WeakReference<>(obj));
        return obj;
    }

    private static class Holder {
        static JuggCache SINGLETON = new JuggCache();
    }

    public ThreadService getMainThread() {
        return mMainThread == null ? mMainThread = new MainThreadService() : mMainThread;
    }

    public ThreadService getBackgroundThread() {
        return mBackgroundThread == null ? mBackgroundThread = new BackgroundThreadService() : mBackgroundThread;
    }

    public ThreadService getMultiThread() {
        return mMultiThread == null ? mMultiThread = new MultiThreadService() : mMultiThread;
    }
}
