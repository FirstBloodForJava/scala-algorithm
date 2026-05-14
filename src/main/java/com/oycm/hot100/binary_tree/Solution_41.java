package com.oycm.hot100.binary_tree;

import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_41 {

    /**
     * 102. <a href="https://leetcode.cn/problems/binary-tree-level-order-traversal/description/">二叉树的层序遍历</a>
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        /*
        给你二叉树的根节点 root ，返回其节点值的 层序遍历。
        （即逐层地，从左到右访问所有节点）。
         */
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        List<TreeNode> q = new ArrayList<>();
        q.add(root);
        while (!q.isEmpty()) {
            List<Integer> path = new ArrayList<>();
            List<TreeNode> next = new ArrayList<>();
            for (TreeNode node : q) {
                path.add(node.val);
                if (node.left != null) next.add(node.left);
                if (node.right != null) next.add(node.right);
            }
            q = next;
            ans.add(path);
        }

        return ans;
    }

}
