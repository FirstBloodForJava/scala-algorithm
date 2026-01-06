package com.oycm.datastructure.linked.reverse;


import com.oycm.DataCreateUtils;
import com.oycm.ListNode;

public class Solution_4 {

    /**
     * 25. K 个一组翻转链表
     *
     * @param head
     * @param k
     * @return 每 k 个节点进行翻转后的结果, 剩余数量少于 k, 剩余节点顺序不变
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        // 一个节点翻转还是自己
        if (k == 1) {
            return head;
        }
        // 先计算 k 的总数
        int n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            n++;
        }
        // 需要翻转的次数
        int cnt = n / k;
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while (cnt > 0) {
            int c = 0;
            ListNode pre = null, inner = cur.next, h = cur, tail = inner;
            while (inner != null && c < k) {
                ListNode next = inner.next;
                inner.next = pre;
                pre = inner;
                inner = next;
                c++;
            }
            // 下一个指向反转后的头
            h.next = pre;
            // 尾部节点
            tail.next = inner;
            // 更新下一轮
            cur = tail;
            cnt--;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println(DataCreateUtils.listNodeToString(reverseKGroup(DataCreateUtils.buildListNode(new int[]{1, 2, 3, 4, 5}), 2)));
    }

}
