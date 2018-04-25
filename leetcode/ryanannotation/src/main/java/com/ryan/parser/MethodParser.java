package com.ryan.parser;
import java.lang.reflect.Method;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/25 15:32 1.0
 * @time 2018/1/25 15:32
 * @project leetcode com.ryan.core
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/25 15:32
 */

public interface MethodParser extends Parser{
    boolean parse(Object target,Method method);
}
