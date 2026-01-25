package com.oycm.datastructure.binary_tree.top_down_traverse;

import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_13 {

    /**
     * 971. <a href="https://leetcode.cn/problems/flip-binary-tree-to-match-preorder-traversal/description/">翻转二叉树以匹配先序遍历</a> 1787
     * <p>
     * 交换节点的左右子树，可以 翻转 该二叉树中的任意节点
     *
     * @param root   树中有 n 个节点, 节点值范围在 [1, n]
     * @param voyage 预期 的二叉树 先序遍历 结果
     * @return 求翻转的最少节点列表
     */
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        flipped = new ArrayList<>();
        idx = 0;
        this.voyage = voyage;
        dfs(root);
        if (!flipped.isEmpty() && flipped.get(0) == -1) {
            flipped.clear();
            flipped.add(-1);
        }
        return flipped;
    }

    List<Integer> flipped;
    int idx;
    int[] voyage;

    public void dfs(TreeNode node) {
        if (node == null) return;
        // 当前节点值和遍历的点不匹配, 翻转后肯定不符合要求,
        if (node.val != voyage[idx++]) {
            flipped.clear();
            flipped.add(-1);
            return;
        }
        if (idx < voyage.length && node.left != null && node.left.val != voyage[idx]) {
            flipped.add(node.val);
            dfs(node.right);
            dfs(node.left);
        } else {
            // 先序遍历
            dfs(node.left);
            dfs(node.right);
        }
    }
}
