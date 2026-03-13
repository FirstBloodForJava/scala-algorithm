package com.oycm.datastructure.binary_tree.bfs;

import com.oycm.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_13 {

    /**
     * 1609. <a href="https://leetcode.cn/problems/even-odd-tree/description/">奇偶树</a> 1438
     * 奇偶树 条件
     * 二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
     * 偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
     * 奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
     *
     * @param root
     * @return
     */
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int layer = 0;

        while (!queue.isEmpty()) {
            int n = queue.size();
            int pre = layer % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            while (n-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                /*if (layer % 2 == 0) {
                    if (node.val % 2 == 1 && node.val > pre) {
                        pre = node.val;
                    } else {
                        return false;
                    }
                } else {
                    if (node.val % 2==0 && node.val < pre) {
                        pre = node.val;
                    } else {
                        return false;
                    }
                }*/
                if (layer % 2 == node.val % 2) {
                    return false;
                }
                if ((layer % 2 == 0 && node.val <= pre) || (layer % 2 == 1 && node.val >= pre)) {
                    return false;
                }
                pre = node.val;
            }

            layer++;
        }

        return true;
    }

}
