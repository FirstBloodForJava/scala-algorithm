package com.oycm.datastructure.binary_tree.recursion;

import com.oycm.TreeNode;

public class Solution_3 {

    /**
     * 865. <a href="https://leetcode.cn/problems/smallest-subtree-with-all-the-deepest-nodes/description/">具有所有最深节点的最小子树</a> 1534
     *
     * @param root
     * @return 包含原始树中所有 最深节点 的 最小子树
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        /*
        题解思路:
        lca Lowest common ancestor 最近公共祖先
        这颗二叉树最深叶结点的深度, 指叶子节点在这棵子树内的深度，而不是在整棵二叉树的视角下的深度。相当于这棵子树的高度。
        这棵子树的最深叶结点的最近公共祖先 lca。

        设子树的根节点为 node，node 的左子树的高度为 leftHeight，node 的右子树的高度为 rightHeight。
            如果 leftHeight>rightHeight，那么子树的高度为 leftHeight+1，lca 是左子树的 lca。
            如果 leftHeight<rightHeight，那么子树的高度为 rightHeight+1，lca 是右子树的 lca。
            如果 leftHeight=rightHeight，那么子树的高度为 leftHeight+1，lca 就是 node。
         */
        return dfs(root).lca;
    }

    private record Pair(int height, TreeNode lca) {
    }

    private Pair dfs(TreeNode node) {
        if (node == null) {
            return new Pair(0, null);
        }

        Pair left = dfs(node.left);
        Pair right = dfs(node.right);

        // 左子树更高
        if (left.height > right.height) {
            return new Pair(left.height + 1, left.lca);
        }
        // 右子树更高
        if (left.height < right.height) {
            return new Pair(right.height + 1, right.lca);
        }
        // 一样高
        return new Pair(left.height + 1, node);
    }

}
