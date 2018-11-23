package com.morris.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class StringOOM {

    static String base = "string";

    public static void main(String[] args) throws IllegalAccessException {

        Field field =Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);

        Unsafe unsafe = (Unsafe) field.get(null);

        //while(true) {
        unsafe.allocateMemory(1024*1024);
        unsafe.allocateMemory(1024*1024);

        List<String> list = new ArrayList<String>();

        for (int i = 0; i < Integer.MAX_VALUE; i++) {

            String str = base + base;

            base = str;

            list.add(str.intern());

        }

    }

}
