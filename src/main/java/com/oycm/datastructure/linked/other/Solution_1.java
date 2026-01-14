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

}
