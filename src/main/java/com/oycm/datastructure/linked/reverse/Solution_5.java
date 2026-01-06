package com.oycm.datastructure.linked.reverse;

import com.oycm.DataCreateUtils;
import com.oycm.ListNode;

public class Solution_5 {

    /**
     * 2074. <a href="https://leetcode.cn/problems/reverse-nodes-in-even-length-groups/description/">反转偶数长度组的节点</a> 1685
     * <p>
     * 链表中的节点 按顺序 划分成若干 非空 组, 长度构成一个自然数序列（1, 2, 3, 4, ..., 最后一组的长度可能小于或者等于 1 + 倒数第二组的长度
     *
     * @param head
     * @return 反转 每个 偶数 长度组中的节点
     */
    public static ListNode reverseEvenLengthGroups(ListNode head) {
        // todo 可优化
        // 先计算节点数量
        int n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            n++;
        }
        if (n < 3) {
            return head;
        }
        int i = 0;
        ListNode cur = head;
        int cnt = 2;
        boolean flag = false;
        while (cur != null) {
            i++;
            n--;
            if (cur.next != null && (i == cnt - 1 || flag)) {
                if (n < cnt && n % 2 !=0) {
                    break;
                }
                i = 1;
                ListNode h = cur, pre = null, tail = cur.next;
                cur = cur.next;
                while (i <= cnt && n > 0) {
                    ListNode next = cur.next;
                    cur.next = pre;
                    pre = cur;
                    cur = next;
                    i++;
                    n--;
                }
                h.next = pre;
                tail.next = cur;
                // 下一轮偶数 节点数量
                cnt += 2;
                i = 0;
                if (n > 0 && n < cnt && n % 2 == 0) {
                    cur = tail;
                    n++;
                    flag = true;
                }
            } else {
                cur = cur.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        System.out.println(DataCreateUtils.listNodeToString(reverseEvenLengthGroups(DataCreateUtils.buildListNode(new int[]{5, 2, 6, 3, 9, 1, 7, 3, 8, 4}))));
        System.out.println(DataCreateUtils.listNodeToString(reverseEvenLengthGroups(DataCreateUtils.buildListNode(new int[]{1, 1, 0, 6, 5}))));
        System.out.println(DataCreateUtils.listNodeToString(reverseEvenLengthGroups(DataCreateUtils.buildListNode(new int[]{1, 1, 0, 6}))));
        System.out.println(DataCreateUtils.listNodeToString(reverseEvenLengthGroups(DataCreateUtils.buildListNode(new int[]{1, 5, 0, 2, 4, 7, 3, 6}))));
        System.out.println(DataCreateUtils.listNodeToString(reverseEvenLengthGroups(DataCreateUtils.buildListNode(new int[]{0, 3, 4, 1, 5, 2}))));
    }

}
