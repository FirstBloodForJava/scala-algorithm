package com.oycm.datastructure.binary_tree.top_down_traverse;


import com.oycm.TreeNode;

public class Solution_2 {

    private int ans = Integer.MAX_VALUE;

    /**
     * 111. <a href="https://leetcode.cn/problems/minimum-depth-of-binary-tree/">二叉树的最小深度</a>
     *
     * @param root
     * @return 根节点到最近叶子节点的最长路径上的节点数
     */
    public int minDepth(TreeNode root) {
        /*
        可以转换成子问题: 左子树/右子树 的 最短长度, 加 1 就是 当前子树的最短长度
        如果 树是一个链表 这样计算就会有问题, 怎么判断是
         */
        dfs(root, 0);
        return ans;
    }

    public void dfs(TreeNode node, int cnt) {
        if (node == null) return ;
        cnt++;
        if (node.left == null && node.right == null) {
            ans = Math.min(cnt, ans);
            return;
        }
        dfs(node.left, cnt);
        dfs(node.right, cnt);
    }


}
