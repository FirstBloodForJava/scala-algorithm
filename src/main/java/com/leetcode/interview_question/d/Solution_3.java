package com.leetcode.interview_question.d;

import com.oycm.ListNode;
import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_3 {

    /**
     * 面试题 04.03. <a href="https://leetcode.cn/problems/list-of-depth-lcci/">特定深度节点链表</a>
     *
     * @param tree
     * @return
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        /*
        给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。
        返回一个包含所有深度的链表的数组。
         */
        /*
        二叉树 bfs（层序遍历）
         */
        if (tree == null) return new ListNode[0];
        List<ListNode> list = new ArrayList<>();
        List<TreeNode> q = new ArrayList<>();
        q.add(tree);
        while (!q.isEmpty()) {
            List<TreeNode> p = q;
            q = new ArrayList<>();
            // 哨兵节点
            ListNode dummy = new ListNode(), cur = dummy;
            for (TreeNode node : p) {
                cur.next = new ListNode(node.val);
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
                cur = cur.next;
            }
            list.add(dummy.next);
        }

        return list.toArray(new ListNode[0]);
    }

}
