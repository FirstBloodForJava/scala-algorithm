package com.oycm.datastructure.binary_tree.search;

import com.oycm.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class Solution_12 {

    /**
     * 653. <a href="https://leetcode.cn/problems/two-sum-iv-input-is-a-bst/description/">两数之和 IV - 输入二叉搜索树</a>
     *
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        /*
        哈希表 + 枚举右维护左 相当于两数之和
         */
        return dfs(root, k, new HashSet<>());
    }

    public boolean dfs(TreeNode node, int k, Set<Integer> set) {
        if (node == null) return false;

        if (dfs(node.left, k, set) || set.contains(k - node.val)) {
            return true;
        }
        set.add(node.val);
        return dfs(node.right, k, set);
    }

}
