package com.oycm.datastructure.binary_tree.bfs;

import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_12 {

    /**
     * 2415. <a href="https://leetcode.cn/problems/reverse-odd-levels-of-binary-tree/description/">反转二叉树的奇数层</a> 1431
     * <p>
     * 完美 二叉树: 二叉树的所有父节点都有两个子节点，且所有叶子节点都在同一层
     *
     * @param root 完美 二叉树
     * @return 反转这棵树中每个 奇数 层的节点值, 节点的 层数 等于该节点到根节点之间的边数
     */
    public TreeNode reverseOddLevels(TreeNode root) {
        /*
        bfs 遍历, 奇数层, 把节点值交换下
         */
        int depth = 0;
        List<TreeNode> cur = List.of(root);
        while (!cur.isEmpty()) {
            List<TreeNode> next = new ArrayList<>();

            for (TreeNode node : cur) {
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            if (depth % 2 == 1) {
                int n = cur.size();
                // 利用偶数性质
                for (int i = 0; i < n / 2; i++) {
                    int temp = cur.get(i).val;
                    cur.get(i).val = cur.get(n - 1 - i).val;
                    cur.get(n - 1 - i).val = temp;
                }
            }
            cur = next;
            depth++;
        }

        return root;
    }

    class Solution_12_1 {

        public TreeNode reverseOddLevels(TreeNode root) {
            dfs(root.left, root.right, true);
            return root;
        }

        public void dfs(TreeNode node1, TreeNode node2, boolean swap) {
            if (node1 == null) return;

            if (swap) {
                int temp = node1.val;
                node1.val = node2.val;
                node2.val = temp;
            }
            dfs(node1.left, node2.right, !swap);
            dfs(node1.right, node2.left, !swap);
        }

    }

}
