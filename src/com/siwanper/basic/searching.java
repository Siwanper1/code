package com.siwanper.basic;

import com.siwanper.Watch;

public class searching {

    public static void main(String[] arg) {
        Watch.start();
        int[] data = {1,4,6,7,8,13,15,16,19,21,25,26,27,29,28,29,38,39,59,56,69,68,70,156,569,699,899};
        int value = 59;
        int i = binarySearch(data, value);
        System.out.println(i);
        Watch.end();
    }
    // 二分查找
    public static int binarySearch(int[] data, int value) {
        // 定义首位指针
        int low = 0;
        int height = data.length - 1;
        while (low <= height) {
            int mid = (low + height) / 2;
            if (data[mid] == value) {
                return mid;
            } else if (data[mid] > value) {
                height = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

}
