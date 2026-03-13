package com.oycm.datastructure.binary_tree.bfs;

import com.oycm.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_14 {

    /**
     * 623. <a href="https://leetcode.cn/problems/add-one-row-to-tree/description/">在二叉树中增加一行</a>
     *
     * @param root
     * @param val
     * @param depth
     * @return
     */
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        /*
        bfs 遍历
         */
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int curDepth = 1;
        while (!queue.isEmpty()) {
            curDepth++;
            int n = queue.size();
            while (n-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (curDepth == depth) {
                    node.left = new TreeNode(val, node.left, null);
                    node.right = new TreeNode(val, null, node.right);
                }
            }
            if (curDepth == depth) {
                break;
            }

        }

        return root;
    }

}
