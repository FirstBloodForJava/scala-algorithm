package com.oycm.datastructure.binary_tree.create;

import com.oycm.TreeNode;

public class Solution_3 {

    /**
     * 998. <a href="https://leetcode.cn/problems/maximum-binary-tree-ii/description/">最大二叉树 II</a> 1498
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        /*
        把 root 树 还原成数组比较麻烦
        由于新增的 val 在最右边, 如果 val 是里面的最大值, 则 val 作为 根节点返回
        val 会插入在 root 的右子树, 遍历右子树 val > cur.val 则 parent.right 为 新节点, 新节点的左节点 指向当前节点
        遍历所有的 cur 都不存在以上情况, 则 val 为最小, 在最右边
         */
        TreeNode parent = null;
        TreeNode cur = root;
        while (cur != null) {
            if (val > cur.val) {
                if (parent == null) {
                    return new TreeNode(val, cur, null);
                }
                TreeNode node = new TreeNode(val, cur, null);
                parent.right = node;
                return root;
            } else {
                parent = cur;
                cur = cur.right;
            }
        }
        parent.right = new TreeNode(val);
        return root;
    }

}
