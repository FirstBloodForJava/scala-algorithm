package com.oycm.datastructure.binary_tree.bfs;

import com.oycm.TreeNode;

import java.util.*;

public class Solution_10 {

    /**
     * 2583. <a href="https://leetcode.cn/problems/kth-largest-sum-in-a-binary-tree/description/">二叉树中的第 K 大层和</a> 1374
     *
     * @param root
     * @param k
     * @return
     */
    public long kthLargestLevelSum(TreeNode root, int k) {
        /*
        bfs 遍历后 + 排序
         */
        List<Long> layers = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            int n = q.size();
            long sum = 0;
            while (n-- > 0) {
                TreeNode node = q.poll();
                sum += node.val;
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            layers.add(sum);
        }
        if (k > layers.size()) return -1;
        Collections.sort(layers);
        return layers.get(layers.size() - k);
    }

}
