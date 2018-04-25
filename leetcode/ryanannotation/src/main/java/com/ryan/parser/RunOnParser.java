package com.ryan.parser;

import com.ryan.annotation.RunOn;
import com.ryan.cache.JuggCache;
import com.ryan.enums.ThreadMode;

import java.lang.reflect.Method;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/25 16:38 1.0
 * @time 2018/1/25 16:38
 * @project leetcode com.ryan.parser
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/25 16:38
 */

public class RunOnParser implements MethodParser {

    @Override
    public boolean parse(final Object target, final Method method) {
        if (target != null && method != null) {
            method.setAccessible(true);
            RunOn annotation = method.getAnnotation(RunOn.class);
            ThreadMode threadMode = annotation.threadMode();

            switch (threadMode) {
                case MAIN:
                    break;
                case BACKGROUND:
                    break;
                case NEW_THREAD:
                    break;
            }
        }
        return true;
    }
}
