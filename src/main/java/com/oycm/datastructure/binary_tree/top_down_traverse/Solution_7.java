package com.oycm.datastructure.binary_tree.top_down_traverse;

import com.oycm.TreeNode;


public class Solution_7 {


    /**
     * 1315. <a href="https://leetcode.cn/problems/sum-of-nodes-with-even-valued-grandparent/description/">祖父节点值为偶数的节点和</a> 1427
     *
     * @param root
     * @return 节点的祖父节点的值为偶数的和
     */
    public int sumEvenGrandparent(TreeNode root) {
        /*dfs(root);
        return ans;*/
        if (root.left != null) {
            dfs(root, root.left, root.left.left);
            dfs(root, root.left, root.left.right);
        }
        if (root.right != null) {
            dfs(root, root.right, root.right.left);
            dfs(root, root.right, root.right.right);
        }

        return ans;
    }

    int ans = 0;

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.val % 2 == 0) {
            if (node.left != null) {
                if (node.left.left != null) {
                    ans += node.left.left.val;
                }
                if (node.left.right != null) {
                    ans += node.left.right.val;
                }
            } else if (node.right != null) {
                if (node.right.left != null) {
                    ans += node.right.left.val;
                }
                if (node.right.right != null) {
                    ans += node.right.right.val;
                }
            }
        }
        dfs(node.left);
        dfs(node.right);

    }

    public void dfs(TreeNode grandparent, TreeNode parent, TreeNode node) {
        /*
        祖父节点, 父节点, 当前节点
         */
        if (node == null) {
            return;
        }
        if (grandparent.val % 2 == 0) {
            ans += node.val;
        }
        dfs(parent, node, node.left);
        dfs(parent, node, node.right);
    }

    public void dfs(int grandParentVal, int parentVal, TreeNode node) {
        /*
        使用 1 表示虚拟的 祖父节点, 父节点, 肯定是不符合要求的
         */
        if (node == null) {
            return;
        }
        if (grandParentVal % 2 == 0) {
            ans += node.val;
        }
        // 新一轮的 祖父 -> 父亲 -> 当前节点
        dfs(parentVal, node.val, node.left);
        dfs(parentVal, node.val, node.right);
    }

}
