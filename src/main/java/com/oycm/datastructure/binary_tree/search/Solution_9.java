package com.oycm.datastructure.binary_tree.search;

import com.oycm.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_9 {

    /**
     * 99. <a href="https://leetcode.cn/problems/recover-binary-search-tree/description/">恢复二叉搜索树</a>
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {
        /*
        题解思路: 中序遍历 和前一个 节点值比较大小
         */
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode x = null, y = null, pred = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pred != null && root.val < pred.val) {
                y = root;
                if (x == null) {
                    x = pred;
                } else {
                    break;
                }
            }
            pred = root;
            root = root.right;
        }
        int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }

}
