package com.oycm.datastructure.binary_tree.traverse;


import com.oycm.TreeNode;

public class Solution_7 {

    int ans = -1;
    int head;
    /**
     * 671. <a href="https://leetcode.cn/problems/second-minimum-node-in-a-binary-tree/description/">二叉树中第二小的节点</a>
     *
     * @param root 非空特殊的二叉树, 每个节点的子节点数量只能为 2 或 0, 且 root.val = min(root.left.val, root.right.val)
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        /*
        怎么利用 root.val = min(root.left.val, root.right.val) 这一特点, 头节点是 第一小
        递归过程和 head.val 比较, 比 head 大, 则更新答案, 答案更新后, node.val >= ans 该节点不需要进行后续递归
        如果小于 ans 则更新最小
         */
        head = root.val;
        return ans;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        if (ans != -1 && node.val >= ans) {
            return;
        }
        // 比 ans 大的结束递归, 后面如果比 ans 小, 但是 大于 head 继续更新答案
        if (node.val > head) {
            ans = node.val;
        }
        dfs(node.left);
        dfs(node.right);
    }



}
