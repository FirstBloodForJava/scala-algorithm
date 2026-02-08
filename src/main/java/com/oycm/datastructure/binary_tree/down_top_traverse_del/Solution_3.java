package com.oycm.datastructure.binary_tree.down_top_traverse_del;

import com.oycm.TreeNode;

import java.util.*;

public class Solution_3 {

    /**
     * 1110. <a href="https://leetcode.cn/problems/delete-nodes-and-return-forest/description/">删点成林</a> 1511
     *
     * @param root
     * @param to_delete
     * @return
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        /*
        to_delete 为了判断删除, 可以转换成 set 集合
        题解思路, 后续遍历,
            更新当前 左/右子树 为递归后 左/右子树 的返回值;
            判断当前节点是否被删除
                删除, 则返回空节点, 子节点不为空则加入答案中
                不删除 返回当前节点
            如果根节点没删除, 把根节点加入答案

         */
        for (int i : to_delete) {
            set.add(i);
        }
        if (dfs(root) != null) {
            ans.add(root);
        }
        return ans;
    }

    Set<Integer> set = new HashSet<>();
    List<TreeNode> ans = new ArrayList<>();

    public TreeNode dfs(TreeNode node) {
        if (node == null) return null;
        node.left = dfs(node.left);
        node.right = dfs(node.right);
        if (!set.contains(node.val)) return node;
        if (node.left != null) {
            ans.add(node.left);
        }
        if (node.right != null) {
            ans.add(node.right);
        }
        return null;
    }
}
