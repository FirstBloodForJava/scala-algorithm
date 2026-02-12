package com.oycm.datastructure.binary_tree.lca;

import com.oycm.TreeNode;

public class Solution_4 {

    /**
     * 2096. <a href="https://leetcode.cn/problems/step-by-step-directions-from-a-binary-tree-node-to-another/description/">从二叉树一个节点到另一个节点每一步的方向</a> 1805
     *
     * @param root       二叉树总共有 n 个节点, 每个节点的值为 1 到 n 中的一个整数, 且互不相同
     * @param startValue 起点节点 s 的值
     * @param destValue  终点节点 t 的值
     * @return
     */
    public String getDirections(TreeNode root, int startValue, int destValue) {
        /*
        直接经过 lca 的路径是最短的
        root -> s 最短路径 lcaS
        root -> t 最短路径 lcaT
        分类讨论:
            s 和 t 在 root 的左右子树, 则 lcaS 和 lcaT 没有公共前缀, lcaS 全部转换成 U + lcaT 就是答案
            s 是 t 的 lca, lcaT 包含 lcaS, lcaT 截取 lcaS 后就是答案
            t 是 s 的 lca, lcaS 包含 lcaT, lcaS 截取 lcaT 后, 再把剩余字符转换成 U 就是 答案

            去掉公共前缀后, lcaS 全部转换成 U 再加 lcaT 即使答案
         */
        dfs(root, startValue, new StringBuilder());
        String lcaS = ans;
        dfs(root, destValue, new StringBuilder());
        String lcaT = ans;
        int lca = 0;
        for (int i = 0; i < lcaS.length() && i < lcaT.length(); i++) {
            if (lcaS.charAt(i) == lcaT.charAt(i)) {
                lca++;
            } else {
                break;
            }
        }
        return lcaS.substring(lca).replaceAll("(?s).", "U") + lcaT.substring(lca);
    }

    private String ans;

    public void dfs(TreeNode node, int target, StringBuilder path) {
        // 找到 root 到 target 的路径
        if (node == null) return;
        if (node.val == target) {
            ans = path.toString();
            return;
        }
        dfs(node.left, target, path.append("L"));
        path.setCharAt(path.length() - 1, 'R');
        dfs(node.right, target, path);
        path.setLength(path.length() - 1);
    }


}
