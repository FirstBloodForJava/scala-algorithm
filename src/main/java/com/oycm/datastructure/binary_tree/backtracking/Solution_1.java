package com.oycm.datastructure.binary_tree.backtracking;

import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_1 {

    /**
     * 257. <a href="https://leetcode.cn/problems/binary-tree-paths/description/">二叉树的所有路径</a>
     *
     * @param root
     * @return 所有从根节点到叶子节点的路径
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        dfs(root, new StringBuilder(), ans);
        return ans;
    }

    private void dfs(TreeNode node, StringBuilder path, List<String> ans) {
        if (node == null) return;
        int length = path.length();
        path.append(node.val);
        // 叶子节点
        if (node.left == null && node.right == null) {
            ans.add(path.toString());
        } else {
            path.append("->");
            dfs(node.left, path, ans);
            dfs(node.right, path, ans);
        }
        // 回溯
        path.setLength(length);
    }

}
