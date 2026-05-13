package com.oycm.hot100.linked;

import com.oycm.Node;


public class Solution_32 {

    /**
     * 138. <a href="https://leetcode.cn/problems/copy-list-with-random-pointer/description/">随机链表的复制</a>
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        /*
        给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
        构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
        新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态（不能修改原链表指针）。
        复制链表中的指针都不应指向原链表中的节点 。
        如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
         */
        /*
        遍历 head 链表的同时记录并创建一个新的链表，
            同时用 hash 表记录旧链表节点对应的下标；
            用 hash 表记录下标对应新链表的节点
         */
        /*
        遍历 1 -> 2 -> 3 链表的过程中，插入一个当前节点值的新链表（赋值） 得到 1 -> 1' -> 2 -> 2' -> 3 -> 3'
        遍历新链表，给随机连接新节点赋值，一次走两步
        分离链表
         */
        for (Node cur = head; cur != null; cur = cur.next.next) {
            Node copy = new Node(cur.val);
            copy.next = cur.next;
            cur.next = copy;
        }
        // 随机链表新节点为 交错链表的下一个节点
        for (Node cur = head; cur != null; cur = cur.next.next) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
        }
        Node dummy = new Node(0);
        for (Node cur = head, tail = dummy; cur != null; cur = cur.next, tail = tail.next) {
            Node copy = cur.next;
            // 分离恢复
            cur.next = copy.next;
            // 新链表末尾插入
            tail.next = copy;
        }

        return dummy.next;
    }

}
