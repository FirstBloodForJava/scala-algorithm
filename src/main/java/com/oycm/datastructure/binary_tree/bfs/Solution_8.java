package com.oycm.datastructure.binary_tree.bfs;

import com.oycm.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_8 {

    /**
     * 1161. <a href="https://leetcode.cn/problems/maximum-level-sum-of-a-binary-tree/description/">最大层内元素和</a> 1250
     * <p>
     * 二叉树层号从 1 开始
     *
     * @param root
     * @return 返回总和 最大 的那一层的层号 x。如果有多层的总和一样大，返回其中 最小 的层号 x
     */
    public int maxLevelSum(TreeNode root) {
        /*
        bfs
         */
        int ans = 0;
        long max = Integer.MIN_VALUE;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int idx = 1;
        while (!queue.isEmpty()) {
            long sum = 0;
            int n = queue.size();
            while (n-- > 0) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            if (sum > max) {
                max = sum;
                ans = idx;
            }
            idx++;
        }

        return ans;
    }

}
