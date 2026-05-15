package com.oycm.hot100.binary_tree;

import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_45 {

    /**
     * 199. <a href="https://leetcode.cn/problems/binary-tree-right-side-view/description/">二叉树的右视图</a>
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        /*
        给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
         */
        /*
        根 -> 右子树 -> 左子树 顺序遍历，用一个遍历记录深度，当深度 i 首次等于视图的大小时，对应的节点就应该在视图中
         */
        List<Integer> path = new ArrayList<>();
        dfs(root, 0, path);
        return path;
    }

    public void dfs(TreeNode node, int i, List<Integer> path) {
        if (node == null) return;
        if (i == path.size()) path.add(node.val);
        dfs(node.right, i + 1, path);
        dfs(node.left, i + 1, path);
    }


}
