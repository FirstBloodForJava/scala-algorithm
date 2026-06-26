package com.oycm.hot100.binary_tree;

import com.oycm.TreeNode;

public class Solution_109 {

    /**
     * 297. <a href="https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/description/">二叉树的序列化与反序列化</a>
     */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            dfs(root, sb);
            System.out.println(sb);
            return sb.toString();
        }

        public void dfs(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append("#,");
            } else {
                sb.append(node.val).append(",");
                dfs(node.left, sb);
                dfs(node.right, sb);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] cs = data.split(",");
            System.out.println(cs.length);
            return dfs(cs);
        }

        int i = 0;

        public TreeNode dfs(String[] cs) {
            String s = cs[i++];
            if (s.equals("#")) {
                return null;
            }
            return new TreeNode(Integer.parseInt(s), dfs(cs), dfs(cs));
        }
    }

}
