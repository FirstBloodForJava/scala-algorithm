package com.oycm.datastructure.binary_tree.bfs;

import com.oycm.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution_6 {

    /**
     * 515. <a href="https://leetcode.cn/problems/find-largest-value-in-each-tree-row/description/">在每个树行中找最大值</a>
     *
     * @param root
     * @return 找出每层二叉树中的最大值
     */
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            int max = Integer.MIN_VALUE;
            while (n-- > 0) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ans.add(max);
        }

        return ans;
    }

}
