package com.oycm.datastructure.binary_tree.traverse;


import com.oycm.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class Solution_5 {

    /**
     * LCP 44. <a href="https://leetcode.cn/problems/sZ59z6/description/">开幕式焰火</a>
     *
     * @param root 二叉树 root 代表焰火，节点值表示巨型焰火这一位置的颜色种类(表示什么颜色)
     * @return
     */
    public int numColor(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        dfs(root, set);
        return set.size();
    }

    public void dfs(TreeNode node, Set<Integer> set) {
        if (node == null) return;
        set.add(node.val);
        dfs(node.left, set);
        dfs(node.right, set);
    }
}
