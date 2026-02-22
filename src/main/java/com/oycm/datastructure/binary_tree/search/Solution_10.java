package com.oycm.datastructure.binary_tree.search;

import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_10 {

    /**
     * 897. <a href="https://leetcode.cn/problems/increasing-order-search-tree/description/">递增顺序搜索树</a> 1473
     *
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        TreeNode dummy = new TreeNode();
        TreeNode curr = dummy;
        for (Integer c : list) {
            curr.right = new TreeNode(c);
            curr = curr.right;
        }

        return dummy.right;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) return;
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }

}
