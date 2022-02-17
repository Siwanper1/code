package com.siwanper;

public class Watch {
    public static long startTime, endTime, startMemory, endMemory;
    public static void start(){
        startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        startMemory = runtime.totalMemory() - runtime.freeMemory();
    }

    public static void end(){
        endTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        endMemory = runtime.totalMemory() - runtime.freeMemory();
        //计算start和end之间的代码执行期间所耗时间(ms)与内存(M)。
        // 1毫秒(ms) = 1000微秒(us) = 1000 000纳秒(ns)
        // 1M = 1*2^20 byte = 1024 * 1024 byte;
        String time = String.valueOf((double)(endTime - startTime)/1000000);
        String memory = String.valueOf((double)(endMemory - startMemory)/1024/1024) ;
        System.out.println("---------------您的代码执行时间为：" + time.substring(0,time.equals("0.0") ? 1 : (time.indexOf(".")+3)) + " ms, 消耗内存：" + memory.substring(0,memory.equals("0.0") ? 1 : (memory.indexOf(".")+3)) + " M + !---------------");

    }
}
