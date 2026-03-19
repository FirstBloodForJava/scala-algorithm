package com.oycm.datastructure.binary_tree.other;

import com.oycm.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class Solution_1 {

    /**
     * 1261. <a href="https://leetcode.cn/problems/find-elements-in-a-contaminated-binary-tree/description/">在受污染的二叉树中查找元素</a> 1440
     */
    class FindElements {

        /*
        root.val == 0
        对于任意 treeNode:
            如果 treeNode.val 为 x 且 treeNode.left != null，那么 treeNode.left.val == 2 * x + 1
            如果 treeNode.val 为 x 且 treeNode.right != null，那么 treeNode.right.val == 2 * x + 2
         */
        public FindElements(TreeNode root) {
            root.val = 0;
            dfs(root);
        }
        TreeNode root;
        Set<Integer> set = new HashSet<>();

        public boolean find(int target) {
            /*
            题解思路 把所有值加 1
            root = 1; root.left = 2; root.right = 3
            把节点的值转换成二进制, 可以发现规律，从次高位开始枚举 如果是 0 表示在左节点, 如果是 1 表示在右节点
             */
            target++;
            TreeNode cur = root;
            for (int i = 30 - Integer.numberOfLeadingZeros(target); i >= 0; i--) {
                int bit = (target >> i) & 1;
                cur = bit == 0 ? cur.left : cur.right;
                if (cur == null) {
                    return false;
                }
            }
            // 没有走到空节点，说明存在
            // return true;

            return set.contains(target);
        }

        public void dfs(TreeNode node) {
            if (node == null) return;
            set.add(node.val);
            // 先更新值, 再访问子节点
            if (node.left != null) {
                node.left.val = node.val * 2 + 1;
                dfs(node.left);
            }
            if (node.right != null) {
                node.right.val = node.val * 2 + 2;
                dfs(node.right);
            }
        }

    }

}
