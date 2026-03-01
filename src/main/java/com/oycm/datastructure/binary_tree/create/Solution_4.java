package com.oycm.datastructure.binary_tree.create;

import com.oycm.TreeNode;

public class Solution_4 {

    /**
     * 1008. <a href="https://leetcode.cn/problems/construct-binary-search-tree-from-preorder-traversal/description/">前序遍历构造二叉搜索树</a> 1563
     *
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        /*
        题解思路: 前序遍历 8,5,1,7,10,12 preorder[0] 是 root 的根节点
        最后一个小于 preorder[0] 的 idx left 一定是 root 的左子树
        left + 1 则一定是 root 的右子树
        idx 的查找可以使用二分查找，如果存在 右子树，则 分界点 至右边一定是单调递增的
        这样就可以递归的构建左右子树
         */
        int len = preorder.length;
        if (len == 0) {
            return null;
        }
        return dfs(preorder, 0, len - 1);
    }

    private TreeNode dfs(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[left]);
        if (left == right) {
            return root;
        }

        // 在区间 [left..right] 里找最后一个小于 preorder[left] 的下标
        // 注意这里设置区间的左边界为 left ，不能是 left + 1
        // 这是因为考虑到区间只有 2 个元素 [left, right] 的情况，第 1 个部分为空区间，第 2 部分只有一个元素 right
        int l = left;
        int r = right;

        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (preorder[mid] < preorder[left]) {
                // 下一轮搜索区间是 [mid, r]
                l = mid;
            } else {
                // 下一轮搜索区间是 [l, mid - 1]
                r = mid - 1;
            }
        }

        TreeNode leftTree = dfs(preorder, left + 1, l);
        TreeNode rightTree = dfs(preorder, l + 1, right);
        root.left = leftTree;
        root.right = rightTree;
        return root;
    }

}
