package com.oycm.hot100.binary_tree;

import com.oycm.TreeNode;

public class Solution_44 {

    /**
     * 230. <a href="https://leetcode.cn/problems/kth-smallest-element-in-a-bst/description/">二叉搜索树中第 K 小的元素</a>
     *
     * @param root 二叉搜索树的根节点
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        /*
        查找其中第 k 小的元素（k 从 1 开始计数）
         */
        /*
        题解思路：中序遍历 + 不记录答案 + 提前返回
        由于 Node.val [0, 1e4]
            递归边界：如果当前节点是空节点，返回 -1；
            中序遍历，先递归左子树；
            判断左子树结果是否为 -1，如果不是 -1，说明在左子树中找到了答案，返回左子树结果；否则，继续下一步；
            把 k 减少 1，如果 k == 0，那么答案就是当前节点值，返回当前节点值。
            如果当前节点不是答案，递归右子树，右子树如果不是 -1，则返回该子树结果；如果是 -1，表示当前子树搜索完毕，不符合条件，返回 -1
         */
        /*
        查找其中第 k 大的元素（k 从 1 开始计数）
        递归树的顺序交换，先递归有子树，根，左子树
         */
        this.k = k;
        return dfs(root);
    }

    int k;

    public int dfs(TreeNode node) {
        if (node == null) return -1;
        int leftVal = dfs(node.left);
        if (leftVal != -1) {
            return leftVal;
        }
        if (--k == 0) {
            return node.val;
        }
        return dfs(node.right);
    }


}
