package com.oycm.datastructure.binary_tree.create;

import com.oycm.TreeNode;

import java.util.Arrays;

public class Solution_8 {

    /**
     * 106. <a href="https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/">从中序与后序遍历序列构造二叉树</a>
     *
     * @param inorder   二叉树的中序遍历, 根-左-右
     * @param postorder 同一棵树的后序遍历, 左-右-根
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        /*
        题解思路
         */
        int n = postorder.length;
        if (n == 0) {
            return null;
        }
        int leftSize = indexOf(inorder, postorder[n - 1]);
        int[] pre1 = Arrays.copyOfRange(inorder, 0, leftSize);
        int[] pre2 = Arrays.copyOfRange(inorder, leftSize + 1, n);
        int[] in1 = Arrays.copyOfRange(postorder, 0, leftSize);
        int[] in2 = Arrays.copyOfRange(postorder, leftSize, n - 1);
        TreeNode left = buildTree(pre1, in1);
        TreeNode right = buildTree(pre2, in2);
        return new TreeNode(postorder[n - 1], left, right);
    }

    private int indexOf(int[] a, int x) {
        for (int i = 0; ; i++) {
            if (a[i] == x) {
                return i;
            }
        }
    }


}
