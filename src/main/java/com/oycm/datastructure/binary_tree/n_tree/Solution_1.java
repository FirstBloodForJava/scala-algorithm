package com.oycm.datastructure.binary_tree.n_tree;

import com.oycm.Node;

import java.util.ArrayList;
import java.util.List;

public class Solution_1 {

    /**
     * 589. <a href="https://leetcode.cn/problems/n-ary-tree-preorder-traversal/description/">N 叉树的前序遍历</a>
     *
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        ans = new ArrayList<>();
        dfs(root);
        return ans;
    }

    private List<Integer> ans;

    public void dfs(Node node) {
        if (node == null) return;
        ans.add(node.val);
        if (node.children == null || node.children.isEmpty()) return;
        for (Node child : node.children) {
            dfs(child);
        }
    }

}
