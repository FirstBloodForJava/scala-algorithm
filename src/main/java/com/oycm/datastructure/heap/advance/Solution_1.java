package com.oycm.datastructure.heap.advance;


import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_1 {

    /**
     * 23. <a href="https://leetcode.cn/problems/merge-k-sorted-lists/description/">合并 K 个升序链表</a>
     *
     * @param lists ListNode 数组中的链表按升序排列
     * @return 将 链表数组合并至一个升序链表 中
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        /*
        暴力的做法是 将 lists 中的元素全部放到 小顶堆中, 遍历堆的过程中同时移除堆顶元素
         */
        if (lists.length == 0) {
            return null;
        }
        PriorityQueue<Integer> min = new PriorityQueue<>();
        for (ListNode n : lists) {
            while (n != null) {
                min.add(n.val);
                n = n.next;
            }
        }
        ListNode node = new ListNode();
        if (!min.isEmpty()) {
            node.val = min.poll();
        } else {
            return null;
        }
        ListNode next = null;
        while (!min.isEmpty()) {
            if (next == null) {
                next = new ListNode(min.poll());
                node.next = next;
            } else {
                next.next = new ListNode(min.poll());
                next = next.next;
            }
        }

        return node;
    }

    public static ListNode answerNodeHeap(ListNode[] lists) {
        /*
        题解: 堆 根据链表的 头节点进行 排序
         */
        PriorityQueue<ListNode> min = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode list : lists) {
            if (list != null) {
                min.add(list);
            }
        }
        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        // 堆不为空
        while (!min.isEmpty()) {
            ListNode head = min.poll();
            if (head.next != null) {
                // 下一个节点不为空, 可能不是最小节点
                min.add(head.next);
            }
            // 添加到 哨兵节点的后面
            curr.next = head;
            // 下一个节点 更新下次使用
            curr = curr.next;
        }

        return dummy.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(4, new ListNode(5));

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3, new ListNode(4));

        ListNode node3 = new ListNode(2, new ListNode(6));

        mergeKLists(new ListNode[]{node1, node2, node3});
    }
}
