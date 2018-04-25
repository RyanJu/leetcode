package com.ryan.annotation;

import com.ryan.enums.ThreadMode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/25 15:18 1.0
 * @time 2018/1/25 15:18
 * @project leetcode com.ryan.annotation
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/25 15:18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RunOn {
    ThreadMode threadMode() default ThreadMode.MAIN;
}
