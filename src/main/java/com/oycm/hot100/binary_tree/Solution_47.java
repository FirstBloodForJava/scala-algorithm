package com.oycm.hot100.binary_tree;

import com.oycm.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution_47 {

    /**
     * 105. <a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/">从前序与中序遍历序列构造二叉树</a>
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        /*
        二叉树
        3
      9   20
    6  7 15 7
        先序遍历, 根左右 [3, 9, 6, 7, 20, 15, 7] preorder
        中序遍历, 左根右 [6, 9, 7, 3, 15, 20, 7] inorder
        preorder[0] 是二叉树的根节点，找到 inorder[0] 在中序遍历的位置 3，
            根节点左边是左子树的数量，为 3 - 0
            根节点右边是右子树的数量，为 4 - 4
            所以先序遍历数组可以分为，[1, 4) [4, 7) 两个数组分别表示对于左右子树
            这样得到了两个先序遍历数组和对于中序遍历的数组，可以递归的构建二叉树
        递归遍历 l == r return null
        为了快速查询 preorder[0] 在 inorder 中的位置，可以使用 hash 表 key 作为值，value 为中序遍历的下标
        还需 inorder 中序遍历左子树下标，找到根节点下标时，好计算左子树长度，用来截取 pre 数组范围
         */
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return dfs(0, inorder.length, 0, preorder, map);
    }

    public TreeNode dfs(int l, int r, int inL, int[] preorder, Map<Integer, Integer> map) {
        if (l == r) return null;
        int leftSize = map.get(preorder[l]) - inL;
        return new TreeNode(preorder[l],
                dfs(l + 1,l + 1 + leftSize, inL, preorder, map ),
                dfs(l + 1 + leftSize, r, inL + 1 + leftSize, preorder, map));
    }

}
