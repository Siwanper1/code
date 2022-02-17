package com.siwanper;

import com.siwanper.addTwoNumber.AddTowNumber_2;
import com.siwanper.addTwoNumber.ListNode;

import java.util.HashSet;

public class test {

    public static void main(String[] args) {
//        System.out.printf(String.valueOf(lengthOfLongestSubstring("abcabcbb")));

        int[] nums1 = {1,3,4,8}, nums2 = {1,2,3,4,5,6,7,8,9};
        System.out.printf(String.valueOf(findMedianSortedArrays(nums1, nums2)));

    }

    private static void addTwoNumber(){
        ListNode node1 = new ListNode(9,new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))));
        ListNode node2 = new ListNode(9,new ListNode(9));
        AddTowNumber_2 addTowNumber2 = new AddTowNumber_2();
        ListNode node = addTowNumber2.addTwoNumbers(node1, node2);
        printNode(node);
    }

    private static void printNode(ListNode node) {
        if (node != null) {
            System.out.printf(String.valueOf(node.val));
            node = node.next;
            printNode(node);
        }
    }

    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * @param s
     * @return 滑动窗口，左右两个指针
     */
    private static int lengthOfLongestSubstring(String s) {
        // 记录不重复的字符串
        HashSet<Character> set = new HashSet<Character>();
        // 右指针在第一个位置
        int r = 0, ans = 0;
        // i 代表左指针
        for (int i = 0; i < s.length(); i++) {
            if (ans >= (s.length() - i)) {
                return ans;
            }
            // 左指针每向右滑动一次，移除第一个元素
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }
            // 右指针向右滑动，如果集合中不包含则加入，如果包含则结束滑动。
            while (r < s.length() && !set.contains(s.charAt(r))) {
                set.add(s.charAt(r));
                r++;
            }
            ans = Math.max(ans,r - i);
        }
        return ans;
    }

    /**
     * 4. 寻找两个正序数组的中位数
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     *
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     */
    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length , length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            // 奇数
            int midIndex = totalLength / 2 + 1;
            return getKElement(nums1, nums2, midIndex);
        } else {
            // 偶数
            int midIndex1 = totalLength / 2; int midIndex2 = totalLength / 2 + 1;
            return (getKElement(nums1, nums2, midIndex1) + getKElement(nums1, nums2, midIndex2)) / 2.0;
        }
    }

    private static int getKElement(int[] nums1, int[] nums2, int k) {
        int length1 = nums1.length , length2 = nums2.length;
        int index1 = 0, index2 = 0;
        while (true) {
            // 如果说查找过程中某一个数组越界了，就将这个数组所有元素排除。返回剩余数组的第k个元素。
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            // 如果k=1，说明已经找到了第k个元素
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            // 二分查找
            int half = k / 2;
            int newIndex1 = Math.min(half + index1, length1) - 1;
            int newIndex2 = Math.min(half + index2, length2) - 1;
            int pivot1 = nums1[newIndex1] ,pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                // k 减去排除的元素个数。
                k -= (newIndex1 - index1 + 1);
                // 较小的数组排除包含newIndex下标的值，并且下标往后移一位。
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }







}
