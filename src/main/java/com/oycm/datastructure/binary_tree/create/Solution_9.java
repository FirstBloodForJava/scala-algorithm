package com.oycm.datastructure.binary_tree.create;

import com.oycm.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_9 {

    /**
     * 889. <a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-postorder-traversal/description/">根据前序和后序遍历构造二叉树</a> 1732
     *
     * @param preorder  无重复 值的二叉树的前序遍历
     * @param postorder 同一棵树的后序遍历
     * @return
     */
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        /*
        preorder  [根, 左子树前序遍历结果, 右子树前序遍历结果]
        postorder [左子树后序遍历结果, 右子树后序遍历结果, 根]
        题解思路: 如果二叉树的每个非叶节点都有两个儿子，知道前序和后序就能唯一确定这棵二叉树。
        preorder =  [1,2,4,5,3,6,7]
        postorder = [4,5,2,6,7,3,1]
        在前序遍历中, 规定 preorder[1] 为左子树的根节点
        2 为左子树的根节点, 则 2 == postorder[2] 以及左边都在左子树, 1 == postorder[6], [3,5] 就是右子树
        对应的数的情况就是
            preL = [2,4,5]; postL = [4,5,2]
            preR = [3,6,7]; postR = [6,7,3]
         */
        int n = preorder.length;
        if (n == 0) {
            return null;
        }
        if (n == 1) {
            return new TreeNode(preorder[0]);
        }
        int leftSize = indexOf(postorder, preorder[1]) + 1;
        int[] preL = Arrays.copyOfRange(preorder, 1, 1 + leftSize);
        int[] preR = Arrays.copyOfRange(preorder, 1 + leftSize, n);
        int[] postL = Arrays.copyOfRange(postorder, 0, leftSize);
        int[] postR = Arrays.copyOfRange(postorder, leftSize, n - 1);

        TreeNode left = constructFromPrePost(preL, postL);
        TreeNode right = constructFromPrePost(preR, postR);


        return new TreeNode(preorder[0], left, right);
    }

    private int indexOf(int[] a, int x) {
        for (int i = 0; ; i++) {
            if (a[i] == x) {
                return i;
            }
        }
    }

    public TreeNode constructFromPrePost1(int[] preorder, int[] postorder) {
        int n = preorder.length;
        Map<Integer, Integer> index = new HashMap<>(n, 1);
        for (int i = 0; i < postorder.length; i++) {
            index.put(postorder[i], i);
        }
        return dfs(0, n, 0, preorder, index);

    }

    public TreeNode dfs(int preL, int preR, int postL, int[] preorder, Map<Integer, Integer> index) {
        if (preL == preR) {
            return null;
        }
        if (preL + 1 == preR) {
            return new TreeNode(preorder[preL]);
        }
        // 后续遍历确定 左子树大小
        int leftSize = index.get(preorder[preL + 1]) - postL + 1;
        TreeNode left = dfs(preL + 1, preL + 1 + leftSize, postL, preorder, index);
        TreeNode right = dfs(preL + 1 + leftSize, preR, postL + leftSize, preorder, index);

        return new TreeNode(preorder[0], left, right);
    }

}
