package com.oycm.datastructure.linked.integrate;


import com.oycm.ListNode;

public class Solution_3 {

    /**
     * 707. <a href="https://leetcode.cn/problems/design-linked-list/description/">设计链表</a>
     * 单链表, 使用哨兵节点
     */
    static class MyLinkedList {
        int size = 0;
        ListNode head = new ListNode(0);

        public MyLinkedList() {

        }

        /**
         * @param index
         * @return 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1
         */
        public int get(int index) {
            ListNode node = node(index);
            if (node == null) {
                return -1;
            }
            return node.next.val;
        }

        /**
         * 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
         *
         * @param val
         */
        public void addAtHead(int val) {
            ListNode next = head.next;
            head.next = new ListNode(val, next);
            size++;
        }

        /**
         * 将一个值为 val 的节点追加到链表中作为链表的最后一个元素
         *
         * @param val
         */
        public void addAtTail(int val) {
            ListNode cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new ListNode(val);
            size++;
        }

        /**
         * 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。如果 index 比长度更大，该节点将 不会插入 到链表中。
         *
         * @param index
         * @param val
         */
        public void addAtIndex(int index, int val) {
            if (index == size) {
                addAtTail(val);
            } else {
                ListNode node = node(index);
                if (node != null) {
                    ListNode next = node.next;
                    node.next = new ListNode(val, next);
                    size++;
                }
            }

        }

        /**
         * 如果下标有效，则删除链表中下标为 index 的节点
         *
         * @param index
         */
        public void deleteAtIndex(int index) {
            // 要删除头节点, 使用哨兵节点
            ListNode node = node(index);
            if (node != null) {
                node.next = node.next.next;
                size--;
            }
        }

        private ListNode node(int index) {
            if (index >= size) {
                return null;
            }
            ListNode cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur;
        }
    }

}
