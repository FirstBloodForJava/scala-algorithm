package com.oycm.datastructure.binary_tree.bfs;

import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_1 {

    /**
     * 102.
     * <a href="https://leetcode.cn/problems/binary-tree-level-order-traversal/">二叉树的层序遍历</a>
     * <p>
     * 层序遍历: 即逐层地，从左到右访问所有节点
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        /*
        队列/数组记录 下一层要遍历的元素
         */
        if (root == null) return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        List<TreeNode> cur = List.of(root);
        while (!cur.isEmpty()) {
            List<Integer> layer = new ArrayList<>(cur.size());
            List<TreeNode> next = new ArrayList<>();
            for (TreeNode node : cur) {
                layer.add(node.val);
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            cur = next;
            ans.add(layer);
        }

        return ans;
    }

}
