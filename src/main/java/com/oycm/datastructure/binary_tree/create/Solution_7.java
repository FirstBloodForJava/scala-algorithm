package com.oycm.datastructure.binary_tree.create;

import com.oycm.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_7 {

    /**
     * 105. <a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/">从前序与中序遍历序列构造二叉树</a>
     *
     * @param preorder 二叉树的先序遍历, 根-左-右
     * @param inorder 同一棵树的中序遍历, 左-根-右
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        /*
        题解思路
         */
        int n = preorder.length;
        if (n == 0) {
            return null;
        }
        int leftSize = indexOf(inorder, preorder[0]);
        int[] pre1 = Arrays.copyOfRange(preorder, 1, 1 + leftSize);
        int[] pre2 = Arrays.copyOfRange(preorder, 1 + leftSize, n);
        int[] in1 = Arrays.copyOfRange(inorder, 0, leftSize);
        int[] in2 = Arrays.copyOfRange(inorder, 1 + leftSize, n);
        TreeNode left = buildTree(pre1, in1);
        TreeNode right = buildTree(pre2, in2);
        return new TreeNode(preorder[0], left, right);
    }

    private int indexOf(int[] a, int x) {
        for (int i = 0; ; i++) {
            if (a[i] == x) {
                return i;
            }
        }
    }

    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        int n = inorder.length;
        Map<Integer,Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            index.put(inorder[i], i);
        }
        return dfs(0, n, 0, preorder, index);
    }

    /**
     *
     * @param preL preorder 左闭区间
     * @param preR preorder 右开区间
     * @param inL inorder 左闭区间
     * @param preorder
     * @param index
     * @return
     */
    public TreeNode dfs(int preL, int preR, int inL, int[] preorder, Map<Integer,Integer> index) {
        /*
        preorder [根, 左子树前序遍历结果, 右子树前序遍历结果]
        inorder  [左子树中序遍历结果, 根, 右子树中序遍历结果]
        preorder 根节点 在 inorder 中找位置, 该位置表示左子树边界
         */
        if (preL == preR) return null;
        // 左子树大小
        int leftSize = index.get(preorder[preL]) - inL;
        TreeNode left = dfs(preL + 1, preL + 1 + leftSize, inL, preorder, index);
        TreeNode right = dfs(preL + 1 + leftSize, preR, inL + 1 + leftSize, preorder, index);
        return new TreeNode(preorder[preL], left, right);
    }
}
