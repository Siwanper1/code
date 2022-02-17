package com.siwanper.basic;

import com.siwanper.Watch;

import java.util.Arrays;
import java.util.Scanner;

public class sort {

    public static void main(String[] args) {
//        Watch.start();
//        int[] nums = {1, 2, 244, 123, 211, 7, 9, 5, 8, 4, 3, 12, 6, 21, 45, 11, 22, 31, 14, 67, 122};
//        int[] nums = {3,4,6,1,2,5};
//        bubbleSort(nums); // 0.69ms
//        selectSort(nums); // 0.50
//        insertSort(nums);
//        shellSort(nums);
//        mergeSort(nums, 0, nums.length - 1);
//        quickSort(nums, 0, nums.length -1);
//        System.out.println(Arrays.toString(nums));
//        Watch.end();

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        quickSort(nums, 0, n - 1);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 1、冒泡排序 升序排序
      */
    private static void bubbleSort(int[] nums){
        boolean hasChange = true;
        for (int i = 0, n = nums.length; i < n - 1 && hasChange; i++) {
            hasChange = false;
            // 冒泡开始，如果这次冒泡结束，没有变化，说明已经排序完成，直接退出
            for (int j = 0; j < n - i - 1; j++) {
                // 对比相邻的两个值，如果大于则交换位置
                if (nums[j] < nums[j + 1]) {
                    hasChange = true;
                    swap(nums, j, j + 1);
                }
            }
        }
    }
    // 交换两个值
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 2、选择排序
     * 每次将最小（大）的值，放到前面
     */
    private static void selectSort(int[] nums) {
        int n = nums.length;
        int mid, temp;
        for (int i = 0; i < n; i++) {
            mid = i;
            for (int j = i + 1; j < n; j++) {
                // 每次循环找到最小值的索引
                if (nums[j] < nums[mid]) {
                    mid = j;
                }
            }
            // 将最小值与本次循环的第一个值交换位置
            swap(nums, i, mid);
        }
    }

    /**
     * 3、插入排序
     *  对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入
     */
    private static void insertSort(int[] nums) {
        int n = nums.length;
        int pre, cur;
        for (int i = 1; i < n; i++) {
            cur = nums[i];
            pre = i - 1;
            while (pre >= 0 && cur < nums[pre]) {
                nums[pre + 1] = nums[pre];
                pre--;
            }
            nums[pre + 1] = cur;
        }
    }

    /**
     * 4、希尔排序
     * 将数组分隔为gap个序列，分别对其进行插入排序.分组规则为，每隔gap个，去一个元素。
     * 每次循环结束，gap减半，知道gap = 1
     * @param nums
     */
    private static void shellSort(int[] nums) {
        int length = nums.length;
        int current;
        // 对数组进行分组，每次分组数量减半
        for (int gap = (int) Math.floor(length / 2); gap > 0; gap = (int) Math.floor(gap / 2)) {
            // 将分组后的序列进行插入排序
            for (int i = gap; i < length; i++) {
                int j = i;
                current = nums[i];
                while ((j - gap) >= 0 && current < nums[j - gap]) {
                    nums[j] = nums[j - gap];
                    j = j - gap;
                }
                nums[j] = current;
            }
        }
    }

    /**
     * 5、归并排序
     * 归并排序的核心思想是分治，把一个复杂问题拆分成若干个子问题来求解。
     * 归并排序的算法思想是：把数组从中间划分为两个子数组，一直递归地把子数组划分成更小的数组，直到子数组里面只有一个元素的时候开始排序。排序的方法就是按照大小顺序合并两个元素。接着依次按照递归的顺序返回，不断合并排好序的数组，直到把整个数组排好序。
     */
    private static int[] tmp = new int[100010];
    public static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        // 将数组递归为大小为1的小数组，然后再进行对比合并
        int mid = (left + right) >>> 1;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        // 对比两个小数组所有元素的大小，排序后进行合并
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }
        // 如果小数组对比完后有剩余元素，直接添加到临时数组中
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        // 合并后的数组
        for (i = left, j = 0; i <= right; i++, j++) {
            nums[i] = tmp[j];
        }
    }

    /**
     * 6、快速排序
     * 快速排序也采用了分治的思想：把原始的数组筛选成较小和较大的两个子数组，然后递归地排序两个子数组。
     * @param nums
     */
    private static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left - 1, j = right + 1;
        int x = nums[left];
        while (i < j) {
            while (nums[++i] < x);
            while (nums[--j] > x);
            if (i < j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }

        quickSort(nums, left, j);
        quickSort(nums, j + 1, right);

    }


}
