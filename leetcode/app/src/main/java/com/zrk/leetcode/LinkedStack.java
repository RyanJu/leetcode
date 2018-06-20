package com.zrk.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/5/10 16:26 1.0
 * @time 2018/5/10 16:26
 * @project leetcode com.zrk.leetcode
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/5/10 16:26
 */

public class LinkedStack<T> {
    static {
        System.out.println("load2");
    }
    static {
        System.out.println("load");
    }

    public void name(String... ags){

    }

    public void name(Integer... args){

    }

    private class Node<V> {
        V data;
        Node next;

        public Node(V data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<T> top = new Node<>(null, null);

    public void push(T t) {
        top = new Node<>(t, top);
    }

    public T pop() {
        if (top.next == null) {
            return null;
        }
        T data = top.data;
        top = top.next;
        return data;
    }

    public boolean empty() {
        return top.next == null;
    }

    public static void main(String[] args) {
//        LinkedStack<Integer> stack = new LinkedStack<>();
//        for (int i = 0; i < 10; i++) {
//            stack.push(i);
//        }
//
//        while (!stack.empty()) {
//            System.out.println("stack : " + stack.pop());
//        }
//        System.out.println(stack.pop());
//
//        for (int i = 0; i < 5; i++) {
//            stack.push(i);
//        }
//        while (!stack.empty()) {
//            System.out.println("stack : " + stack.pop());
//        }
//
//        getClass("1", 1, 1.0);

    }

    public static <T, V, F> void getClass(T t, V v, F f) {
        System.out.println(t.getClass());
        System.out.println(v.getClass());
        System.out.println(f.getClass());
    }


    public static class GeneClass {
        private static long count = 0;
        private final long i = count++;

        private void f(){}

        @Override
        public String toString() {
            return "GeneClass{" +
                    "i=" + i +
                    '}';
        }
    }

}
