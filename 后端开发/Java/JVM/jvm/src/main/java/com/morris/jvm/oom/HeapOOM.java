package com.morris.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args: -Xms20m -Xmx20m -Xmn10m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/dump/ -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 */
public class HeapOOM {

    public static void main(String[] args) {

        List<byte[]> list = new ArrayList<byte[]>();

        int i = 0;

        boolean flag = true;

        while (flag) {

            try {
                i++;
                list.add(new byte[1024 * 1024]);//每次增加一个1M大小的数组对象
            } catch (Throwable e) {
                e.printStackTrace();
                flag = false;
                System.out.println("count=" + i);//记录运行的次数
            }

        }

    }

}
