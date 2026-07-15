package com.oycm.week.lc2018.No98;

import com.oycm.TreeNode;

public class Solution_889 {

    /**
     * 889. 根据前序和后序遍历构造二叉树
     * <br>
     * 889. <a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-postorder-traversal/description/">根据前序和后序遍历构造二叉树</a> 1732
     *
     * @param preorder  无重复 值的二叉树的前序遍历
     * @param postorder 同一棵树的后序遍历
     * @return
     */
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        /*
        给定两个整数数组，preorder 和 postorder ，
        其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵树的后序遍历，重构并返回二叉树。
        如果存在多个答案，您可以返回其中 任何 一个。
         */
        /*
        preorder  [根, 左子树前序遍历结果, 右子树前序遍历结果]
        postorder [左子树后序遍历结果, 右子树后序遍历结果, 根]
        1      1
       /        \
      2          2
     / \        / \
    3   4      3   4
        preorder = [1, 2, 3, 4]
        postorder = [3, 4, 2, 1]
        前序遍历和后序遍历，确定的二叉树并不唯一。

        preorder =  [1,2,4,5,3,6,7]
        postorder = [4,5,2,6,7,3,1]
        在前序遍历中, 规定 preorder[1] 为左子树的根节点
        则 preorder[1] = 2 为 左子树的根节点。
        在后序遍历中找到 2 所在位置 2 = postorder[2]：
            i = 2 以及左边的都在左子树；
            i = 2 右边的数（去掉根节点）都在右子树。
        根据后序遍历获得的左右子树大小情况，可以确定前序遍历左右子树对应的数组
            左子树 [1, 3]
            右子树 [4, 6]
        现在知道了新的二叉树 前序遍历和后序遍历结果，可以根据这个继续构建二叉树
        子问题和原问题一样，可以用递归解决。
         */
        int n = preorder.length;
        int[] idx = new int[n + 1];
        for (int i = 0; i < n; i++) {
            idx[postorder[i]] = i;
        }
        return dfs(0, n, 0, preorder, idx);
    }

    // postL 后序遍历子树开始位置
    private TreeNode dfs(int preL, int preR, int postL, int[] preorder, int[] idx) {
        if (preL == preR) {
            return null;
        }
        if (preL + 1 == preR) {
            // 叶子节点
            return new TreeNode(preorder[preL]);
        }
        // 根据左子树根节点，确定后序遍历左子树长度
        int leftSize = idx[preorder[preL + 1]] - postL + 1;
        TreeNode left = dfs(preL + 1, preL + 1 + leftSize, postL, preorder, idx);
        TreeNode right = dfs(preL + 1 + leftSize, preR, postL + leftSize, preorder, idx);
        return new TreeNode(preorder[preL], left, right);
    }

}
