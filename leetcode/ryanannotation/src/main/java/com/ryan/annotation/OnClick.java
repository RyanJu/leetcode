package com.ryan.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/25 18:22 1.0
 * @time 2018/1/25 18:22
 * @project leetcode com.ryan.annotation
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/25 18:22
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnClick {
    int[] value() default {View.NO_ID};
}
