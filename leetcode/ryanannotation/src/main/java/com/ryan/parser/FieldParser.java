package com.ryan.parser;

import java.lang.reflect.Field;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/25 17:37 1.0
 * @time 2018/1/25 17:37
 * @project leetcode com.ryan.parser
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/25 17:37
 */

public interface FieldParser extends Parser {
    boolean parse(Object target, Field field);
}
