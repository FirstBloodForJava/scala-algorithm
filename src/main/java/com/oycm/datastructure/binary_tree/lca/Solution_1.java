package com.oycm.datastructure.binary_tree.lca;

import com.oycm.TreeNode;

public class Solution_1 {

    /**
     * 235. <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/description/">二叉搜索树的最近公共祖先</a>
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*
        题解思路
        分类讨论:
            当前节点是空, 这种情况不用判断, 因为 p,q 在左右子树才会进入递归, 下一个节点肯定不为空
            p, q 都在左子树, 返回递归左子树的结果
            p, q 都在右子树, 返回递归右子树的结果
            其它, 返回当前节点, 当前节点就是最近公共祖先
                p 和 q 分别在 左右子树
                当前节点是 p
                当前节点是 q
        */
        int x = root.val;
        if (p.val < x && q.val < x) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > x && q.val > x) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

}
