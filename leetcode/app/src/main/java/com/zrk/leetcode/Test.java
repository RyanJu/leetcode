package com.zrk.leetcode;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/4/13 14:12 1.0
 * @time 2018/4/13 14:12
 * @project leetcode com.zrk.leetcode
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/4/13 14:12
 */

public class Test {
    static class Art {
        Art() {
            System.out.println("art constructor ");
        }

        Art(int i) {
            System.out.println("art constructor " + i);
        }
    }

    static class Painting extends Art {
        Painting() {
            System.out.println("painting constructor ");
        }
        private void paint(){
            System.out.println("Painting.paint");
        }
    }

    static class Cartoon extends Painting {
        Cartoon(int c) {
            System.out.println("Cartoon constructor " + c);
        }
        public void paint(){
            System.out.println("Cartoon.paint");
        }
    }


    public static class Parent {
        int age;
        String name;

        @Override
        public String toString() {
            return "Parent{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        protected Object clone() {
            Parent copy = new Parent();
            copy.age = this.age;
            copy.name = this.name;
            return copy;
        }
    }

    public static class Son extends Parent {
        public class Inner{
        }
    }

    static class InnerHerit extends Son.Inner{
        InnerHerit(Son son){
            son.super();
        }
    }



    static class A{
        A(){
            System.out.println("A constructor");
            draw();
            System.out.println("A constructor end");
        }
        void draw(){
            System.out.println("draw A");
        }

        Object process() throws IOException{
            return new Object();
        }

    }

    static class B extends A{
        Object object = new Object();
        B(){
            System.out.println("B constructor");
            draw();
            System.out.println("B constructor end");
        }

        void draw(){
            System.out.println("draw B "+object);
        }

        String process(){
            return "string";
        }
    }



    public static void main(String[] args) {
        B a = new B();
        System.out.println(a.process());
    }

    private static Object invokeNewInstance(String classPath) {
        try {
            Class clz = Class.forName(classPath);
            Constructor constructor = clz.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object o = constructor.newInstance();
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
