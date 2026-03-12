package com.oycm.datastructure.binary_tree.bfs;

import com.oycm.TreeNode;

import java.util.*;

public class Solution_3 {

    /**
     * 107. <a href="https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/description/">二叉树的层序遍历 II</a>
     * <p>
     * 自底向上的层序遍历 结果
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> layer = new ArrayList<>(n);
            while (n-- > 0) {
                TreeNode node = queue.poll();
                layer.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ans.add(layer);
        }
        Collections.reverse(ans);
        return ans;
    }

}
