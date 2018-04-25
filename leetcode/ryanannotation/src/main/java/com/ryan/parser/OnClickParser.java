package com.ryan.parser;

import android.view.View;

import com.ryan.annotation.OnClick;
import com.ryan.exception.JuggIllegalArgumentException;
import com.ryan.exception.JuggNullPointerException;
import com.ryan.util.JuggChecker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/1/26 9:31 1.0
 * @time 2018/1/26 9:31
 * @project leetcode com.ryan.parser
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/1/26 9:31
 */

public class OnClickParser implements MethodParser {
    @Override
    public boolean parse(final Object target, final Method method) {
        OnClick onClick = method.getAnnotation(OnClick.class);
        int[] values = onClick.value();
        if (JuggChecker.CheckEmpty(values)) {
            throw new JuggNullPointerException("method " + method.getName() + " has no OnClick values");
        }

        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            try {
                Method findViewById = target.getClass().getMethod("findViewById", int.class);
                View view = (View) findViewById.invoke(target, value);
                final Class<?>[] parameterTypes = method.getParameterTypes();
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            if (parameterTypes == null || parameterTypes.length == 0) {
                                method.invoke(target);
                            } else if (parameterTypes.length == 1
                                    && parameterTypes[0] == View.class) {
                                method.invoke(target, v);
                            } else {
                                throw new JuggIllegalArgumentException("onclick method " + method.getName()
                                        + " can't has illegal argument ,argument must be empty or View");
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
