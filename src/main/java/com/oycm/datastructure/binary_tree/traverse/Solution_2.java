package com.oycm.datastructure.binary_tree.traverse;


import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution_2 {

    /**
     * 94. <a href="https://leetcode.cn/problems/binary-tree-inorder-traversal/description/">二叉树的中序遍历</a>
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    public void dfs(TreeNode node, List<Integer> list) {
        if (node != null) {
            dfs(node.left, list);
            list.add(node.val);
            dfs(node.right, list);
        }

    }

    public List<Integer> inorderTraversalIre(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        // 左 -> 根 -> 右
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }
        return list;
    }

}
