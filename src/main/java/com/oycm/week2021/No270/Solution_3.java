package com.oycm.week2021.No270;

import com.oycm.TreeNode;

public class Solution_3 {

    /**
     * 2096. <a href="https://leetcode.cn/problems/step-by-step-directions-from-a-binary-tree-node-to-another/description/">从二叉树一个节点到另一个节点每一步的方向</a> 1805
     *
     * @param root
     * @param startValue
     * @param destValue
     * @return
     */
    public String getDirections(TreeNode root, int startValue, int destValue) {
        /*
        给你一棵 二叉树 的根节点 root ，这棵二叉树总共有 n 个节点。每个节点的值为 1 到 n 中的一个整数，且互不相同。
        给你一个整数 startValue ，表示起点节点 s 的值，和另一个不同的整数 destValue ，表示终点节点 t 的值。
        请找到从节点 s 到节点 t 的 最短路径 ，并以字符串的形式返回每一步的方向。每一步用 大写 字母 'L' ，'R' 和 'U' 分别表示一种方向：
            'L' 表示从一个节点前往它的 左孩子 节点。
            'R' 表示从一个节点前往它的 右孩子 节点。
            'U' 表示从一个节点前往它的 父 节点。
        请你返回从 s 到 t 最短路径 每一步的方向。
         */
        /*
        找到 root -> s 的路径 toS，找到 root -> t 的路径 toT，公共路径 lca 转换成 U，再加上 toT 后的公共路径
         */
        dfs(root, startValue, destValue, new StringBuilder());
        int i = 0;
        while (i < Math.min(toS.length(), toT.length()) && toS.charAt(i) == toT.charAt(i)) {
            i++;
        }
        return "U".repeat(toS.length() - i) + toT.substring(i);
    }

    String toS, toT;

    public void dfs(TreeNode node, int startValue, int destValue, StringBuilder path) {
        if (node == null) {
            return;
        }
        if (node.val == startValue) {
            toS = path.toString();
        } else if (node.val == destValue) {
            toT = path.toString();
        }
        path.append("L");
        dfs(node.left, startValue, destValue, path);
        path.setCharAt(path.length() - 1, 'R');
        dfs(node.right, startValue, destValue, path);
        // 回溯
        path.setLength(path.length() - 1);
    }

}
