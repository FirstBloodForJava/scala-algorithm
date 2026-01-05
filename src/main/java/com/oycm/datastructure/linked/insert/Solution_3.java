package com.oycm.datastructure.linked.insert;


import com.oycm.ListNode;

public class Solution_3 {

    /**
     * LCR 029. <a href="https://leetcode.cn/problems/4ueAj6/description/">循环有序列表的插入</a>
     * 循环单调非递减列表 l1 <= l2, 向这个列表中插入一个新元素 insertVal, 使这个列表仍然是循环升序的。
     * @param head
     * @param insertVal
     * @return
     */
    public ListNode insert(ListNode head, int insertVal) {
        if (head == null) {
            head = new ListNode(insertVal);
            head.next = head;
            return head;
        }
        // 自己是循环节点
        if (head == head.next) {
            head.next = new ListNode(insertVal, head);
            return head;
        }
        // 这里只考虑了 insertVal 链表中某个节点的情况, 如果 cur 所有值都大于 insertVal, insertVal 应该插入最最小值 的前面, 最大值的后面
        ListNode cur = head;
        int min = head.val, max = head.val;
        while (cur.next != head) {
            min = Math.min(min, cur.next.val);
            max = Math.max(max, cur.next.val);
            cur = cur.next;
        }
        if (min == max) {
            // 插入任意位置
            ListNode next = head.next;
            head.next = new ListNode(insertVal, next);
        } else if (insertVal >= min && insertVal <= max) {
            while (true) {
                if (cur.val <= insertVal && cur.next.val >= insertVal) {
                    // 在cur 后面插入 insertVal
                    ListNode next = cur.next;
                    cur.next = new ListNode(insertVal, next);
                    break;
                }
                cur = cur.next;
            }
        } else {
            while (true) {
                if (cur.val == max && cur.next.val != max) {
                    // 在cur 后面插入 insertVal
                    ListNode next = cur.next;
                    cur.next = new ListNode(insertVal, next);
                    break;
                }
                cur = cur.next;
            }
        }

        return head;
    }


    public ListNode answer(ListNode head, int insertVal) {
        ListNode node = new ListNode(insertVal);
        if (head == null) {
            node.next = node;
            return node;
        }
        if (head.next == head) {
            head.next = node;
            node.next = head;
            return head;
        }
        ListNode cur = head, next = cur.next;
        while (head != next) {
            // 处于中间
            if (insertVal >= cur.val && insertVal <= next.val) {
                break;
            }
            // 大于最大值 || 小于最小值
            if (cur.val > next.val) {
                // 大于最大值, 小于最小值
                if (insertVal > cur.val || insertVal < next.val) {
                    break;
                }
            }
            cur = cur.next;
            next = next.next;
        }
        // 退出循环, 所有元素都相等
        cur.next = node;
        node.next = next;
        return head;
    }

}
