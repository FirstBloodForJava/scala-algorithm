package com.oycm.datastructure.binary_tree.create;

import com.oycm.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        /*
        inorder   [左子树中序遍历结果, 根, 右子树中序遍历结果]
        postorder [左子树后序遍历结果, 右子树后序遍历结果, 根]
         */
        int n = inorder.length;
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            index.put(inorder[i], i);
        }
        return dfs(0, n, 0, postorder, index);
    }

    public TreeNode dfs(int postL, int postR, int inL, int[] postorder, Map<Integer, Integer> index) {
        if (postL == postR) return null;
        int leftSize = index.get(postorder[postR - 1]) - inL;
        TreeNode left = dfs(postL, postL + leftSize, inL, postorder, index);
        TreeNode right = dfs(postL + leftSize, postR - 1,  leftSize + inL + 1, postorder, index);
        return new TreeNode(postorder[postR - 1], left, right);
    }

}
