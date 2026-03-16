package com.oycm.datastructure.binary_tree.bfs;

import com.oycm.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_11 {

    /**
     * 1302. <a href="https://leetcode.cn/problems/deepest-leaves-sum/description/">层数最深叶子节点的和</a> 1388
     *
     * @param root
     * @return 层数最深的叶子节点的和
     */
    public int deepestLeavesSum(TreeNode root) {
        /*
        bfs 最后一层就是答案
         */
        int ans = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int sum = 0;
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
            ans = sum;
        }

        return ans;
    }

    static class Solution_11_1 {
        public int deepestLeavesSum(TreeNode root) {
            /*
            dfs 传递深度, 用一个遍历记录最大深度
             */
            return sum;
        }

        private int maxDepth = -1;
        private int sum = 0;

        private void dfs(TreeNode node, int depth) {
            if (node == null) return;
            if (depth > maxDepth) {
                maxDepth = depth;
                sum = node.val;
            } else if (depth == maxDepth){
                // 高度相等才累加
                sum += node.val;
            }
            dfs(node.left, depth + 1);
            dfs(node.right, depth + 1);
        }
    }

}
