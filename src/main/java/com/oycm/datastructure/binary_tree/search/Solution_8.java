package com.oycm.datastructure.binary_tree.search;

import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_8 {

    /**
     * 1305. <a href="https://leetcode.cn/problems/all-elements-in-two-binary-search-trees/description/">两棵二叉搜索树中的所有元素</a> 1260
     *
     * @param root1
     * @param root2
     * @return
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> r1 = new ArrayList<>();
        List<Integer> r2 = new ArrayList<>();
        dfs(root1, r1);
        dfs(root2, r2);
        List<Integer> ans = new ArrayList<>();
        int i1 = 0, i2 = 0;
        while (true) {
            if (i1 == r1.size()) {
                ans.addAll(r2.subList(i2, r2.size()));
                break;
            }
            if (i2 == r2.size()) {
                ans.addAll(r1.subList(i1, r1.size()));
                break;
            }
            if (r1.get(i1) < r2.get(i2)) {
                ans.add(r1.get(i1++));
            } else {
                ans.add(r2.get(i2++));
            }
        }
        return ans;
    }

    public void dfs(TreeNode node, List<Integer> path) {
        if (node == null) return;
        dfs(node.left, path);
        path.add(node.val);
        dfs(node.right, path);
    }

}
