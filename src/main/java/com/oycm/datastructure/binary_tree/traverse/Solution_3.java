package com.oycm.datastructure.binary_tree.traverse;


import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution_3 {

    /**
     * 145. <a href="https://leetcode.cn/problems/binary-tree-postorder-traversal/description/">二叉树的后序遍历</a>
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    public void dfs(TreeNode node, List<Integer> list) {
        if (node != null) {
            dfs(node.left, list);
            dfs(node.right, list);
            list.add(node.val);
        }

    }

    public List<Integer> postorderTraversalIte(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root, prev = null;
        //
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            // node.right == null 表示叶子节点, 可记录答案
            // root.right == prev 表示当前时 右子节点的遍历
            // 保证 左 -> 右 -> 根节点顺序
            node = stack.pop();
            if (node.right == null || node.right == prev) {
                list.add(node.val);
                prev = node;
                node = null;
            } else {
                stack.push(node);
                node = node.right;
            }
        }

        return list;
    }

}
