package com.oycm.datastructure.binary_tree.n_tree;

import com.oycm.Node;

import java.util.ArrayList;
import java.util.List;

public class Solution_2 {

    /**
     * 590. <a href="https://leetcode.cn/problems/n-ary-tree-postorder-traversal/description/">N 叉树的后序遍历</a>
     *
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        ans = new ArrayList<>();
        dfs(root);
        if (root != null) {
            ans.add(root.val);
        }
        return ans;
    }

    private List<Integer> ans;

    public void dfs(Node node) {
        if (node == null) return;
        if (node.children == null || node.children.isEmpty()) return;
        for (Node child : node.children) {
            dfs(child);
            ans.add(child.val);
        }
    }

}
