package com.oycm.datastructure.binary_tree.top_down_traverse;


import com.oycm.TreeNode;

public class Solution_8 {

    /**
     * 988. <a href="https://leetcode.cn/problems/smallest-string-starting-from-leaf/description/">从叶结点开始的最小字符串</a> 1429
     *
     * @param root
     * @return 求 字符串从这棵树的一个叶子结点开始，到根结点结束 的最小字典序字符串
     */
    public String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return ans;
    }

    private String ans = "~";

    public void dfs(TreeNode node, StringBuilder sb) {
        if (node == null) return;
        sb.append((char) (node.val + 'a'));

        if (node.left == null && node.right == null) {
            sb.reverse();
            String temp = sb.toString();
            sb.reverse();
            if (temp.compareTo(ans) < 0) {
                ans = temp;
            }
        }

        dfs(node.left, sb);
        dfs(node.right, sb);
        // 回溯
        sb.deleteCharAt(sb.length() - 1);
    }

}
