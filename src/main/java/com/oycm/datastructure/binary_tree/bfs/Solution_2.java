package com.oycm.datastructure.binary_tree.bfs;

import com.oycm.TreeNode;

import java.util.*;

public class Solution_2 {

    /**
     * 103. <a href="https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/description/">二叉树的锯齿形层序遍历</a>
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        boolean leftToRight = true;
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
            // 反转
            if (leftToRight) {
                Collections.reverse(layer);
            }
            leftToRight = !leftToRight;
            ans.add(layer);
        }
        return ans;
    }

}
