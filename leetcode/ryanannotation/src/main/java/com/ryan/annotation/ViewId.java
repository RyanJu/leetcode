package com.ryan.annotation;

import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/25 17:38 1.0
 * @time 2018/1/25 17:38
 * @project leetcode com.ryan.annotation
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/25 17:38
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewId {
    @IdRes int value();
}
