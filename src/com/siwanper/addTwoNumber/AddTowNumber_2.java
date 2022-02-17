package com.siwanper.addTwoNumber;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddTowNumber_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return add(0, l1, l2);
    }

    private ListNode add(int node ,ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null && node == 0) {
            return null;
        }
        int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + node;
        int remainder = sum % 10;
        int divider = sum / 10;
        ListNode result = new ListNode(remainder);
        result.next = add(divider, l1 != null ? l1.next : null, l2 != null ? l2.next : null);
        return result;
    }



}
