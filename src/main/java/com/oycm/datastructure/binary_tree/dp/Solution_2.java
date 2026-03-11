package com.oycm.datastructure.binary_tree.dp;

import com.oycm.TreeNode;

public class Solution_2 {

    /**
     * 968. <a href="https://leetcode.cn/problems/binary-tree-cameras/description/">监控二叉树</a> 2124
     *
     * @param root
     * @return
     */
    public int minCameraCover(TreeNode root) {
        /*
        ints.length = 3
        ans[0] 表示 当前节点安装摄像头, 蓝色
        ans[1] 表示 当前节点不安装摄像头, 且它的父节点安装摄像头, 黄色
        ans[2] 表示 当前节点不安装摄像头, 且它的至少一个子节点安装摄像头, 红色
        子树根节点为 蓝色 时, 这颗子树需要多少摄像头
        子树根节点为 黄色 时, 这颗子树需要多少摄像头
        子树根节点为 红色 时, 这颗子树需要多少摄像头
            蓝色
           /    \
  蓝色,黄色,红色 蓝色,黄色,红色      子节点可为 蓝色或黄色或红色
            黄色
           /    \
      蓝色,红色  蓝色,红色           子节点可为 蓝色或红色; 不能为黄色, 因为父节点是黄色, 黄色父节点安装摄像头, 黄色的父节点需要安装摄像头, 不符合要求
            红色          红色          红色
           /    \        /    \        /   \
         蓝色    蓝色   蓝色   红色    红色   蓝色  子节点至少一个蓝色

         计算过程
         蓝色 = min(左蓝, 左黄, 左红) + min(右蓝, 右黄, 右红) + 1
         黄色 = min(左蓝, 左红) + min(右蓝, 右红)
         红色 = min(左蓝 + 右红, 左红 + 右蓝, 左蓝 + 右蓝)

        递归边界: 节点为空
            蓝色 = Integer.MAX_VALUE / 2
            黄色 = 0
            红色 = 0
        蓝色不能返回 0, 因为如果这里返回了 0, 叶子节点红色的代价是 0, 这个结果是不合法的
        root 节点没有父节点，所有去 min(ans[0], ans[2])

        红色节点计算可优化
            至少选一个蓝色和这个 min(左蓝, 左红) + min(右蓝, 右红) + max(0, min(左蓝 - 左红, 右蓝 - 右红) ) 等价
            当所有红色都比蓝色小时, max 表达式表示把一个 红色变成蓝色 的最小代价

        表达式计算优化
        黄色 = min(左蓝, 左红) + min(右蓝, 右红)
        红色 = 黄色 + max(0, min(左蓝 - 左红, 右蓝 - 右红)
        蓝色 = min(左蓝, 左黄) + min(右蓝, 右黄) + 1 因为 红色 = 黄色 + ?(>= 0)

         */
        int[] ans = dfs(root);
        return Math.min(ans[0], ans[2]);
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        /*// 当前节点安装监控
        int choose = Math.min(left[0], left[1]) + Math.min(right[0], right[1]) + 1;
        // 父节点安装监控
        int byFa = Math.min(left[0], left[2]) + Math.min(right[0], right[2]);
        // 至少一个子节点安装监控
        int byChildren = Math.min(Math.min(left[0] + right[2], left[2] + right[0]), left[0] + right[0]);*/

        // 父节点安装监控 黄色
        int byFa = Math.min(left[0], left[2]) + Math.min(right[0], right[2]);
        // 至少一个子节点安装监控 红色
        int byChildren = byFa + Math.max(0, Math.min(left[0] - left[2], right[0] - right[2]));
        // 当前节点安装监控
        int choose = Math.min(left[0], left[1]) + Math.min(right[0], right[1]) + 1;

        return new int[]{choose, byFa, byChildren};
    }

}
