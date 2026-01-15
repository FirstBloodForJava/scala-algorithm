package com.oycm.datastructure.linked.other;

import com.oycm.Node;

import java.util.HashMap;
import java.util.Map;

public class Solution_1 {

    /**
     * 138. <a href="https://leetcode.cn/problems/copy-list-with-random-pointer/description/">随机链表的复制</a>
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Integer> nodeMap = new HashMap<>();
        Map<Integer,Node> intMap = new HashMap<>();
        int i = 0;
        for (Node cur = head; cur != null; cur = cur.next) {
            nodeMap.put(cur, i);
            intMap.put(i, new Node(cur.val));
            i++;
        }


        Node dummy = new Node(0), newCur = dummy;
        i = 0;
        for (Node cur = head; cur != null; cur = cur.next) {
            Node node = intMap.get(i);
            if (cur.random != null) {
                node.random = intMap.get(nodeMap.get(cur.random));
            }
            newCur.next = node;
            newCur = newCur.next;
            i++;
        }

        return dummy.next;
    }

    public Node noneHash(Node head) {
        /*
        题解思路: 交错链表
        1 -> 2 -> 3 => 1 -> 1' -> 2 -> 2' -> 3 -> 3' 这样 1 的 random 节点如果指向 3, 则 1' 的 random 指向 1.random.next
         */
        if (head == null) {
            return null;
        }
        // 新的交错链表
        for (Node cur = head; cur != null; cur = cur.next.next) {
            Node temp = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = temp;
        }
        // 遍历交错链表
        for (Node cur = head; cur != null; cur = cur.next.next) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
        }
        // 分离链表 1 -> 1' -> 2 -> 2' -> 3 -> 3'
        // 1 -> 2 -> 3; 1' -> 2' -> 3'
        Node copyHead = head.next;
        Node cur = head;
        // 至少四个节点才拆分 1 -> 1' -> 2 -> 2'
        for (; cur.next.next != null; cur = cur.next) {
            // 新的节点
            Node copy = cur.next;
            cur.next = copy.next;
            copy.next = copy.next.next;
        }
        cur.next = null;

        return copyHead;
    }

}
