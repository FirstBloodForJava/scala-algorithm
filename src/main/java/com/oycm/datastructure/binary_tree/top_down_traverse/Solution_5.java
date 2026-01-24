package com.oycm.datastructure.binary_tree.top_down_traverse;


import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_5 {

    /**
     * 199. <a href="https://leetcode.cn/problems/binary-tree-right-side-view/description/">二叉树的右视图</a>
     *
     * @param root
     * @return 按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans, 0);
        return ans;
    }

    public void dfs(TreeNode node, List<Integer> ans, int i) {
        if (node == null) {
            return;
        }
        if (i == ans.size()) {
            ans.add(node.val);
        }
        dfs(node.right, ans, i + 1);
        dfs(node.left, ans, i + 1);
    }


}
