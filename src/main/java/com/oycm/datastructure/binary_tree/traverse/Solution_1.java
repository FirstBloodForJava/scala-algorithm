package com.oycm.datastructure.binary_tree.traverse;


import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution_1 {

    /**
     * 144. <a href="https://leetcode.cn/problems/binary-tree-preorder-traversal/description/">二叉树的前序遍历</a>
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    public void dfs(TreeNode node, List<Integer> list) {
        if (node != null) {
            list.add(node.val);
            dfs(node.left, list);
            dfs(node.right, list);
        }

    }

    public List<Integer> preorderTraversalIte(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                list.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }

        return list;
    }



}
