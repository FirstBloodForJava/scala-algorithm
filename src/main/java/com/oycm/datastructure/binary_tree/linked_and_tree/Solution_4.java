package com.oycm.datastructure.binary_tree.linked_and_tree;

import com.oycm.Node;

public class Solution_4 {

    /**
     * 116. <a href="https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/description/">填充每个节点的下一个右侧节点指针</a>
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        /*
        bfs 遍历 可以在一层中执行下一个同层节点
        bdf + 链表
        当前节点 和 下一层要遍历的链表头节点
         */
        Node dummy = new Node();
        Node cur = root;
        while (cur != null) {
            // 下一层
            Node next = dummy;
            // 这里必须设为 null, 否则会在最后一层死循环
            next.next = null;
            while (cur != null) {
                if (cur.left != null) {
                    next.next = cur.left;
                    next = next.next;
                }
                if (cur.right != null) {
                    next.next = cur.right;
                    next = next.next;
                }
                // 当前层的下一个节点
                cur = cur.next;
            }
            // 下一层节点
            cur = dummy.next;
        }

        return root;
    }

    public static void main(String[] args) {
        Node node = new Node(1, new Node(2, new Node(5), new Node(6)), new Node(3, new Node(7), new Node(8)));
        new Solution_4().connect(node);
    }

}
