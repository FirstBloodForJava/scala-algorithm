package com.oycm.datastructure.binary_tree.traverse;


import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_4 {

    /**
     * 872. <a href="https://leetcode.cn/problems/leaf-similar-trees/description/">叶子相似的树</a> 1288
     *
     * 叶值序列: 二叉树所有的叶子, 从左到右的顺序构成的序列
     * @param root1
     * @param root2
     * @return root1 和 root2 的 叶值序列 是否相等
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        /*
        获取 root1, root2 的叶值序列, 再进行比较
         */
        List<Integer> l1 = new ArrayList<>();
        dfs(root1, l1);
        List<Integer> l2 = new ArrayList<>();
        dfs(root2, l2);
        if (l1.size() != l2.size()) {
            return false;
        }
        for (int i = 0; i < l1.size(); i++) {
            if (l1.get(i).compareTo(l2.get(i)) != 0) {
                return false;
            }
        }

        return true;
    }

    public void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            list.add(node.val);
            return;
        }
        dfs(node.left, list);
        dfs(node.right, list);
    }

}
