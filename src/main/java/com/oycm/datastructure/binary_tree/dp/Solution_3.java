package com.oycm.datastructure.binary_tree.dp;

import com.oycm.TreeNode;

public class Solution_3 {

    /**
     * LCP 10. <a href="https://leetcode.cn/problems/er-cha-shu-ren-wu-diao-du/description/">二叉树任务调度</a>
     * <p>
     * 对于某个任务，你需要先完成他的前导任务（如果非空），才能开始执行该任务。
     * 任务的依赖关系是一棵二叉树，其中 root 为根任务，root.left 和 root.right 为他的两个前导任务（可能为空）。root.val 为其自身的执行时间
     * <p>
     * 在一个 CPU 核执行某个任务时，我们可以在任何时刻暂停当前任务的执行，并保留当前执行进度。
     * <p>
     * 系统有两个 CPU 核，即我们可以同时执行两个任务，但是同一个任务不能同时在两个核上执行。给定这颗任务树，请求出所有任务执行完毕的最小时间。
     *
     * @param root
     * @return
     */
    public double minimalExecTime(TreeNode root) {
        /*
        1
      /  \
     3     2
          /  \
         4    4
        7.5
        一个 CPU 核在值为 3 节点执行 1 分钟, 另一个 CPU 核在值为 4 的两个节点各自执行 1/2 = 0.5 分钟
        1 + 3.5 + 2 + 1 = 7.5
        题解思路:
        要知道 完成子树的最短时间, 子树所有任务的总耗时
        理论最短时间 = max(最大链时间, 总时间 / 2)

        double[0] 子树任务总时间
        double[1] 子树最短完成时间
        状态转移方程
        root[0] = left[0] + right[0] + root.val
        root[1] = max(max(left[1], right[1]), (left[0] + right[0]) / 2) + node.val

        怎么证明 理论最短时间 = max(最大链时间, 总时间 / 2) 这个表达式成立?

         */
        return dfs(root)[1];
    }

    public double[] dfs(TreeNode node) {
        if (node == null) return new double[]{0, 0};
        double[] left = dfs(node.left);
        double[] right = dfs(node.right);
        double childSum = left[0] + right[0];
        double sum = childSum + node.val;

        return new double[]{sum, Math.max(Math.max(left[1], right[1]), childSum / 2) + node.val};
    }

}
