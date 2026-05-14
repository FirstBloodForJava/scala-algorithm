package com.oycm.hot100.binary_tree;

import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_36 {

    /**
     * 94. <a href="https://leetcode.cn/problems/binary-tree-inorder-traversal/description/">二叉树的中序遍历</a>
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        /*
        中序遍历：左-根-右，先访问根的左子树，再获取根节点值，最后访问根的右子树。
         */
        List<Integer> path = new ArrayList<>();
        dfs(root, path);
        return path;
    }

    public void dfs(TreeNode node, List<Integer> path) {
        if (node == null) return;
        dfs(node.left, path);
        path.add(node.val);
        dfs(node.right, path);
    }

}
