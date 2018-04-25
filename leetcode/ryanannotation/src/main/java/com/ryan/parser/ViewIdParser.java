package com.ryan.parser;

import com.ryan.annotation.ViewId;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/25 17:39 1.0
 * @time 2018/1/25 17:39
 * @project leetcode com.ryan.parser
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/25 17:39
 */

public class ViewIdParser implements FieldParser {
    @Override
    public boolean parse(Object target, Field field) {
        try {
            field.setAccessible(true);
            ViewId viewId = field.getAnnotation(ViewId.class);
            Method findViewById = target.getClass().getMethod("findViewById", int.class);
            Object view = findViewById.invoke(target, viewId.value());
            field.set(target, view);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }
}
