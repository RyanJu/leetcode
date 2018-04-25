package com.ryan.core;

import android.support.v4.util.ArrayMap;

import com.ryan.annotation.OnClick;
import com.ryan.annotation.RunOn;
import com.ryan.annotation.ViewId;
import com.ryan.cache.JuggCache;
import com.ryan.exception.JuggNullPointerException;
import com.ryan.parser.MethodParser;
import com.ryan.parser.OnClickParser;
import com.ryan.parser.Parser;
import com.ryan.parser.RunOnParser;
import com.ryan.util.JuggChecker;
import com.ryan.util.JuggPrinter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/25 15:22 1.0
 * @time 2018/1/25 15:22
 * @project leetcode com.ryan.core
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/25 15:22
 */

public class Jugg {

    private static Map<String, Parser> sParserPool;

    public static void inject(Object target) {
        if (target == null) {
            throw new JuggNullPointerException("inject with null object!");
        }
        parseFields(target);
        parseMethod(target);
    }

    private static void parseFields(Object target) {
        if (target == null)
            return;
        Field[] fields = target.getClass().getFields();
        if (JuggChecker.CheckEmpty(fields)) {
            JuggPrinter.print("object has no fields");
            return;
        }
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            if (field.getAnnotation(ViewId.class) != null) {
                JuggCache.get().getViewIdParser().parse(target,field);
            }
        }
    }

    private static void parseMethod(Object target) {
        if (target == null)
            return;
        Method[] methods = target.getClass().getMethods();
        if (JuggChecker.CheckEmpty(methods)) {
            JuggPrinter.print("class has no methods");
            return;
        }

        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            method.setAccessible(true);
            if (method.getAnnotation(OnClick.class) != null) {
                getMethodParser(OnClickParser.class).parse(target, method);
            }
        }

    }

    private static MethodParser getMethodParser(Class clazz) {
        if (clazz == null) {
            throw new JuggNullPointerException("getMethodParser parameter clazz is null!");
        }

        Map<String, Parser> parserPool = getParserPool();
        MethodParser mp = (MethodParser) parserPool.get(clazz.getName());
        if (mp == null) {
            try {
                mp = (MethodParser) clazz.newInstance();
                parserPool.put(clazz.getName(), mp);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return mp;
    }


    private Jugg() {
    }

    public static Map<String, Parser> getParserPool() {
        return sParserPool == null ? sParserPool = new ArrayMap<>() : sParserPool;
    }
}
